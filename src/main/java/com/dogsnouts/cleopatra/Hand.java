package com.dogsnouts.cleopatra;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

class Hand {

    private final Set<PlayableCard> hand = new TreeSet<>();

    private static void doRepeatedly(int iterations, Consumer<Integer> action) { //put in a utility class
        for (int i = 0; i < iterations; i++) {
            action.accept(i);
        }
    }

    Hand() {
        fillStarterHand();
    }

    //better name? maybe "fillHandDefault"?
    void fillStarterHand() {
        doRepeatedly(5, i ->
            hand.add(new InfluenceCard(i+1))
        );
    }

    //is error handling necessary or can hand.remove(card) be used in place of this method?
    void removeCard(PlayableCard card) {
        boolean cardRemoved = hand.remove(card);
        if (!cardRemoved) {
            //error
        }
    }

    //hand size should be tested before removing card from deck
    void addCard(PlayableCard card) {
        if (hand.size() < 5) {
            hand.add(card);
        } else {
            //error
        }
    }

    Set<PlayableCard> getCards() {
        return hand;
    }

    boolean containsInfluenceCard() {
        return hand.stream().anyMatch(playableCard -> playableCard instanceof InfluenceCard);
    }

    boolean ContainsActionCard(ActionCard actionCard) {
        //
    }

}
