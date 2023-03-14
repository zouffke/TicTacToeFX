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
        // labels maken voor de players en initializeren
        this.player1 = new Label("Player 1");
        this.player2 = new Label("Player 2");
        // een nieuw tekstfield maken om iets in te vullen
        this.name1 = new TextField();
        if (choice != 1){
            //als de keuze bij gamemode niet 1  was (pvp) dan word er een nieuwe textfield aangemaakt en ingevuld met 'bot'
            this.name2 = new TextField("Bot");
            // je kan dit tekstveld niet aanpass,
            this.name2.setEditable(false);
        }  else {
            //als er wel 1 werd aangeduid komt er gewoon een 2de tekstveld bij
            this.name2 = new TextField();
        }
        //de maximum wijdte van de tekstvelden
        this.name1.setMaxWidth(250);
        this.name2.setMaxWidth(250);
        this.ok = new Button("OK");
    }

    private void layoutNodes() {
        //padding instellen, 5 vanboven, 15 rechts, 0 vanonder en 15 van links
        this.setPadding(new Insets(5, 15, 0, 15));
        //alle variabelen toevoegen aan de scene
        this.getChildren().addAll(this.player1, this.name1, this.player2, this.name2, this.ok);
        //op alle variabelen/tekstvelden en padding zetten van 5 aan alle kanten
        this.player1.setPadding(new Insets(5));
        this.player2.setPadding(new Insets(5));
        this.name1.setPadding(new Insets(5));
        this.name2.setPadding(new Insets(5));
        // de padding voor de ok button, 20 boven, 5 rechts en onder en 200 van links
        VBox.setMargin(this.ok, new Insets(20, 5, 5, 200));
    }
    // getters maken voor de vars
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
