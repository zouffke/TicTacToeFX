package be.kdg.tic_tac_toe.view.application;
import be.kdg.tic_tac_toe.model.Model;


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
