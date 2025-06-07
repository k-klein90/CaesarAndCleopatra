package com.dogsnouts.cleopatra;

class ActionCard extends PlayableCard {

    enum CardType {
        Assassination,
        Castling,
        Scout,
        Spy,
        Veto,
        WrathOfGod
    }

    private final CardType cardType;

    ActionCard(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public int compareTo(PlayableCard o) {
        if (o instanceof InfluenceCard) {
            return 10; //should a less arbitrary value be used?
        }
        if (o instanceof ActionCard ac) {
            return cardType.ordinal() - ac.cardType.ordinal();
        }
        throw new IllegalArgumentException("Unsupported card type");
    }

    void play() {
        switch (cardType) {
            case Assassination -> ; //discard one of opponent's face-up I cards (if face-up I card present)
            case Castling -> ;  //redistribute own I cards from 2 I groups and place face-down (if groups have at least
                                //1 card total; group size limits must be obeyed)
            case Scout -> ; //flip face-up all I cards in one of opponent's I groups (if face-down I card present)
            case Spy -> ; //search opponent's hand and discard one card; opponent refills hand
            case Veto -> ;  //negate opponent's action card when they play it; refill own hand (tricky to implement;
                            //interrupt turn to give opponent chance to respond?)
            case WrathOfGod -> ; //discard all I cards on both sides of one PatricianGroup (if I card present)
        }
    }

}
