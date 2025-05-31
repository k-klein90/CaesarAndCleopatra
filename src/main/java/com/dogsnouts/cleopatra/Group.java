package com.dogsnouts.cleopatra;

public interface Group {

    enum GroupName {
        AEDILIS,
        CENSOR,
        PRAETOR,
        QUAESTOR,
        SENATOR
    }

    GroupName getGroup();

}
