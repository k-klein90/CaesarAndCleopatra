package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

public class Hand {

    Set<PlayableCard> hand = new TreeSet<>();

    private static void doRepeatedly(int iterations, Consumer<Integer> action) {
        for (int i = 0; i < iterations; i++) {
            action.accept(i);
        }
    }

    public Hand() {
        doRepeatedly(5, i ->
            hand.add(new InfluenceCard(i))
        );
    }

    void removeCard(PlayableCard card) {
        boolean cardRemoved = hand.remove(card);
        if (!cardRemoved) {
            //error
        }
    }

    void addCard(PlayableCard card) {
        if (hand.size() < 5){
            hand.add(card);
        } else {
            //error
        }
    }

    ArrayList<PlayableCard> getCards() {
        return hand;
    }

    boolean containsInfluenceCard(){
        for (PlayableCard card : hand) {
            if (card instanceof InfluenceCard) {
                return true;
            }
        }
        return false;
    }

    void playInfluenceCard(InfluenceCard card, InfluenceGroup group) {
        group.addCard(card);
    }

    void playActionCard(ActionCard card) {
        card.play();
        removeCard(card);
    }

}
