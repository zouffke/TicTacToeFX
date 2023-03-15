package be.kdg.tic_tac_toe.model;

public class Player {
    //variabele die niet kan veranderen genaamd NAME
    private final String NAME;
//Constructor voor de var
    Player(String name) {
        this.NAME = name;
    }

    @Override
    public String toString() {
        //return de var naam
        return this.NAME;
    }

}
