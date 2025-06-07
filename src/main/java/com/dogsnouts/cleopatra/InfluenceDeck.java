package com.dogsnouts.cleopatra;

import java.util.List;
import java.util.Collections;

class InfluenceDeck extends Deck {

    InfluenceDeck() {
        List<PlayableCard> deck = getDeck();

        doRepeatedly(5, i ->
            deck.add(new InfluenceCard(i+1)) //Influence cards of value 1 through 5
        );
        doRepeatedly(2, i ->
            deck.add(new InfluenceCard(0)) //Philosopher cards
        );

        Collections.shuffle(deck);
    }

}
