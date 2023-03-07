package be.kdg.tic_tac_toe.view.highscore;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import javafx.application.Platform;

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

    }

    private void updateView() {
    }
}


