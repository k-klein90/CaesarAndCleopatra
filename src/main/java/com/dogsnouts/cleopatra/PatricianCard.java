package com.dogsnouts.cleopatra;

public class PatricianCard {

    private final Group.GroupName group;
    private final String name;

    public PatricianCard(Group.GroupName group, String name) {
        this.group = group;
        this.name = name;
    }

    public Group.GroupName getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

}
