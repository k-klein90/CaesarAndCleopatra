package com.dogsnouts.cleopatra;

import java.util.ArrayList;

public class Player {

    ActionDeck actionDeck = new ActionDeck();
    InfluenceDeck influenceDeck = new InfluenceDeck();
    SecretBonusCard secretBonus;
    Hand hand = new Hand();
    ArrayList<PatricianCard> wonPatricians = new ArrayList<>();

    public Player(){
        //secret bonus
    }

    public boolean canDrawInfluenceCards(){
        return influenceDeck.deck.isEmpty();
    }

    private boolean canPlayInfluenceCard(){
        //if, for each patrician group, either 5 I cards on player's side or 8 I cards total, return false; else true
        return false;
    }

    //subpar name; misses nuance
    private boolean canPlayActionCard(){
        //if, for all A cards in hand, none can allow player to play an I card, return false; else true
        return false;
    }

    //incorporate canPlay() methods into this one method?
    public boolean canTakeTurn(){
        return (!canPlayInfluenceCard() && !canPlayActionCard() && !actionDeck.isEmpty());
    }

    public void takeTurn(){
        if (canTakeTurn()){
            if (canPlayInfluenceCard() && hand.containsInfluenceCard()) {
                //make both active and passive turns available
                //(go to UI, which calls either takeActive- or takePassiveTurn() depending on user input)
            } else {
                //tell player they either cannot play I card or have no I cards to play;
                //make only passive turn available
                //(go to UI, which must call takePassiveTurn())
            }
        } else {
            //tell player they cannot play;
            //skip turn
        }
    }

    public void takeActiveTurn(){

    }

    public void takePassiveTurn(){

    }

    public void drawActionCard(){

    }

}
