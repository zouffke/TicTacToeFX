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
        this.view.getOk().setOnAction(action -> {
            try {
                this.model.setPlayers(this.view.getChoice(), this.view.getName1().getText(), this.view.getName2().getText());
                this.namesFilled = true;
            } catch (GameException e) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setContentText(e.getMessage());
                warning.showAndWait();
            }
            if (namesFilled) {
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
