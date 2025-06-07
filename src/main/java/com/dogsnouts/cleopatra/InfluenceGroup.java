package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.List;

class InfluenceGroup implements Comparable<InfluenceGroup> {

    private final List<InfluenceCard> cards = new ArrayList<>();
    private final Vote group;

    InfluenceGroup(Vote group) {
        this.group = group;
    }

    Vote getGroup() {
        return group;
    }

    int getCardSum() {
        int sum = 0;
        for (InfluenceCard card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    int getNumPhilosophers() {
        int numPhilosophers = 0;
        for (InfluenceCard card : cards) {
            if (card.getValue() == 0) {
                numPhilosophers++;
            }
        }
        return numPhilosophers;
    }

    //return a positive value if you win the vote of confidence
    @Override
    public int compareTo(InfluenceGroup o) {
        if (getNumPhilosophers() == o.getNumPhilosophers()) {
            return getCardSum() - o.getCardSum();
        } else {
            return o.getCardSum() - getCardSum();
        }
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
        InfluenceCard highestCard = cards.get(0);
        for (InfluenceCard card : cards) {
            if (card.getValue() > highestCard.getValue()) {
                highestCard = card;
            }
        }
        removeCard(highestCard);
    }

    //can this and above method be combined into one?
    void removeLowestCard() {
        InfluenceCard lowestCard = cards.get(0);
        for (InfluenceCard card : cards) {
            if (card.getValue() < lowestCard.getValue()) {
                lowestCard = card;
            }
        }
        removeCard(lowestCard);
    }

    void flipAllCardsUp() {
        for (InfluenceCard card : cards) {
            if (!card.isFaceUp()) {
                card.flipCardOver();
            }
        }
    }

//    void flipCard(InfluenceCard card) {
//        card.flipCardOver();
//    }

    int cardSum() {
        int sum = 0;
        for (InfluenceCard card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    int numPhilosophers() {
        int numPhilosophers = 0;
        for (InfluenceCard card : cards) {
            if (card.getValue() == 0) {
                numPhilosophers++;
            }
        }
        return numPhilosophers;
    }

}
