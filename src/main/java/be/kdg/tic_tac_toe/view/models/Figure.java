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
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        draw();
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

    private void createSquare() {
        gc.strokeLine(gridSize, gridSize, 0, 0);
    }

    private void createCircle() {
        gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.BLUE);
        gc.strokeOval(5, 5, this.gridSize - 10, this.gridSize - 10);
    }

    private void createCross() {
        gc.strokeLine(0, 0, this.gridSize, this.gridSize);
        gc.strokeLine(0, this.gridSize, this.gridSize, 0);
    }

    public void setFigureType(FigureType figureType) {
        this.figureType = figureType;
        draw();
    }

    public FigureType getFigureType() {
        return figureType;
    }
}
