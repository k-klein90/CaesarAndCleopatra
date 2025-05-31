package com.dogsnouts.cleopatra;

public class InfluenceCard extends PlayableCard {

    private final int value;
    private boolean isFaceUp = false;

    public InfluenceCard(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flipCardOver(){
        isFaceUp = !isFaceUp;
    }

}
