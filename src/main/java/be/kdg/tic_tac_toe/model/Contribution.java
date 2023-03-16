package be.kdg.tic_tac_toe.model;

/**
 * CLass that holds the information of the players and the sorts.
 */
class Contribution {
    // vars aanmaken, 2 arrays die niet kunnen veranderen
    private final Player[] PLAYERS;
    private final Sort[] SORTS;

    /**
     * Constructor for the Contribution class.
     *
     * @param name1 the name of the first player. (String)
     * @param name2 the name of the second player. (String)
     */
    // lees dit hiervoor
    Contribution(String name1, String name2) {
        //contribution voor de gamemode pvp want je geeft 2 namen mee
        //player geinitializeerd met 2 humans die allebij een naam hebben
        PLAYERS = new Player[]{new Human(name1), new Human(name2)};
        // er zijn 2 soorten dus daarom de 2
        SORTS = new Sort[2];
    }

    /**
     * Constructor for the Contribution class.
     *
     * @param name the name of the first player. (String)
     */
    Contribution(String name, boolean ai) {
        //contribution voor pve of ultra nightmare want de ene is een naam en de andere is npc ai
        //maar in npc staat een functie om te kijken of de makkelijke of de slimme is
        PLAYERS = new Player[]{new Human(name), new NPC(ai)};
        SORTS = new Sort[2];
    }

    /**
     * Function that returns the sort of the player.
     *
     * @param index the index of the player. (int) (1-2)
     * @return the sort of the player. (Sort)
     */
    String getSort(int index) {
        // geeft een string terug met X of O en de index de min 1 staat er om de opties 1 en 2 te kunne gebruiken
        //met de -1 zet die het terug om naar 0 of 1
        return String.format("%s", SORTS[index - 1]);
    }

    /**
     * Function that returns the name of the player.
     *
     * @param index the index of the player. (int) (1-2)
     * @return the name of the player. (String)
     */
    String getName(int index) {
        // om de naam van de gekozen speler te geven
        // de -1 weer om 1 en 2 om te zetten naar de plaats in de array van 0 en 1
        return PLAYERS[index - 1].toString();
    }

    /**
     * Function that returns the player.
     *
     * @param index the index of the player. (int) (1-2)
     * @return the player. (Player)
     */
    Player getPlayer(int index) {
        //de speler die gekozen is, -1 om de plaats in de array te bepalen, teruggeven
        return PLAYERS[index - 1];
    }

    /**
     * Function to randomly set the sorts of the players.
     * Is used when a new game is started.
     */
    void setSorts() {
        //de sortkeuze 0 kiest een random, X of O, de sort 1 kiest dan de andere
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.oppositSort(SORTS[0]);
    }
}
