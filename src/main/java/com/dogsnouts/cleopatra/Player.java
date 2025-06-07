package com.dogsnouts.cleopatra;

import java.util.*;

class Player {

    private final ActionDeck actionDeck = new ActionDeck();
    private final InfluenceDeck influenceDeck = new InfluenceDeck();
    private final SecretBonusCard secretBonus;
    private final Hand hand = new Hand();
    private final Set<PatricianCard> wonPatricians = new TreeSet<>();

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

    boolean hasInfluenceCardInHand() {
        return hand.containsInfluenceCard();
    }

    //adequate name? returns whether the player has any more I cards to play
    boolean canPlayInfluenceCards() {
        if (hasInfluenceCardInHand()
            || canDrawInfluenceCards()) {
                return true;
        }
        return false;
    }

    //subpar name; misses nuance (can play an action card that allows more I cards to be played)
    boolean canPlayActionCards() {
        if (hasActionCardInHand(ActionCard.CardType.Assassination)
            || hasActionCardInHand(ActionCard.CardType.WrathOfGod)
            || canDrawActionCard(ActionCard.CardType.Assassination)
            || canDrawActionCard(ActionCard.CardType.WrathOfGod)) {
                return true;
        }
        return false;
    }

    void removeCardFromHand(PlayableCard card) {
        hand.removeCard(card);
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
