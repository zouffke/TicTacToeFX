package be.kdg.tic_tac_toe.view.game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameView extends GridPane {

    private Label titel;
    private Button[][] fields;

    public GameView(int size){
        initializeNodes(size);
        layoutNodes();
    }

    private void initializeNodes(int size){
        this.titel = new Label("Tic-Tac-Toe");

        fields = new Button[size][size];

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new Button();
            }

        }
    }

    private void layoutNodes(){
        this.add(this.titel, 1, 0);
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                this.add(fields[i][j], i, j + 1);
            }
        }
        this.setGridLinesVisible(true);
    }
}
