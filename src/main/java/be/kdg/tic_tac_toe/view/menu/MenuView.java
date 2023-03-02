package be.kdg.tic_tac_toe.view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
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

    private VBox radioButtonBox1;
    private VBox radioButtonBox2;

    public MenuView(){
        initializeNodes();
        layoutNodes();
    }


    private void initializeNodes() {
        radioButtonBox1 = new VBox();
        radioButtonBox2 = new VBox();
        ToggleGroup group = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();

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

    private void layoutNodes() {
        this.getChildren().addAll( this.gamemode, radioButtonBox1,this.bordSize,radioButtonBox2, this.play, this.terug);
        this.setAlignment(Pos.TOP_CENTER);

        VBox.setMargin(this.radioButtonBox1, new Insets(50));
        VBox.setMargin(this.radioButtonBox2, new Insets(50));
        radioButtonBox1.setPadding(new Insets(0, 0, 0, 350));
        radioButtonBox2.setPadding(new Insets(0, 0, 0, 350));


    }

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


