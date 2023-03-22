package be.kdg.tic_tac_toe.view.previous_games;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;

public class PreviousGamesView extends BorderPane {

    private Label title;
    private MenuButton games;
    private Button back;


    public PreviousGamesView() {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.title = new Label("Previous Games");
        this.games = new MenuButton("Games");
        this.back = new Button("Back");
    }

    private void layoutNodes() {
        this.setTop(this.title);
        BorderPane.setAlignment(this.title, Pos.CENTER);

        this.setCenter(this.games);
        BorderPane.setAlignment(this.games, Pos.CENTER);

        this.setBottom(this.back);
        BorderPane.setAlignment(this.back, Pos.CENTER);
        this.back.getStyleClass().add("returnButton");
        BorderPane.setMargin(this.back, new Insets(0, 0, 10, 0));
    }

    Button getBack(){
        return this.back;
    }

    MenuButton getGames(){
        return this.games;
    }
}
