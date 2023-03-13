package be.kdg.tic_tac_toe.view.players;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlayerView extends VBox {

    private final int choice;
    //create vars
    private Label player1;
    private Label player2;
    private TextField name2;
    private TextField name1;
    private Button ok;

    //constructor die initalizeNodes en layoutNodes aanroept zodat ze gebruikt kunnen worden
    public PlayerView(int choice) {
        this.choice = choice;

        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        this.player1 = new Label("Player 1");
        this.player2 = new Label("Player 2");
        this.name1 = new TextField();
        if (choice != 1) {
            this.name2 = new TextField("Bot");
            this.name2.setEditable(false);
        } else {
            this.name2 = new TextField();
        }
        this.name1.setMaxWidth(250);
        this.name2.setMaxWidth(250);
        this.ok = new Button("OK");
    }

    private void layoutNodes() {
        this.setPadding(new Insets(5, 15, 0, 15));
        this.getChildren().addAll(this.player1, this.name1, this.player2, this.name2, this.ok);
        this.player1.setPadding(new Insets(5));
        this.player2.setPadding(new Insets(5));
        this.name1.setPadding(new Insets(5));
        this.name2.setPadding(new Insets(5));
        VBox.setMargin(this.ok, new Insets(20, 5, 5, 200));
    }

    TextField getName1() {
        return name1;
    }

    TextField getName2() {
        return name2;
    }

    Button getOk() {
        return this.ok;
    }

    int getChoice() {
        return this.choice;
    }
}
