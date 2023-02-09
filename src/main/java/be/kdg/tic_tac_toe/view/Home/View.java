package be.kdg.tic_tac_toe.view.Home;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class View extends VBox {

    private Label titel;
    private Button play;
    private Button highscore;
    private Button quit;

    public View(){
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
    }
}
