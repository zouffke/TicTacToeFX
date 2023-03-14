package be.kdg.tic_tac_toe.view.players;

import be.kdg.tic_tac_toe.model.Game;
import be.kdg.tic_tac_toe.model.GameException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PlayerPresenter {
    //attributen aanmaken
    private final PlayerView view;
    private final Game model;
    private final Stage playerStage;
    private boolean namesFilled;

    //constructor waarin view en model worden aangeroepen en waar addEventHandlers en updateView worden aangeroepen
    public PlayerPresenter(PlayerView view, Game model, Stage playerStage) {
        this.view = view;
        this.model = model;
        this.playerStage = playerStage;
        this.namesFilled = false;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // als je op de button klikt dan ga je naar gameview
        this.view.getOk().setOnAction(action -> {
            try {
                // zals deze acties worden uitgevoerd/opgeroepen
                this.model.setPlayers(this.view.getChoice(), this.view.getName1().getText(), this.view.getName2().getText());
                this.namesFilled = true;

                // als iets mis ging moet je volgende exeption gooien
            } catch (GameException e) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                // de inhoud van de warning is de message
                warning.setContentText(e.getMessage());
                warning.showAndWait();
            }
            if (namesFilled) {
                // als de namen zijn ingevuld dan kan je verder gaan en sluit de stage
                this.playerStage.close();
            }
        });
    }

    private void updateView() {
    }

    public boolean isNamesFilled() {
        return namesFilled;
    }
}
