package com.dogsnouts.cleopatra;

import java.util.ArrayList;

public class InfluenceGroup implements Group {

    private ArrayList<InfluenceCard> cards = new ArrayList<>();
    private final GroupName group;

    public InfluenceGroup(GroupName group) {
        this.group = group;
    }

    @Override
    public GroupName getGroup() {
        return group;
    }

//    public int getNumCards() {
//        return cards.size();
//    }

    public void addCard(InfluenceCard card) {
        //if this group's size !< 5 || both groups' size !< 8,
            //error;
            //should "both groups" condition be handled outside?
        //else
            //cards.add(card);
    }

    public void removeCard(InfluenceCard card) {
        boolean cardRemoved = cards.remove(card);
        if (!cardRemoved) {
            //error
        }
    }

    public void removeHighestCard() {
        InfluenceCard highestCard = cards.get(0);
        for (InfluenceCard card : cards){
            if (card.getValue() > highestCard.getValue()){
                highestCard = card;
            }
        }
        removeCard(highestCard);
    }

    //can this and above method's code be combined into one method?
    public void removeLowestCard() {
        InfluenceCard lowestCard = cards.get(0);
        for (InfluenceCard card : cards){
            if (card.getValue() < lowestCard.getValue()){
                lowestCard = card;
            }
        }
        removeCard(lowestCard);
    }

    public void flipAllCardsUp() {
        for (InfluenceCard card : cards) {
            if (!card.isFaceUp()) {
                card.flipCardOver();
            }
        }
    }

//    public void flipCard(InfluenceCard card) {
//        card.flipCardOver();
//    }

    public int cardSum() {
        int sum = 0;
        for (InfluenceCard card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public int numPhilosophers(){
        int numPhilosophers = 0;
        for (InfluenceCard card : cards) {
            if (card.getValue() == 0){
                numPhilosophers++;
            }
        }
        return numPhilosophers;
    }

}
