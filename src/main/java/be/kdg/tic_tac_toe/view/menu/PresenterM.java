package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Model;

public class PresenterM {
    private final ViewM viewM;
    private final Model model;
    public PresenterM(ViewM view, Model model) {
        this.viewM = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers(){
    }

    private void updateView(){
    }
}
