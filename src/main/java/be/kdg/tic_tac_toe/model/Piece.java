package be.kdg.tic_tac_toe.model;

import java.util.Objects;

public class Piece {

    private final Sort SORT;
    private final int X;
    private final int Y;

    Piece(Sort sort, int y, int x) {
        this.X = x;
        this.Y = y;
        this.SORT = sort;
    }

    public boolean equalsSort(Sort sort) {
        if (sort == null) {
            return false;
        } else {
            return this.getSORT().equals(sort);
        }
    }

    Sort getSORT() {
        return SORT;
    }    @Override
    public int hashCode() {
        return Objects.hash(SORT, X, Y);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        if (this.hashCode() == o.hashCode()) {
            return X == piece.X && Y == piece.Y && SORT == piece.SORT;
        }
        return false;
    }


    @Override
    public String toString() {
        return String.format("%s", this.getSORT());
    }
}
