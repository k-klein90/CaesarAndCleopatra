package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class GameBoard {

    //HashMap foo = new LinkedHashMap
    private ArrayList<PatricianGroup> patricianGroups = new ArrayList<>();
    private ArrayList<InfluenceGroup> cleopatraGroups = new ArrayList<>();
    private ArrayList<InfluenceGroup> caesarGroups = new ArrayList<>();
    private ArrayList<VoteOfConfCard> voteOfConfDeck = new ArrayList<>();
    private ArrayList<VoteOfConfCard> voteOfConfDiscard = new ArrayList<>();

    public GameBoard() {
        String[] groups = {"Aedilis", "Censor", "Praetor", "Quaestor", "Senator"};
        for (Group.GroupName group : Group.GroupName.values()) {
            patricianGroups.add(new PatricianGroup(group));
            cleopatraGroups.add(new InfluenceGroup(group));
            caesarGroups.add(new InfluenceGroup(group));
        }

//        String[] voteTypes = {"Aedilis", "Censor", "Praetor", "Quaestor", "Senator", "Orgy", "OrgyReshuffle"};
        for (Group.GroupName group : Group.GroupName.values()) {
            voteOfConfDeck.add(new VoteOfConfCard(group));
        }
        voteOfConfDeck.add(new VoteOfConfCard());
        Collections.shuffle(voteOfConfDeck);
    }

    private Group findGroup(ArrayList<Group> groups, Group.GroupName groupName) {
        for (Group group : groups) {
            if (group.getGroup().equals(groupName)) {
                return group;
            }
        }
        //error
    }

    public void drawVoteOfConf(){
        VoteOfConfCard card = voteOfConfDeck.getFirst();
        Group.GroupName voteType = card.getVoteType();
        if (voteType.equals("OrgyReshuffle")){
            while (!voteOfConfDiscard.isEmpty()){
                voteOfConfDeck.add(voteOfConfDiscard.removeFirst());
            }
            Collections.shuffle(voteOfConfDeck);
        } else if (voteType.equals("Orgy")){
            voteOfConfDiscard.add(voteOfConfDeck.removeFirst());
        } else {
            if (findGroup(patricianGroups, voteType).isEmpty()){
                voteOfConfDiscard.removeFirst();
                drawVoteOfConf();
                //return;
            } else {
                settleVoteOfConf(voteType);
                voteOfConfDiscard.add(voteOfConfDeck.removeFirst());
            }
        }
    }

    public void settleVoteOfConf(Group.GroupName group) {
        InfluenceGroup cleoGroup = findGroup(cleopatraGroups, group);
        InfluenceGroup caesarGroup = findGroup(caesarGroups, group);
        PatricianGroup patricianGroup = findGroup(patricianGroups, group);
        cleoGroup.flipAllCardsUp();
        caesarGroup.flipAllCardsUp();
        boolean cleoWon;

        if (cleoGroup.cardSum() == caesarGroup.cardSum()) {
            ;   //do nothing
        } else if (cleoGroup.cardSum() > caesarGroup.cardSum()) {
            if (cleoGroup.numPhilosophers() == caesarGroup.numPhilosophers()) {
                cleoWon = true;
            } else {
                cleoWon = false;
            }
            //cleo discards high, caes discards low
        } else {
            if (cleoGroup.numPhilosophers() == caesarGroup.numPhilosophers()) {
                cleoWon = false;
            } else {
                cleoWon = true;
            }
            //caes discards high, cleo discards low
        }
    }

}
