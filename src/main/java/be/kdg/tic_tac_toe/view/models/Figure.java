package be.kdg.tic_tac_toe.view.models;

import be.kdg.tic_tac_toe.model.Sort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Figure extends Canvas {

    private final int row;
    private final int column;
    private final int gridSize;
    private Sort sort;
    private final GraphicsContext gc;

    public Figure(int row, int column, Sort sort) {
        super(100, 100);
        this.column = column;
        this.row = row;
        this.gridSize = 100;

        this.sort = sort;
        this.gc = this.getGraphicsContext2D();
        gc.setLineWidth(5);
        draw();
    }

    public Figure(int row, int column){
        this(row, column, null);
    }

    private void draw() {
        if (sort == null) {
            return;
        }
        if (sort.equals(Sort.O)) {
            createCircle();
        } else if (sort.equals(Sort.X)) {
            createCross();
        }
    }

    private void createCircle() {
        gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.BLUE);
        gc.strokeOval(5, 5, this.gridSize - 10, this.gridSize - 10);
    }

    private void createCross() {
        gc.setStroke(Color.RED);
        gc.strokeLine(10, 10, this.gridSize - 10, this.gridSize - 10);
        gc.strokeLine(10, this.gridSize - 10, this.gridSize - 10, 10);
    }

    public void setSort(Sort sort) {
        this.sort = sort;
        draw();
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}
