package be.kdg.tic_tac_toe.model;

public class Coordinaat {
    private final int x;
    private final int y;

    public Coordinaat(int y, int x) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", y, x) ;
    }
}
