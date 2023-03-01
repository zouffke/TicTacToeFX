package be.kdg.tic_tac_toe.view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        RadioButton radioButton1 = new RadioButton("PvP");
        radioButton1.setToggleGroup(group);

        RadioButton radioButton2 = new RadioButton("PvE");
        radioButton2.setToggleGroup(group);

        RadioButton radioButton3 = new RadioButton("Ultra Nightmare ");
        radioButton3.setToggleGroup(group);

        radioButtonBox1.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        this.gamemode = new Label("Gamemode");
        RadioButton radioButton4 = new RadioButton("3X3");
        radioButton4.setToggleGroup(group2);

        RadioButton radioButton5 = new RadioButton("5X5");
        radioButton5.setToggleGroup(group2);

        RadioButton radioButton6 = new RadioButton("7X7 ");
        radioButton6.setToggleGroup(group2);
        radioButtonBox2.getChildren().addAll(radioButton4, radioButton5, radioButton6);
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

    Label getGamemode() {
        return gamemode;
    }

    RadioButton getPvP() {
        return PvP;
    }

    RadioButton getPvE() {
        return PvE;
    }

    RadioButton getUltraNightmare() {
        return ultraNightmare;
    }

    Label getBordSize() {
        return bordSize;
    }

    RadioButton getDrie() {
        return drie;
    }

    RadioButton getVijf() {
        return vijf;
    }

    RadioButton getZeven() {
        return zeven;
    }

    Button getPlay() {
        return play;
    }

    Button getTerug() {
        return terug;
    }
}


