package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.FigureType;
import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.util.Optional;

public class GamePresenter {

    private final GameView view;

    //TODO add the game logics to the model folder and assign the head logic to the presenter
    private final Model model;

    public GamePresenter(GameView view, Model model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //quit menu
        this.view.getExit().setOnAction(actionEvent -> exitPopup());

        //return menu
        this.view.getBack().setOnAction(actionEvent -> returnPopup());

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

    private void returnPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Return?");
        alert.setContentText("Are you sure you want to return to the menu?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                MenuView menuView = new MenuView();
                Model model = new Model();
                new MenuPresenter(menuView, model);

                this.view.getScene().setRoot(menuView);
                menuView.getScene().getWindow().setHeight(700);
                menuView.getScene().getWindow().setWidth(900);
                if (this.view.getPlayer().isAutoPlay()) {
                    this.view.getPlayer().stop();
                }
            }
        }
    }
}
