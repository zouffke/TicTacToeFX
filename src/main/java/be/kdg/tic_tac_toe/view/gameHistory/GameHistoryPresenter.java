package be.kdg.tic_tac_toe.view.gameHistory;

import be.kdg.tic_tac_toe.model.GameSaveObjects;
import be.kdg.tic_tac_toe.model.GamesSave;
import be.kdg.tic_tac_toe.model.SaveFileException;
import be.kdg.tic_tac_toe.view.previous_games.PreviousGamesPresenter;
import be.kdg.tic_tac_toe.view.previous_games.PreviousGamesView;
import javafx.scene.control.Alert;

public class GameHistoryPresenter {
    private final GameSaveObjects model;
    private final GameHistoryView view;

    public GameHistoryPresenter(GameSaveObjects model, GameHistoryView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBack().setOnAction(action -> {
            try {
                PreviousGamesView previousGamesView = new PreviousGamesView();
                this.view.getScene().setRoot(previousGamesView);
                new PreviousGamesPresenter(new GamesSave(), previousGamesView);
            } catch (SaveFileException e){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText(String.format("Sorry, it seems like something went wrong.%nPlease try again later%n%n(Error: %s)", e.getMessage()));
                error.show();
            }
        });
    }

    private void updateView() {
        this.view.getTitle().setText(String.format("Game %d", this.model.getGameNumber()));

        this.view.getMoves().add(this.model.getMoves());
    }
}
