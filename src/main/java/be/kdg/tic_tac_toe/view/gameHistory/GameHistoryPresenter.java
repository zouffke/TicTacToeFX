package be.kdg.tic_tac_toe.view.gameHistory;

import be.kdg.tic_tac_toe.model.GameSaveObjects;
import be.kdg.tic_tac_toe.model.GamesSave;
import be.kdg.tic_tac_toe.model.SaveFileException;
import be.kdg.tic_tac_toe.view.previous_games.PreviousGamesPresenter;
import be.kdg.tic_tac_toe.view.previous_games.PreviousGamesView;
import javafx.scene.control.Alert;

import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/y HH:mm");

        this.view.getMoves().add(String.format("Game %d was played on %s using a %s board", this.model.getGameNumber(), formatter.format(this.model.getDate()), this.model.getBoard()));
        this.view.getMoves().add(String.format("Player 1: %s, Played as %s", this.model.getPlayer1().toString(), this.model.getSort1().toString()));
        this.view.getMoves().add("Player 2: " + this.model.getPlayer2().toString() + ", Played as " + this.model.getSort2().toString());
        this.view.getMoves().add("The winner of the game was: " + this.model.getWinnerPlayer().toString());
        this.view.getMoves().add("The Moves played were:");

        for (int i = 0; i < this.model.getMoveAmount(); i++){
            this.view.getMoves().add("\t" + this.model.getMove(i));
        }
    }
}
