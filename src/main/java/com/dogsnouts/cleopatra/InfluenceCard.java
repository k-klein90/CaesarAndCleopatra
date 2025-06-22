package com.dogsnouts.cleopatra;

class InfluenceCard extends PlayableCard {

    private final int value; //implement value as enum?
    private boolean isFaceUp = false;

    InfluenceCard(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(PlayableCard o) {
        if (o instanceof ActionCard) {
            return -10; //should a less arbitrary value be used?
        }
        if (o instanceof InfluenceCard ic) {
            return value - ic.value;
        }
        throw new IllegalArgumentException("Unsupported card type"); //necessary?
    }

    int getValue() {
        return value;
    }

    boolean isFaceUp() {
        return isFaceUp;
    }

    void flipCardOver() {
        isFaceUp = !isFaceUp;
    }

}
