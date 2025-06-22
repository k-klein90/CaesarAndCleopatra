package com.dogsnouts.cleopatra;

import java.util.Collections;
import java.util.List;

import static com.dogsnouts.cleopatra.ActionCard.CardType.*;

class ActionDeck extends Deck {

    ActionDeck() {
        List<PlayableCard> deck = getDeck();
        for (ActionCard.CardType cardType : ActionCard.CardType.values()) {
            for (int i = 0; i < cardType.getCardCount(); i++) {
                deck.add(new ActionCard(cardType));
            }
        }

        Collections.shuffle(deck);
    }

//    boolean containsCard(ActionCard.CardType cardType) {
//        return getDeck().stream().anyMatch(card ->
//            ((ActionCard)card).getCardType() == cardType
//        );
//    }
//
}
