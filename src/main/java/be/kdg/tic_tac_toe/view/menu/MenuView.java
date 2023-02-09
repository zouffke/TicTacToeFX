package be.kdg.tic_tac_toe.view.menu;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
    private Label gamemode;
    private Button PvP;
    private Button PvE;
    private Button ultraNightmare;
    private Label bordSize;
    private Button drie;
    private Button vijf;
    private Button zeven;
    private Button play;
    private Button terug;

    public MenuView(){
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        this.gamemode = new Label("Gamemode");
        this.PvP = new Button("PvP");
        this.PvE = new Button("PvE");
    }

    private void layoutNodes() {
        this.getChildren().addAll(this.gamemode, this.PvP, this.PvE);
    }

    Label getGamemode() {
        return gamemode;
    }

    Button getPvP() {
        return PvP;
    }

    Button getPvE() {
        return PvE;
    }

    Button getUltraNightmare() {
        return ultraNightmare;
    }

    Label getBordSize() {
        return bordSize;
    }

    Button getDrie() {
        return drie;
    }

    Button getVijf() {
        return vijf;
    }

    Button getZeven() {
        return zeven;
    }

    Button getPlay() {
        return play;
    }

    Button getTerug() {
        return terug;
    }
}
