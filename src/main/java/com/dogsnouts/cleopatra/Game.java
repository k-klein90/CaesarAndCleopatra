package com.dogsnouts.cleopatra;

import java.util.*;

class Game {

    private final Map<PlayerID, Player> players = Map.of(PlayerID.Cleopatra, new Player(),
                                                         PlayerID.Caesar, new Player());
    private final Map<Vote, Map<PlayerID, InfluenceGroup>> influenceGroups = new LinkedHashMap<>();
    private final Map<Vote, PatricianGroup> patricianGroups = new LinkedHashMap<>();
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

    //preparation phase
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

    //play phase
    //is name adequate? checks end-of-game conditions and which actions player can perform
    void checkTurnConditions() {
        if (!getCurrentPlayer().canPlayInfluenceCards()
            && !getOtherPlayer().canPlayInfluenceCards()) {
                //endGame("Neither player has any influence cards to play."); //create method for this
        } else if (!getCurrentPlayer().canPlayActionCards()
                   && !getOtherPlayer().canPlayActionCards()) {
            //endGame("All patrician groups are full and no action cards can be played to change this.");
        } else {
            if (getCurrentPlayer().hasInfluenceCardInHand()) {
                //player can play active or passive turn
            } else if (getCurrentPlayer().canPlayInfluenceCards()
                       || getCurrentPlayer().canPlayActionCards()){
                //player can only play passive turn
            } else {
                //player cannot play
            }
        }
    }

    private void playInfluenceCard(Vote vote, InfluenceCard card) {
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
        } else {
            while (!voteOfConfDiscard.isEmpty()) {
                voteOfConfDeck.add(voteOfConfDiscard.remove(0));
            }
            Collections.shuffle(voteOfConfDeck);
        }
    }

    void settleVoteOfConf(Vote group) {
        InfluenceGroup currPlayerGroup = influenceGroups.get(group).get(currentPlayerID);
        InfluenceGroup otherPlayerGroup = influenceGroups.get(group).get(PlayerID.getOtherPlayerID(currentPlayerID));
        PatricianGroup patricianGroup = patricianGroups.get(group);
        currPlayerGroup.flipAllCardsUp();
        otherPlayerGroup.flipAllCardsUp();
        boolean cleoWon;

        if (currPlayerGroup.cardSum() == otherPlayerGroup.cardSum()) {
            ;   //do nothing
        } else if (currPlayerGroup.cardSum() > otherPlayerGroup.cardSum()) {
            if (currPlayerGroup.numPhilosophers() == otherPlayerGroup.numPhilosophers()) {
                cleoWon = true;
            } else {
                cleoWon = false;
            }
            //cleo discards high, caes discards low
        } else {
            if (currPlayerGroup.numPhilosophers() == otherPlayerGroup.numPhilosophers()) {
                cleoWon = false;
            } else {
                cleoWon = true;
            }
            //caes discards high, cleo discards low
        }

        if (patricianGroup.isEmpty()) {
            //If a PatricianGroup is emptied after settling the vote, discard the InfluenceCards in each InfluenceGroup
            //and remove the VoC cards relating to the group from the game.
        }
    }

}