package be.kdg.tic_tac_toe.view.models;

import be.kdg.tic_tac_toe.model.Sort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Figure extends Canvas {
// vars aanmaken
    private final int row;
    private final int column;
    private final int gridSize;
    //instantie van de enum sort (ctrl klik op Sort om er naartoe te gaan) dit zijn de X en de o
    private Sort sort;
    //variabel om de x en de o te kunne tekenen
    private final GraphicsContext gc;

    //constructor en variabelen initializeren
    public Figure(int row, int column, Sort sort) {
        //een canvas maken van 100 op 100
        super(100, 100);
        this.column = column;
        this.row = row;
        //het grid is 100 groot
        this.gridSize = 100;

        this.sort = sort;
        this.gc = this.getGraphicsContext2D();
        // de grote van de lijn is 5 dik
        gc.setLineWidth(5);
        draw();
    }

    public Figure(int row, int column){
        // de rows en columns worden null ingevul din het begin
        this(row, column, null);
    }

    private void draw() {
        if (sort == null) {
            //als sort gelijk is aan nul moet je niks teruggeven
            return;
        }
        if (sort.equals(Sort.O)) {
            // als de sort gelijk is aan O moet je een cirkel tekenen
            createCircle();
        } else if (sort.equals(Sort.X)) {
            // als de sort gelijk is aan X mote je een kruis tekenen
            createCross();
        }
    }

    private void createCircle() {
        // de binnekant van de cirkel is transparant
        gc.setFill(Color.TRANSPARENT);
        //de kleur van de cirkel is blauw
        gc.setStroke(Color.BLUE);
        // hij begint te tekenen in de hoek en tekent via rechts de cirkel
        gc.strokeOval(5, 5, this.gridSize - 10, this.gridSize - 10);
    }

    private void createCross() {
        // de kleur van het kruis is rood
        gc.setStroke(Color.RED);
        // de ene lijn begint van linksboven en gaat naar rechts onder
        gc.strokeLine(10, 10, this.gridSize - 10, this.gridSize - 10);
        // de andere lijn begint links onder en gaat naar rechtsboven
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
