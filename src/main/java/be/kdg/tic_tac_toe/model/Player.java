package be.kdg.tic_tac_toe.model;

public class Player {
    private final String NAME;

    Player(String name) {
        this.NAME = name;
    }

    public String getNAME() {
        return NAME.substring(0, 1).toUpperCase() + NAME.substring(1).toLowerCase();
    }

}
