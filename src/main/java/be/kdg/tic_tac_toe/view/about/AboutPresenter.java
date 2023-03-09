package be.kdg.tic_tac_toe.view.about;


import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.game.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutPresenter {
    private final AboutView view;
    private final Model model;

    public AboutPresenter(AboutView view, Model model) {
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
