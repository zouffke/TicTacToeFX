package be.kdg.tic_tac_toe.view.models;

import be.kdg.tic_tac_toe.model.FigureType;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Figure extends Canvas {

    private final int row;
    private final int column;
    private final int gridSize;
    private FigureType figureType;
    private final GraphicsContext gc;

    public Figure(int row, int column, FigureType figureType) {
        super(100, 100);
        this.column = column;
        this.row = row;
        this.gridSize = 100;
        this.figureType = figureType;
        this.gc = this.getGraphicsContext2D();
        gc.setLineWidth(5);
        draw();
    }

    public Figure(int row, int column){
        this(row, column, null);
    }

    private void draw() {
        if (figureType == null) {
            return;
        }
        if (figureType.equals(FigureType.CIRCLE)) {
            createCircle();
        } else if (figureType.equals(FigureType.CROSS)) {
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

    public void setFigureType(FigureType figureType) {
        this.figureType = figureType;
        draw();
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
