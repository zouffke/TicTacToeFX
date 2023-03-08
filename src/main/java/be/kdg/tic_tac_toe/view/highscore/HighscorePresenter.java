package be.kdg.tic_tac_toe.view.highscore;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;

public class HighscorePresenter {
    private final HighscoreVieuw view;

    private final Model model;

    public HighscorePresenter(HighscoreVieuw view, Model model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getTerug().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            new HomePresenter(homeView, model);

            this.view.getScene().setRoot(homeView);
        });
    }

    private void updateView() {
    }
}


