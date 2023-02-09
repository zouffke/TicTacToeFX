package be.kdg.tic_tac_toe.view.Home;

import be.kdg.tic_tac_toe.model.Model;
import javafx.application.Platform;


public class Presenter {

    private final View view;
    private final Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //quiting the program
        this.view.getQuit().setOnAction(actionEvent -> {
            Platform.exit();
        });
    }

    private void updateView() {
    }
}
