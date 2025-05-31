package com.dogsnouts.cleopatra;

public class VoteOfConfCard {

    private Enum voteType;

    public enum NonGroupVoteType {
        ORGY,
        ORGY_RESHUFFLE
    }

    public VoteOfConfCard(Group.GroupName voteType) {
        this.voteType = voteType;
    }

    public Enum getVoteType() {
        return voteType;
    }

}
