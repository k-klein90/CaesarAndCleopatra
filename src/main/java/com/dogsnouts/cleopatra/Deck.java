package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

abstract class Deck {

    private List<PlayableCard> deck = new ArrayList<>();

    List<PlayableCard> getDeck() {
        return deck;
    }

    static void doRepeatedly(int iterations, Consumer<Integer> op) {
        for (int i = 0; i < iterations; i++) {
            op.accept(i);
        }
    }

    PlayableCard drawCard() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        } else {
            //error
            return null;
        }
    }

    boolean isEmpty() {
        return deck.isEmpty();
    }

}
