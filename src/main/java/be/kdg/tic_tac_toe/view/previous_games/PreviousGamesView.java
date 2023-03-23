package be.kdg.tic_tac_toe.view.previous_games;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class PreviousGamesView extends BorderPane {

    private Label title;
    private ListView<String> list;
    private ObservableList<String> games;
    private Button back;


    public PreviousGamesView() {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.title = new Label("Previous Games");
        this.list = new ListView<>();
        this.games = FXCollections.observableArrayList();
        this.back = new Button("Back");
    }

    private void layoutNodes() {
        this.setTop(this.title);
        BorderPane.setAlignment(this.title, Pos.CENTER);

        this.setCenter(this.list);
        BorderPane.setAlignment(this.list, Pos.CENTER);
        this.list.setItems(this.games);
        BorderPane.setMargin(this.list, new Insets(10));

        this.setBottom(this.back);
        BorderPane.setAlignment(this.back, Pos.CENTER);
        this.back.getStyleClass().add("returnButton");
        BorderPane.setMargin(this.back, new Insets(0, 0, 10, 0));
    }

    Button getBack(){
        return this.back;
    }

    ObservableList<String> getGames(){
        return this.games;
    }
}
