package be.kdg.tic_tac_toe.view.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    //create vars
    private Label titel;
    private Button play;
    private Button highscore;
    private Button quit;

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
    }

    private void layoutNodes() {
        // toevoegen van al de buttons en labels
        this.getChildren().addAll(this.titel, this.play, this.highscore, this.quit);

        //positie van de buttons en labels zetten naar canter
        this.setAlignment(Pos.TOP_CENTER);

        //overal 50 pixels ruimte rond de buttons en de labels
        VBox.setMargin(this.play, new Insets(50));
        VBox.setMargin(this.highscore, new Insets(50));
        VBox.setMargin(this.quit, new Insets(50));
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
