package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.List;

class InfluenceGroup implements Comparable<InfluenceGroup> {

    private final List<InfluenceCard> cards = new ArrayList<>();
    private final Vote group;
    private int otherPlayerNumCards = 0;

    InfluenceGroup(Vote group) {
        this.group = group;
    }

    Vote getGroup() {
        return group;
    }

    int getCardSum() {
        return cards.stream().mapToInt(card -> card.getValue()).sum();
    }

    int getNumPhilosophers() {
        return (int) cards.stream().filter(card -> card.getValue() == 0).count();
    }

    //return a positive value if you win the vote of confidence
    @Override
    public int compareTo(InfluenceGroup o) {
        if (getNumPhilosophers() == o.getNumPhilosophers()) {
            return getCardSum() - o.getCardSum();
        }
        return o.getCardSum() - getCardSum();
    }

    int getNumCards() {
        return cards.size();
    }

    void addCard(InfluenceCard card) {
        //if this group's size < 5 && both groups' size < 8,
            //cards.add(card);
        //else
            //error;
            //should "both groups" condition be handled outside?
    }

    void removeCard(InfluenceCard card) {
        boolean cardRemoved = cards.remove(card);
        if (!cardRemoved) {
            //error
        }
    }

    void removeHighestCard() {
        removeCard(cards.stream().max(InfluenceCard::compareTo).get());
    }

    void removeLowestCard() {
        removeCard(cards.stream().min(InfluenceCard::compareTo).get());
    }

    void removePhilosophers() {
        cards.removeIf(card -> card.getValue() == 0);
    }

    void flipAllCardsUp() {
        cards.stream().filter(card -> !card.isFaceUp()).forEach(
            InfluenceCard::flipCardOver
        );
    }

    //num is the number of cards added to the other player's influence group (negative if removed)
    void changeOtherPlayerNumCards(int num) {
        otherPlayerNumCards += num;
    }

    boolean isFull() {
        return (cards.size() >= 5) || (cards.size() + otherPlayerNumCards >= 8);
    }

}
