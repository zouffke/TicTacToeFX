package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.View;

public class Presenter {
    private final View view;
    private final Model model;
    public Presenter(View view, Model model) {
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
