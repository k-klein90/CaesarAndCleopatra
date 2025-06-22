package com.dogsnouts.cleopatra;

import java.util.*;

class Player {

    private final ActionDeck actionDeck = new ActionDeck();
    private final InfluenceDeck influenceDeck = new InfluenceDeck();
    private final SecretBonusCard secretBonus;
    private final Hand hand = new Hand();
    private final Set<PatricianCard> wonPatricians = new TreeSet<>();
    private int numInfluenceRemovingCards = ActionCard.CardType.Assassination.getCardCount()
                                            + ActionCard.CardType.WrathOfGod.getCardCount();

    //select secret bonus card randomly
    Player() {
        List<Vote> secretBonusVotes = List.of(Vote.Praetor, Vote.Quaestor, Vote.Senator);
        secretBonus = new SecretBonusCard(secretBonusVotes.get( new Random().nextInt(secretBonusVotes.size()) ));
    }

    ActionDeck getActionDeck() {
        return actionDeck;
    }

    InfluenceDeck getInfluenceDeck() {
        return influenceDeck;
    }

    SecretBonusCard getSecretBonus() {
        return secretBonus;
    }

    Hand getHand() {
        return hand;
    }

    Set<PatricianCard> getWonPatricians() {
        return wonPatricians;
    }

    private boolean canDrawInfluenceCards() {
        return influenceDeck.isEmpty();
    }

    private boolean canDrawActionCards() {
        return actionDeck.isEmpty();
    }

    boolean hasInfluenceCardsInHand() {
        return hand.containsInfluenceCard();
    }

    boolean hasInfluenceRemovingCardsInHand() {
        return hand.containsActionCard(ActionCard.CardType.Assassination)
            || hand.containsActionCard(ActionCard.CardType.WrathOfGod);
    }

    //adequate name? returns whether the player has any more I cards to play
    boolean hasAnyInfluenceCards() {
        if (hasInfluenceCardsInHand()
            || canDrawInfluenceCards()) {
                return true;
        }
        return false;
    }

    boolean hasAnyInfluenceRemovingCards() {
        return (numInfluenceRemovingCards != 0);
    }

    void removeCardFromHand(PlayableCard card) {
        hand.removeCard(card);
        if (card instanceof ActionCard
            && ( ((ActionCard) card).getCardType().equals(ActionCard.CardType.Assassination)
                 || ((ActionCard) card).getCardType().equals(ActionCard.CardType.WrathOfGod) )) {
            numInfluenceRemovingCards--;
        }
    }

    void drawActionCard() {
        if (canDrawActionCards()) {
            hand.addCard(actionDeck.drawCard());
        } else {
            //error
        }
    }

    void drawInfluenceCard() {
        if (canDrawInfluenceCards()) {
            hand.addCard(influenceDeck.drawCard());
        } else {
            //error
        }
    }

    void addWonPatrician(PatricianCard patrician) {
        wonPatricians.add(patrician);
    }

}
