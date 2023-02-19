package be.kdg.tic_tac_toe.view.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Figure_O extends Circle {

    private final int row;
    private final int column;

    public Figure_O(int row, int column) {
        super(10, 10, 20);

        this.row = row;
        this.column = column;

        this.setFill(null);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(3);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
