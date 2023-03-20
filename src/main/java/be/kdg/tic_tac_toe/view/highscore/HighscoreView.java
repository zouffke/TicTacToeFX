package be.kdg.tic_tac_toe.view.highscore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class HighscoreView extends BorderPane {

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
            this.scores[i].getStyleClass().add("scores");
        }
        this.scores[0].getStyleClass().add("first");
        this.scores[1].getStyleClass().add("second");
        this.scores[2].getStyleClass().add("third");

        this.terug = new Button("terug");
        this.terug.getStyleClass().add("returnButton");

    }

    private void layoutNodes() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("vBox");
        this.setTop(this.titel);
        this.setCenter(vBox);
        this.setBottom(this.terug);

        BorderPane.setAlignment(this.titel, Pos.CENTER);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setAlignment(this.terug, Pos.CENTER);

        BorderPane.setMargin(this.terug, new Insets(0, 0, 10, 0));

        for (Label score : scores) {
            vBox.getChildren().add(score);
        }

    }

    Button getTerug() {
        return this.terug;
    }

    Label[] getScores() {
        return this.scores;
    }
}





