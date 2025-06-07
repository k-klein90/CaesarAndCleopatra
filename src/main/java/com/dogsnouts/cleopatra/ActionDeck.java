package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.dogsnouts.cleopatra.ActionCard.CardType.*;

class ActionDeck extends Deck {

    ActionDeck() {
        List<PlayableCard> deck = getDeck();

        doRepeatedly(4, i ->
            deck.add(new ActionCard(Assassination))
        );
        doRepeatedly(2, i-> {
            deck.add(new ActionCard(Spy));
            deck.add(new ActionCard(Castling));
            deck.add(new ActionCard(Scout));
            deck.add(new ActionCard(Veto));
        });
        deck.add(new ActionCard(WrathOfGod));

        Collections.shuffle(deck);
    }

}
