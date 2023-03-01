package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.FigureType;
import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class GamePresenter {

    private final GameView view;
    private final Model model;

    public GamePresenter(GameView view, Model model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //quit menu
        this.view.getExit().setOnAction(actionEvent -> {
            exitPopup();
        });

        //game actions
        for (Figure[] rows : this.view.getFigures()) {
            for (Figure figure : rows) {
                figure.setOnMouseClicked(event -> {
                    figure.setFigureType(FigureType.CROSS);
                    updateView();
                });
            }
        }
    }

    private void updateView() {
    }

    private void exitPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit?");
        alert.setContentText("Are you sure you want to quit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }
}
