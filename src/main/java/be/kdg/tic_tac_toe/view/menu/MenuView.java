package be.kdg.tic_tac_toe.view.menu;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

public class MenuView extends BorderPane{
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
    private void initializeNodes() {
        this.gamemode = new Label("Gamemode");
        this.PvP = new Button("PvP");
        this.PvE = new Button("PvE");
    }

}
