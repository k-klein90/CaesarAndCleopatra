package com.dogsnouts.cleopatra;

import java.util.ArrayList;
import java.util.List;

class PatricianGroup {

    private final List<PatricianCard> cards = new ArrayList<>();
    private final Vote group;

    PatricianGroup(Vote group) {
        this.group = group;
        switch (group) {
            case Aedilis:
                cards.add(new PatricianCard(group, "Commisarius Lexus"));
                cards.add(new PatricianCard(group, "Glutaeus Musculus"));
                cards.add(new PatricianCard(group, "Stephanus Derricus"));
                break;
            case Censor:
                cards.add(new PatricianCard(group, "Beatus Usus"));
                cards.add(new PatricianCard(group, "Humanus Sextor"));
                cards.add(new PatricianCard(group, "Lustus Frustus"));
                break;
            case Praetor:
                cards.add(new PatricianCard(group, "Advocatus Diabolus"));
                cards.add(new PatricianCard(group, "Justitianus Anus"));
                cards.add(new PatricianCard(group, "Livius Redeflus"));
                cards.add(new PatricianCard(group, "Pontius Paragraphus"));
                cards.add(new PatricianCard(group, "Oculus Myopus"));
                break;
            case Quaestor:
                cards.add(new PatricianCard(group, "Fabius Fiscus"));
                cards.add(new PatricianCard(group, "Nobilius Incorruptus"));
                cards.add(new PatricianCard(group, "Octavius Luxus"));
                cards.add(new PatricianCard(group, "Quintius Sestercus"));
                cards.add(new PatricianCard(group, "Theophilus Cassenstus"));
                break;
            case Senator:
                cards.add(new PatricianCard(group, "Brutus Judascus"));
                cards.add(new PatricianCard(group, "Aurelius Clerus"));
                cards.add(new PatricianCard(group, "Gaius Bellicus"));
                cards.add(new PatricianCard(group, "Scipio Africolus"));
                cards.add(new PatricianCard(group, "Sulla Camorra"));
                break;
        }
    }

    Vote getGroup() {
        return group;
    }

    boolean isEmpty() {
        return cards.isEmpty();
    }

}
