package be.kdg.tic_tac_toe.view.previous_games;

import be.kdg.tic_tac_toe.model.GameSaveObjects;
import be.kdg.tic_tac_toe.model.GamesSave;
import be.kdg.tic_tac_toe.view.gameHistory.GameHistoryPresenter;
import be.kdg.tic_tac_toe.view.gameHistory.GameHistoryView;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;

import java.time.format.DateTimeFormatter;

public class PreviousGamesPresenter {
    private final GamesSave model;
    private final PreviousGamesView view;

    public PreviousGamesPresenter(GamesSave model, PreviousGamesView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();
        this.initGames();
    }

    private void addEventHandlers() {
        this.view.getBack().setOnAction(action -> {
            HomeView homeView = new HomeView();
            this.view.getScene().setRoot(homeView);
            new HomePresenter(homeView);
            homeView.getScene().getStylesheets().remove("file:resources/stylesheets/previousGames.css");
            homeView.getScene().getStylesheets().add("file:resources/stylesheets/home.css");
        });

        this.view.getList().setOnMouseClicked(action -> {
            if (this.model.getSize() != 0) {
                int index = this.view.getList().getSelectionModel().getSelectedIndex();
                if (index != -1) {
                    GameHistoryView gameHistoryView = new GameHistoryView();
                    this.view.getScene().setRoot(gameHistoryView);
                    new GameHistoryPresenter(this.model.getGame(index), gameHistoryView);
                }
            }
        });
    }

    private void updateView() {
    }

    private void initGames() {
        if (this.model.getSize() == 0) {
            this.view.getGames().add("No games played yet");
        } else {
            for (int i = 0; i < this.model.getSize(); i++) {
                GameSaveObjects game = this.model.getGame(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/y HH:mm");
                String date = formatter.format(game.getDate());
                this.view.getGames().add(String.format("Game %d %s %s", i + 1, " ".repeat(153), date));
            }
        }
    }
}
