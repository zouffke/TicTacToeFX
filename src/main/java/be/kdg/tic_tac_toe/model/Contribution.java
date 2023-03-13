package be.kdg.tic_tac_toe.model;

class Contribution {

    private final Player[] PLAYERS;
    private final Sort[] SORTS;

    Contribution(String name1, String name2) {
        PLAYERS = new Player[]{new Human(name1), new Human(name2)};
        SORTS = new Sort[2];
        //random sort toekennen.
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.oppositSort(SORTS[0]);
    }

    Contribution(String name) {
        PLAYERS = new Player[]{new Human(name), new NPC()};
        SORTS = new Sort[2];
    }

    String getSort(int index) {
        return String.format("%s", SORTS[index - 1]);
    }

    String getName(int index) {
        return PLAYERS[index - 1].getNAME();
    }

    Player getPlayer(int index) {
        return PLAYERS[index - 1];
    }

    void setSorts() {
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.oppositSort(SORTS[0]);
    }
}
