package com.dogsnouts.cleopatra;

import java.util.*;

class Game {

    private final Map<PlayerID, Player> players = Map.of(PlayerID.Cleopatra, new Player(),
                                                         PlayerID.Caesar, new Player());
    private final Map<Vote, Map<PlayerID, InfluenceGroup>> influenceGroups = new LinkedHashMap<>();
    private final Map<Vote, PatricianGroup> patricianGroups = new LinkedHashMap<>();
    private int numPatriciansLeftToWin = 21; //way to avoid hard coding?
    private final List<VoteOfConfCard> voteOfConfDeck = new ArrayList<>();
    private final List<VoteOfConfCard> voteOfConfDiscard = new ArrayList<>();
    private PlayerID currentPlayerID = PlayerID.Cleopatra;
    private GamePhase gamePhase = GamePhase.Preparation;

    private enum PlayerID {
        Cleopatra,
        Caesar;

        static PlayerID getOtherPlayerID(PlayerID currentPlayerID) {
            if (currentPlayerID == PlayerID.Cleopatra) {
                return PlayerID.Caesar;
            } else {
                return PlayerID.Cleopatra;
            }
        }
    }

    private enum GamePhase {
        Preparation,
        Play,
        Score
    }

    //populate patricianGroups, influenceGroups, and voteOfConfDeck
    Game() {
        for (Vote vote : Vote.values()) {
            switch (vote.getVoteType()) {
                case Patrician:
                    patricianGroups.put(vote, new PatricianGroup(vote));
                    for (PlayerID playerID : PlayerID.values()) {
                        influenceGroups.put(vote, Map.of(playerID, new InfluenceGroup(vote)));
                    }
                    voteOfConfDeck.add(new VoteOfConfCard(vote));
                    break;
                case NotPatrician:
                    voteOfConfDeck.add(new VoteOfConfCard(vote));
                    break;
            }
        }

        Collections.shuffle(voteOfConfDeck);
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayerID);
    }

    Player getOtherPlayer() {
        return players.get(PlayerID.getOtherPlayerID(currentPlayerID));
    }

                                            //PREPARATION PHASE

    void prepInfluenceGroups(HashMap<Vote, InfluenceCard> cards) {
        cards.forEach((vote, card) ->
            playInfluenceCard(vote, card)
        );
        getCurrentPlayer().getHand().fillStarterHand(); //refill hand with start cards
        if (currentPlayerID == PlayerID.Caesar) {
            gamePhase = GamePhase.Play;
        }
        currentPlayerID = PlayerID.getOtherPlayerID(currentPlayerID); //abstract away?
    }

                                                //PLAY PHASE

    //terrible name
    //which way to implement?
    private boolean checkIfAllInfluenceGroupsAreFull() {
        for (Map<PlayerID, InfluenceGroup> playerInfluenceMap : influenceGroups.values()) {
            for (InfluenceGroup influenceGroup : playerInfluenceMap.values()) {
                if (!influenceGroup.isFull()) return false;
            }
        }
        return true;
//        boolean allGroupsAreFull = true;
//        influenceGroups.forEach((vote, playerMap) -> {
//            playerMap.forEach((playerID, group) -> {
//                if (!group.isFull()) allGroupsAreFull = false;
//            });
//        });
//        return allGroupsAreFull;
    }

    //use boolean? call other method? change game phase?
    boolean checkIfEndOfGame() {
        if (numPatriciansLeftToWin == 0) {
            //endGame("There are no more patrician cards to win.");
            return true;
        } else if (!getCurrentPlayer().hasAnyInfluenceCards()
                   && !getOtherPlayer().hasAnyInfluenceCards()) {
            //endGame("Neither player has any influence cards to play.");
            return true;
        } else if (checkIfAllInfluenceGroupsAreFull()
                   && !getCurrentPlayer().hasAnyInfluenceRemovingCards()
                   && !getOtherPlayer().hasAnyInfluenceRemovingCards()) {
            //endGame("All patricians' influence groups are full and no action cards can be played to change this.");
            return true;
        }
        return false;
    }

    void checkTurnConditions() {
        if (getCurrentPlayer().hasInfluenceCardsInHand()
            && (!checkIfAllInfluenceGroupsAreFull()
                || getCurrentPlayer().hasInfluenceRemovingCardsInHand() ) ) {
            //player can play I cards now (active turn)
            //if patrician groups are full, this is dependent on player playing an influence-removing card (limit player's A card play options)
        } else if (getCurrentPlayer().hasAnyInfluenceCards()
                   && (!checkIfAllInfluenceGroupsAreFull()
                       || getCurrentPlayer().hasAnyInfluenceRemovingCards() ) ) {
            //player cannot play I cards now but can play them later, so can draw cards now (passive turn)
        } else {
            //player cannot play
        }
    }

//    pseudocode for client side:
//    void playActiveTurn() {
//        playActionCard(); //optional
//        playInfluenceCard(); //1 or 2
//            //should contain checking for special VoC
//        refillHand();
//        drawVoteOfConf();
//    }

    void playActionCard(ActionCard card) {
        card.play();
        getCurrentPlayer().removeCardFromHand(card);
    }

    void playInfluenceCard(Vote vote, InfluenceCard card) {
        influenceGroups.get(vote).get(currentPlayerID).addCard(card);
        getCurrentPlayer().removeCardFromHand(card);
    }

    void drawVoteOfConf() {
        Vote vote = voteOfConfDeck.get(0).getVote();
        if (vote.getVoteType().equals(Vote.VoteType.Patrician)) {
            settleVoteOfConf(vote);
            voteOfConfDiscard.add(voteOfConfDeck.remove(0));
        } else if (vote.equals(Vote.Orgy)) {
            voteOfConfDiscard.add(voteOfConfDeck.remove(0));
        } else { //OrgyReshuffle
            while (!voteOfConfDiscard.isEmpty()) {
                voteOfConfDeck.add(voteOfConfDiscard.remove(0));
            }
            Collections.shuffle(voteOfConfDeck);
        }
    }

    Player settleVoteOfConf(Vote group) {
        PatricianGroup patricianGroup = patricianGroups.get(group);
        InfluenceGroup currPlayerGroup = influenceGroups.get(group).get(currentPlayerID);
        InfluenceGroup otherPlayerGroup = influenceGroups.get(group).get(PlayerID.getOtherPlayerID(currentPlayerID));
        currPlayerGroup.flipAllCardsUp();
        otherPlayerGroup.flipAllCardsUp();

        //determine winner
        Player winner;
        if (currPlayerGroup.compareTo(otherPlayerGroup) == 0)
            return null;
        if (currPlayerGroup.compareTo(otherPlayerGroup) > 0) {
            winner = getCurrentPlayer();
        } else {
            winner = getOtherPlayer();
        }
        winner.addWonPatrician(patricianGroup.removePatrician());
        numPatriciansLeftToWin--;

        //discard cards
        if (patricianGroup.isEmpty()) {
            //If a PatricianGroup is emptied after settling the vote, discard the InfluenceCards in each InfluenceGroup
            //and remove the VoC cards relating to the group from the game.
        } else {
            currPlayerGroup.removePhilosophers();
            otherPlayerGroup.removePhilosophers();
            if (currPlayerGroup.getCardSum() > otherPlayerGroup.getCardSum()) {
                currPlayerGroup.removeHighestCard();
                otherPlayerGroup.removeLowestCard();
            } else {
                currPlayerGroup.removeLowestCard();
                otherPlayerGroup.removeHighestCard();
            }
        }

        return winner;
    }

}