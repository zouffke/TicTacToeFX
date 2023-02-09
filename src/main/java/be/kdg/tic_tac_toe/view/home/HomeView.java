package be.kdg.tic_tac_toe.view.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    private Label titel;
    private Button play;
    private Button highscore;
    private Button quit;

    public HomeView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        this.titel = new Label("Tic Tac Toe");
        this.titel.setStyle("-fx-focus-color: transparent;");

        this.play = new Button("Play");

        this.highscore = new Button("Highscore");

        this.quit = new Button("Quit");
    }

    private void layoutNodes() {
        this.getChildren().addAll(this.titel, this.play, this.highscore, this.quit);
        this.setAlignment(Pos.TOP_CENTER);

        VBox.setMargin(this.play, new Insets(50));
        VBox.setMargin(this.highscore, new Insets(50));
        VBox.setMargin(this.quit, new Insets(50));
    }

    Label getTitel() {
        return titel;
    }

    Button getPlay() {
        return play;
    }

    Button getHighscore() {
        return highscore;
    }

    Button getQuit() {
        return quit;
    }
}
