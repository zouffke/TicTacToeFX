package be.kdg.tic_tac_toe.view.highscore;

import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;

public class HighscorePresenter {
    private final HighscoreView view;

    public HighscorePresenter(HighscoreView view) {
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getTerug().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            new HomePresenter(homeView);

            this.view.getScene().setRoot(homeView);
        });
    }

    private void updateView() {
    }
}


