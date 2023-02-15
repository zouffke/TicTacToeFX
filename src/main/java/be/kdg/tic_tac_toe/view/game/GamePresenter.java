package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.Model;

public class GamePresenter {

    private final GameView view;
    private final Model model;

    public GamePresenter(GameView view, Model model){
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers(){

    }

    private void updateView(){

    }
}
