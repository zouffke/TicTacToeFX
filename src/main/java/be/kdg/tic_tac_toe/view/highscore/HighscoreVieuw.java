package be.kdg.tic_tac_toe.view.highscore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class HighscoreVieuw extends VBox {

    //create vars
    private Label titel;
    private Label score1;
    private Label score2;
    private Label score3;
    private Label score4;
    private Label score5;
    private Button terug;

    //constructor die initalizeNodes en layoutNodes aanroept zodat ze gebruikt kunne worden
    public HighscoreVieuw() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        //een naam maken voor het label titel genaamd tic tac toe
        this.titel = new Label("Highscores");

        //de volgende buttons initializeren en ze een naam geven
        this.score1 = new Label("'undefined'");
        this.score2 = new Label("'undefined'");
        this.score3 = new Label("'undefined'");
        this.score4 = new Label("'undefined'");
        this.score5 = new Label("'undefined'");
        this.terug = new Button("terug");

    }

    private void layoutNodes() {
        // toevoegen van al de buttons en labels
        this.getChildren().addAll(this.titel, this.score1, this.score2, this.score3, this.score4, this.score5, this.terug);

        //positie van de buttons en labels zetten naar canter
        this.setAlignment(Pos.TOP_CENTER);

        //overal 50 pixels ruimte rond de buttons en de labels
        VBox.setMargin(this.titel, new Insets(50));
        VBox.setMargin(this.score1, new Insets(50));
        VBox.setMargin(this.score2, new Insets(50));
        VBox.setMargin(this.score3, new Insets(50));
        VBox.setMargin(this.score4, new Insets(50));
        VBox.setMargin(this.score5, new Insets(50));
        VBox.setMargin(this.terug, new Insets(50));

    }


}





