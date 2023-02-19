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

    public Figure(int row, int column){
        super(20, 20);
        this.column = column;
        this.row = row;
    }

    private void createCircle(){
        Circle circle = new Circle(10, 10, 10);

        circle.setFill(null);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);
    }

    private void createCross(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);

        gc.strokeLine(0, 0, 20, 20);
        gc.strokeLine(0, 20, 20, 0);
    }

    public void setFigureType(FigureType figureType){

        if (figureType.equals(FigureType.CIRCLE)){
            createCircle();
        } else if (figureType.equals(FigureType.CROSS)) {
            createCross();
        }
    }
}
