package be.kdg.tic_tac_toe.view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuView extends BorderPane {
    // create vars
    private Label gamemode;
    private RadioButton PvP;
    private RadioButton PvE;
    private RadioButton ultraNightmare;
    private Label bordSize;
    private RadioButton drie;
    private RadioButton vijf;
    private RadioButton zeven;
    private Button play;
    private Button terug;

    public MenuView() {
        initializeNodes();
        layoutNodes();
    }


    private void initializeNodes() {
        //groepen en buttonboxen initializeren
        ToggleGroup group = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();

        // radiobuttons initializeren en een naam geven
        this.PvP = new RadioButton("PvP");
        this.PvP.setToggleGroup(group);
        this.PvP.getStyleClass().remove("radio-button");
        this.PvP.getStyleClass().addAll("PvP", "toggle-button");
        this.PvE = new RadioButton("PvE");
        this.PvE.setToggleGroup(group);
        this.PvE.getStyleClass().remove("radio-button");
        this.PvE.getStyleClass().addAll("PvE", "toggle-button");

        this.ultraNightmare = new RadioButton("Ultra Nightmare ");
        this.ultraNightmare.setToggleGroup(group);
        this.ultraNightmare.getStyleClass().remove("radio-button");
        this.ultraNightmare.getStyleClass().addAll("nightmare", "toggle-button");


        this.gamemode = new Label("Gamemode");

        this.drie = new RadioButton("3X3");
        this.drie.setToggleGroup(group2);
        this.drie.getStyleClass().remove("radio-button");
        this.drie.getStyleClass().addAll("toggle-button", "sizeButtons");

        this.vijf = new RadioButton("5X5");
        this.vijf.setToggleGroup(group2);
        this.vijf.getStyleClass().remove("radio-button");
        this.vijf.getStyleClass().addAll("toggle-button", "sizeButtons");

        this.zeven = new RadioButton("7X7 ");
        this.zeven.setToggleGroup(group2);
        this.zeven.getStyleClass().remove("radio-button");
        this.zeven.getStyleClass().addAll("toggle-button", "sizeButtons");


        this.bordSize = new Label("bordSize");
        this.play = new Button("play");
        this.terug = new Button("terug");
        this.terug.getStyleClass().add("returnButton");
    }

    //voegt alle vars toe aan de scene zodat je ze kan zien
    private void layoutNodes() {
        VBox mainField = new VBox();
        VBox radioButtonBox1 = new VBox();
        VBox radioButtonBox2 = new VBox();

        this.setCenter(mainField);
        this.setBottom(this.terug);

        BorderPane.setAlignment(this.terug, Pos.CENTER);
        BorderPane.setAlignment(mainField, Pos.CENTER);

        BorderPane.setMargin(this.terug, new Insets(0, 0, 10, 0));

        mainField.getChildren().addAll(this.gamemode, radioButtonBox1, this.bordSize, radioButtonBox2, this.play);
        mainField.setAlignment(Pos.TOP_CENTER);

        //Takes care of the player options
        HBox line1 = new HBox();
        line1.getChildren().addAll(this.PvP, this.PvE);
        line1.getStyleClass().add("players");

        radioButtonBox1.getChildren().addAll(line1, this.ultraNightmare);
        radioButtonBox1.getStyleClass().add("players");

        //takes care of the board options
        HBox line2 = new HBox();
        line2.getChildren().addAll(this.drie, this.vijf);
        line2.getStyleClass().add("board");

        radioButtonBox2.getChildren().addAll(line2, this.zeven);
        radioButtonBox2.getStyleClass().add("board");

        this.play.getStyleClass().add("playButton");
    }

    //maak getters voor de vars zodat je ze kan gebruiken in de presenter
    RadioButton getPvP() {
        return this.PvP;
    }

    RadioButton getPvE() {
        return this.PvE;
    }

    RadioButton getUltraNightmare() {
        return this.ultraNightmare;
    }

    RadioButton getDrie() {
        return this.drie;
    }

    RadioButton getVijf() {
        return this.vijf;
    }

    RadioButton getZeven() {
        return this.zeven;
    }

    Button getPlay() {
        return this.play;
    }

    Button getTerug() {
        return this.terug;
    }
}


