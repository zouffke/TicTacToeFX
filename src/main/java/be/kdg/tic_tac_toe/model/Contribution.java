package be.kdg.tic_tac_toe.model;

/**
 * CLass that holds the information of the players and the sorts.
 */
class Contribution {

    private final Player[] PLAYERS;
    private final Sort[] SORTS;

    /**
     * Constructor for the Contribution class.
     *
     * @param name1 the name of the first player. (String)
     * @param name2 the name of the second player. (String)
     */
    Contribution(String name1, String name2) {
        PLAYERS = new Player[]{new Human(name1), new Human(name2)};
        SORTS = new Sort[2];
    }

    /**
     * Constructor for the Contribution class.
     *
     * @param name the name of the first player. (String)
     */
    Contribution(String name) {
        PLAYERS = new Player[]{new Human(name), new NPC()};
        SORTS = new Sort[2];
    }

    /**
     * Function that returns the sort of the player.
     *
     * @param index the index of the player. (int) (1-2)
     * @return the sort of the player. (Sort)
     */
    String getSort(int index) {
        return String.format("%s", SORTS[index - 1]);
    }

    /**
     * Function that returns the name of the player.
     *
     * @param index the index of the player. (int) (1-2)
     * @return the name of the player. (String)
     */
    String getName(int index) {
        return PLAYERS[index - 1].getNAME();
    }

    /**
     * Function that returns the player.
     *
     * @param index the index of the player. (int) (1-2)
     * @return the player. (Player)
     */
    Player getPlayer(int index) {
        return PLAYERS[index - 1];
    }

    /**
     * Function to randomly set the sorts of the players.
     * Is used when a new game is started.
     */
    void setSorts() {
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.oppositSort(SORTS[0]);
    }
}
