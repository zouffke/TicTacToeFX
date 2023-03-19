package be.kdg.tic_tac_toe.view.highscore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class HighscoreView extends VBox {

    //create vars
    private Label titel;
    private Label[] scores;
    private Button terug;

    //constructor die initalizeNodes en layoutNodes aanroept zodat ze gebruikt kunne worden
    public HighscoreView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        this.scores = new Label[5];
        //een naam maken voor het label titel genaamd tic tac toe
        this.titel = new Label("Highscores");

        //de volgende buttons initializeren en ze een naam geven
        for (int i = 0; i < this.scores.length; i++) {
            this.scores[i] = new Label();
        }
        this.terug = new Button("terug");

    }

    private void layoutNodes() {
        // toevoegen van al de buttons en labels
        this.getChildren().add(this.titel);
        for (Label score : scores) {
            this.getChildren().add(score);
        }
        this.getChildren().add(this.terug);

        //positie van de buttons en labels zetten naar canter
        this.setAlignment(Pos.TOP_CENTER);

        //overal 50 pixels ruimte rond de buttons en de labels
        VBox.setMargin(this.titel, new Insets(50));
        for (Label score : scores) {
            VBox.setMargin(score, new Insets(50));
        }
        VBox.setMargin(this.terug, new Insets(50));

    }

    Button getTerug() {
        return this.terug;
    }

    Label[] getScores() {
        return this.scores;
    }
}





