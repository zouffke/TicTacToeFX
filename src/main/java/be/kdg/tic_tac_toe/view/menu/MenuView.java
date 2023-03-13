package be.kdg.tic_tac_toe.view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
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
    // Vbox is een manier om buttons(nodes) enzo juist te plaasten
    private VBox radioButtonBox1;
    private VBox radioButtonBox2;

    public MenuView() {
        initializeNodes();
        layoutNodes();
    }


    private void initializeNodes() {
        //groepen en buttonboxen initializeren
        radioButtonBox1 = new VBox();
        radioButtonBox2 = new VBox();
        ToggleGroup group = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();

        // radiobuttons initializeren en een naam geven
        this.PvP = new RadioButton("PvP");
        this.PvP.setToggleGroup(group);

        this.PvE = new RadioButton("PvE");
        this.PvE.setToggleGroup(group);

        this.ultraNightmare = new RadioButton("Ultra Nightmare ");
        this.ultraNightmare.setToggleGroup(group);

        radioButtonBox1.getChildren().addAll(this.PvP, this.PvE, this.ultraNightmare);

        this.gamemode = new Label("Gamemode");
        this.drie = new RadioButton("3X3");
        this.drie.setToggleGroup(group2);

        this.vijf = new RadioButton("5X5");
        this.vijf.setToggleGroup(group2);

        this.zeven = new RadioButton("7X7 ");
        this.zeven.setToggleGroup(group2);
        radioButtonBox2.getChildren().addAll(this.drie, this.vijf, this.zeven);
        this.bordSize = new Label("bordSize");
        this.play = new Button("play");
        this.terug = new Button("terug");
    }

    //voegt alle vars toe aan de scene zodat je ze kan zien
    private void layoutNodes() {
        this.getChildren().addAll(this.gamemode, radioButtonBox1, this.bordSize, radioButtonBox2, this.play, this.terug);
        this.setAlignment(Pos.TOP_CENTER);
        //overal 50 pixels ruimte rond de buttons en de labels
        VBox.setMargin(this.radioButtonBox1, new Insets(50));
        VBox.setMargin(this.radioButtonBox2, new Insets(50));
        //deze code zorgt ervoor dat de radiobuttons in het midden staan, als je de breedte van de scene veranderd dan veranderen ze ook mee
        radioButtonBox1.setPadding(new Insets(0, 0, 0, 350));
        radioButtonBox2.setPadding(new Insets(0, 0, 0, 350));


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


