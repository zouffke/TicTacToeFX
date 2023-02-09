package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Model;

public class MenuPresenter {
    private final MenuView view;
    private final Model model;
    public MenuPresenter(MenuView view, Model model) {
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
