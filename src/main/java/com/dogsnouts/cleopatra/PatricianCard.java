package com.dogsnouts.cleopatra;

class PatricianCard implements Comparable<PatricianCard> {

    private final Vote group;
    private final String name;

    PatricianCard(Vote group, String name) {
        this.group = group;
        this.name = name;
    }

    Vote getGroup() {
        return group;
    }

    String getName() {
        return name;
    }

    @Override
    public int compareTo(PatricianCard o) {
        return group.ordinal() - o.group.ordinal();
    }

}
