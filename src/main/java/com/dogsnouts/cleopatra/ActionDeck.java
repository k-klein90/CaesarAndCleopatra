package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ActionDeck {

    ArrayList<ActionCard> deck = new ArrayList<>();

    private static void doRepeatedly(int iterations, Consumer<Integer> op) {
        for (int i = 0; i < iterations; i++) {
            op.accept(i);
        }
    }

    ActionDeck() {
        doRepeatedly(4, i -> {
            deck.add(new AssassinationCard());
        });
        doRepeatedly(2, i-> {
            deck.add(new SpyCard());
            deck.add(new CastlingCard());
            deck.add(new ScoutCard());
            deck.add(new VetoCard());
        });

        deck.add(new WrathOfGodCard());

        Collections.shuffle(deck);
    }

    ActionCard drawCard() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        } else {
            //error
        }
    }

    boolean isEmpty() {
        return deck.isEmpty();
    }

}
