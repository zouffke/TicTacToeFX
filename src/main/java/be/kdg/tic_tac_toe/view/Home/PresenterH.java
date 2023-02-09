package be.kdg.tic_tac_toe.view.Home;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.PresenterM;
import be.kdg.tic_tac_toe.view.menu.ViewM;
import javafx.application.Platform;


public class PresenterH {

    private final ViewH viewH;
    private final Model model;

    public PresenterH(ViewH view, Model model) {
        this.viewH = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //quiting the program
        this.viewH.getQuit().setOnAction(actionEvent -> {
            Platform.exit();
        });

        //going to menu screen
        this.viewH.getPlay().setOnAction(actionEvent -> {
            ViewM viewM = new ViewM();
            PresenterM presenter = new PresenterM(viewM, model);

            viewM.getScene().setRoot(viewM);
            viewM.getScene().getWindow().sizeToScene();
        });
    }

    private void updateView() {
    }
}
