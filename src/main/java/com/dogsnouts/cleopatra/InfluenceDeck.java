package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.Collections;

public class InfluenceDeck {

    ArrayList<InfluenceCard> deck = new ArrayList<>();

    public InfluenceDeck() {
        for (int i = 0; i < 6; i++) {
            deck.add(new InfluenceCard(1));
            deck.add(new InfluenceCard(2));
            deck.add(new InfluenceCard(3));
            deck.add(new InfluenceCard(4));
            deck.add(new InfluenceCard(5));
        }
        for (int i = 0; i < 2; i++) {
            deck.add(new InfluenceCard(0)); //Philosopher cards
        }

        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    InfluenceCard drawCard() {
        if (!isEmpty()) {
            return deck.remove(0);
        } else {
            //error
        }
    }

}
