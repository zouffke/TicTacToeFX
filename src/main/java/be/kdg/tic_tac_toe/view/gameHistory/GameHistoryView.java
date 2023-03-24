package be.kdg.tic_tac_toe.view.gameHistory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class GameHistoryView extends BorderPane {

    private Label title;
    private Button back;
    private ListView<String> list;
    private ObservableList<String> moves;


    public GameHistoryView() {
        this.initializeNodes();
        this.layoutNodes();
    }

    private void initializeNodes() {
        this.title = new Label();

        this.list = new ListView<>();
        this.moves = FXCollections.observableArrayList();

        this.back = new Button("Back");
    }

    private void layoutNodes() {
        this.setTop(this.title);
        BorderPane.setAlignment(this.title, Pos.CENTER);

        this.setCenter(this.list);
        this.list.setItems(this.moves);
        BorderPane.setMargin(this.list, new Insets(25));
        this.list.setMouseTransparent(true);
        this.list.setFocusTraversable(false);

        this.setBottom(this.back);
        BorderPane.setAlignment(this.back, Pos.CENTER);
        this.back.getStyleClass().add("returnButton");
        BorderPane.setMargin(this.back, new Insets(0, 0, 10, 0));
    }

    Button getBack(){
        return this.back;
    }

    Label getTitle() {
        return this.title;
    }

    ObservableList<String> getMoves(){
        return this.moves;
    }
}
