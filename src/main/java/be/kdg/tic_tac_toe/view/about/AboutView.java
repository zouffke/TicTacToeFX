package be.kdg.tic_tac_toe.view.about;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class AboutView extends BorderPane {
    private Label regels;

    public AboutView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        //een label met de speluitleg
        this.regels = new Label("""
                1. Het spel wordt gespeeld op een spelbord van 3 rijen op 3 kolommen, dat initieel leeg is.
                2. Het spel heeft 2 spelers; X en O .
                3. De eerste speler is de speler die met X speelt.
                4. Wanneer een speler 3 opeenvolgende vakjes heeft (verticaal, horizontaal, diagonaal), is deze gewonnen en stopt het spel.""");
    }

    private void layoutNodes() {
        this.setCenter(regels);
    }
}

