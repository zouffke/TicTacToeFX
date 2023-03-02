package be.kdg.tic_tac_toe.model;

public class Contribution {

    private final Player[] PLAYERS;
    private final Sort[] SORTS;

    public Contribution(String name1, String name2){
        PLAYERS = new Player[]{new Human(name1), new Human(name2)};
        SORTS = new Sort[2];
       //random sort toekennen.
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.oppositSort(SORTS[0]);
    }
    public Contribution(String name){
        PLAYERS = new Player[]{new Human(name), new NPC()};
        SORTS = new Sort[2];
    }

    public String getSort(int index) {
        return String.format("%s", SORTS[index - 1]);
    }

    public String getName(int index){
        return PLAYERS[index - 1].getNAME();
    }

    public Player getPlayer(int index){
        return PLAYERS[index - 1];
    }

    public void setSorts(){
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.oppositSort(SORTS[0]);
    }
}
