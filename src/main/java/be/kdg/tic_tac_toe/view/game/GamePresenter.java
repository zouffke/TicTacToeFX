package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.*;
import be.kdg.tic_tac_toe.view.about.AboutPresenter;
import be.kdg.tic_tac_toe.view.about.AboutView;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class GamePresenter {
    private final GameView view;
    private final Game model;
    private final Board board;
    private boolean humanTurn;
    private boolean gameOver;

    public GamePresenter(GameView view, Game model) {
        this.view = view;
        this.model = model;
        this.board = model.getBoard();
        this.gameOver = false;

        this.view.getName1().setText(this.model.getPlayer1());
        this.view.getName2().setText(this.model.getPlayer2());

        this.addEventHandlers();
        this.updateView();
        this.addWindowEventHandlers();
    }

    private void addWindowEventHandlers() {
        //TODO fix this
        //view.getScene().getWindow().setOnCloseRequest(event -> exitPopup());
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
                    if (!gameOver) {
                        if (humanTurn) {
                            try {
                                this.model.place(figure.getColumn(), figure.getRow());
                                this.humanTurn = false;
                                updateView();
                            } catch (GameException e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText(e.getMessage());
                                alert.showAndWait();
                            }
                        }
                    }
                });
            }
        }

        view.getRules().setOnAction(event -> {
            AboutView aboutView = new AboutView();
            new AboutPresenter(aboutView);
            Stage aboutStage = new Stage();
            aboutStage.initOwner(view.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(aboutView));
            aboutStage.sizeToScene();
            aboutStage.setResizable(false);
            aboutStage.setX(view.getScene().getWindow().getX() + 100);
            aboutStage.setY(view.getScene().getWindow().getY() + 100);
            aboutStage.showAndWait();
        });
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

        if (!gameOver) {
            if (!humanTurn) {
                //wait for the screen to render before calling the NPC function
                Task<Void> task = new Task<>() {
                    @Override
                    public Void call() {
                        callNPC();
                        return null;
                    }
                };
                new Thread(task).start();
            }
        }
        setGameOver();
    }

    private void callNPC() {
        if (this.model.getCurrentPlayer() instanceof NPC) {
            this.model.npcMove();
            updateView();
        }
    }

    private void setGameOver() {
        if (this.model.winCheck()) {
            gameOver = true;
            this.model.addScore(false, this.model.getCurrentPlayer());
            this.model.saveGameProgress(false, this.model.getCurrentPlayer());
            gameEndPopup("Win", String.format("%s has won\nDo you want to play again?", this.model.getCurrentPlayer().toString()));
        } else if (this.model.drawCheck()) {
            gameOver = true;
            this.model.addScore(true);
            this.model.saveGameProgress(true);
            gameEndPopup("Draw", "It's a draw!\nDo you want to play again?");
        } else {
            this.model.updateParameters();
        }

        if (this.model.getCurrentPlayer() instanceof Human) {
            this.humanTurn = true;
        }
    }

    private void exitPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit?");
        alert.setContentText("Are you sure you want to quit?%nAny unsaved progress will be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }

    private void gameEndPopup(String title, String msg) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(yes, no);
        alert.setTitle(title);
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == yes) {
                try {
                    GameView newGameView = new GameView(this.view.getBoardSize(), this.view.getMusic());
                    Game newGameModel = new Game(this.model.getBoardChoice(), this.model.getPlayerChoice());
                    newGameModel.setPlayers(this.model.getPlayerChoice(), this.model.getPlayer1(), this.model.getPlayer2());
                    new GamePresenter(newGameView, newGameModel);
                    this.view.getScene().setRoot(newGameView);
                } catch (GameException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Sorry, it seems like something went wrong.\nPlease try again later");
                    error.show();
                }
            } else {
                gotoMenu();
            }
        }
    }

    private void returnPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Return?");
        alert.setContentText("Are you sure you want to return to the menu?%nAny unsaved progress will be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                gotoMenu();
            }
        }
    }

    private void gotoMenu() {
        MenuView menuView = new MenuView();
        new MenuPresenter(menuView);

        this.view.getScene().setRoot(menuView);
        menuView.getScene().getWindow().setHeight(700);
        menuView.getScene().getWindow().setWidth(900);
        if (this.view.getPlayer() != null) {
            if (this.view.getPlayer().isAutoPlay()) {
                this.view.getPlayer().stop();
            }
        }
    }
}
