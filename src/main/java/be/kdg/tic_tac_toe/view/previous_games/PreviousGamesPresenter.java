package be.kdg.tic_tac_toe.view.previous_games;

import be.kdg.tic_tac_toe.model.GamesSave;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;
import javafx.scene.control.MenuItem;

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
            homeView.getScene().getStylesheets().remove("file:resources/stylesheets/settings.css");
            homeView.getScene().getStylesheets().add("file:resources/stylesheets/home.css");
        });
    }

    private void updateView() {
    }

    private void initGames() {
        if (this.model.getSize() == 0) {
            this.view.getGames().getItems().add(new MenuItem("No games to display"));
        } else {
            for (int i = 0; i < this.model.getSize(); i++) {
                this.view.getGames().getItems().add(new MenuItem(this.model.getGame(i)));
            }
        }
    }
}
