package com.dogsnouts.cleopatra;

abstract class PlayableCard implements Comparable<PlayableCard> {

    @Override
    public abstract int compareTo(PlayableCard o);

    /*
    can be drawn from deck
    can be held in hand
    can be discarded
     */

}
