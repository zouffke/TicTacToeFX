package be.kdg.tic_tac_toe.view.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class HomeView extends BorderPane {

    //create vars
    private Label titel;
    private Button play;
    private Button highscore;
    private Button quit;
    private Button games;
    private Button options;

    //constructor die initalizeNodes en layoutNodes aanroept zodat ze gebruikt kunne worden
    public HomeView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        //een naam maken voor het label titel genaamd tic tac toe
        this.titel = new Label("Tic Tac Toe");

        //de volgende buttons initializeren en ze een naam geven
        this.play = new Button("Play");
        this.highscore = new Button("Highscore");
        this.quit = new Button("Quit");
        this.quit.getStyleClass().add("returnButton");
        this.games = new Button("Previous games");
        this.options = new Button("Options");
    }

    private void layoutNodes() {
        this.setBottom(this.quit);
        BorderPane.setAlignment(this.quit, Pos.CENTER);
        BorderPane.setMargin(this.quit, new Insets(0, 0, 10, 0));

        HBox hBox = new HBox();
        this.setCenter(hBox);

        hBox.getStyleClass().add("Box");

        VBox leftButtons = new VBox();
        leftButtons.getStyleClass().add("Box");
        VBox rightButtons = new VBox();
        rightButtons.getStyleClass().add("Box");
        hBox.getChildren().addAll(leftButtons, rightButtons);

        leftButtons.getChildren().addAll(this.play, this.games);
        rightButtons.getChildren().addAll(this.options, this.highscore);

        this.setTop(this.titel);
        BorderPane.setAlignment(this.titel, Pos.CENTER);
    }

    Button getPlay() {
        //playfunctie getten
        return this.play;
    }

    Button getHighscore() {
        //button van highscore getten
        return this.highscore;
    }

    Button getQuit() {
        // de quit button getten
        return this.quit;
    }
}
