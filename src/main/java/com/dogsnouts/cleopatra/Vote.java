package com.dogsnouts.cleopatra;

enum Vote {
    Aedilis(VoteType.Patrician),
    Censor(VoteType.Patrician),
    Praetor(VoteType.Patrician),
    Quaestor(VoteType.Patrician),
    Senator(VoteType.Patrician),
    Orgy(VoteType.NotPatrician),
    OrgyReshuffle(VoteType.NotPatrician);

    private final VoteType voteType;

    Vote(VoteType voteType) {
        this.voteType = voteType;
    }

    VoteType getVoteType() {
        return voteType;
    }

    enum VoteType {
        Patrician,
        NotPatrician
    }
}