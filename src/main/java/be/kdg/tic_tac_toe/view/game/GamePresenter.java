package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.*;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.util.Optional;

public class GamePresenter {

    private final GameView view;
    private final Game model;
    private final Board board;
    private boolean humanTurn;

    public GamePresenter(GameView view, Game model) {
        this.view = view;
        this.model = model;
        this.board = model.getBoard();

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
                    if (humanTurn) {
                        try {
                            model.place(figure.getColumn(), figure.getRow());
                            updateView();
                            humanTurn = false;
                        } catch (BoardException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
                        }
                    }
                });
            }
        }
    }

    private void updateView() {
        for (int i = 0; i < this.board.getPieces().length; i++) {
            for (int j = 0; j < this.board.getPieces()[i].length; j++) {
                if (this.board.getPieces()[i][j] != null) {
                    if (this.board.getPieces()[i][j].equalsSort(Sort.O)) {
                        this.view.getFigures()[i][j].setSort(Sort.O);
                    } else if (this.board.getPieces()[i][j].equalsSort(Sort.X)) {
                        this.view.getFigures()[i][j].setSort(Sort.X);
                    }
                }
            }
        }

        //wait for the screen to render before calling the NOC function
        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                callNPC();
                humanTurn = true;
                return null;
            }
        };
        new Thread(task).start();
    }

    private void callNPC() {
        if (this.model.getCurrentPlayer() instanceof NPC) {
            this.model.npcMove();
            updateView();
        }
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
