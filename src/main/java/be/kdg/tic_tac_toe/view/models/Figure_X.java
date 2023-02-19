package be.kdg.tic_tac_toe.view.models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Figure_X extends Canvas {

    private final int column;
    private final int row;

    public Figure_X(int row, int column) {
        super(20, 20);

        this.row = row;
        this.column = column;

        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);

        gc.strokeLine(0, 0, 20, 20);
        gc.strokeLine(0, 20, 20, 0);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
