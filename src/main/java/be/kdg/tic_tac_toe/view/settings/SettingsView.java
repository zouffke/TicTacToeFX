package be.kdg.tic_tac_toe.view.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SettingsView extends BorderPane {

    private Label title;
    private Button clearPlayers;
    private Button clearGames;
    private Button back;

    public SettingsView(){
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.title = new Label("Settings");
        this.clearPlayers = new Button("Clear players");
        this.clearGames = new Button("Clear games");
        this.back = new Button("Back");
    }

    private void layoutNodes() {
        this.setTop(this.title);
        BorderPane.setAlignment(this.title, Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getStyleClass().add("hBox");
        this.setCenter(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        hBox.getChildren().addAll(this.clearPlayers, this.clearGames);

        this.setBottom(this.back);
        this.back.getStyleClass().add("returnButton");
        BorderPane.setAlignment(this.back, Pos.CENTER);
        BorderPane.setMargin(this.back, new Insets(0, 0, 10, 0));

    }

    Button getBack(){
        return this.back;
    }

    Button getClearPlayers(){
        return this.clearPlayers;
    }

    Button getClearGames(){
        return this.clearGames;
    }
}
