package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.Board;
import be.kdg.tic_tac_toe.model.Game;
import be.kdg.tic_tac_toe.model.GameException;
import be.kdg.tic_tac_toe.model.Sort;
import be.kdg.tic_tac_toe.view.about.AboutPresenter;
import be.kdg.tic_tac_toe.view.about.AboutView;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GamePresenter {
    private final GameView view;
    private final Game model;
    private final Board board;
    private int y;
    private int x;

    public GamePresenter(GameView view, Game model) {
        this.view = view;
        this.model = model;
        this.board = model.getBoard();
        this.y = 0;
        this.x = 0;

        this.view.getName1().setText(this.model.getPlayer1());
        this.view.getName2().setText(this.model.getPlayer2());

        this.setGameOver();
        this.callNPC();
        this.updateView();

        this.addEventHandlers();
        this.addKeyEventHandlers();
    }

    private void addKeyEventHandlers() {
        this.view.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT, Q -> {
                    if (this.y > 0) {
                        this.y--;
                    }
                }
                case RIGHT, D -> {
                    if (y < this.view.getBoardSize() - 1) {
                        y++;
                    }
                }
                case UP, Z -> {
                    if (x > 0) {
                        x--;
                    }
                }
                case DOWN, S -> {
                    if (x < this.view.getBoardSize() - 1) {
                        x++;
                    }
                }
                case SPACE -> {
                    if (this.model.getGameEnded()) {
                        if (this.model.getHuman()) {
                            try {
                                this.model.place(x, y);
                                updateView();
                            } catch (GameException e) {
                                Alert warning = new Alert(Alert.AlertType.WARNING);
                                warning.setContentText(e.getMessage());
                                warning.showAndWait();
                            }
                        }
                    }
                }
            }
            updateSelector();
        });
    }

    private void updateSelector() {
        for (int i = 0; i < this.view.getBoardSize(); i++) {
            for (int j = 0; j < this.view.getBoardSize(); j++) {
                this.view.getBackgroundPane()[i][j].getStyleClass().remove("selected");
            }
        }

        this.view.getBackgroundPane()[y][x].getStyleClass().add("selected");
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
                    if (this.model.getHuman() && this.model.getGameEnded()) {
                        try {
                            this.model.place(figure.getColumn(), figure.getRow());
                            this.updateView();

                            this.setGameOver();
                            this.callNPC();

                        } catch (GameException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
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
    }

    private void currentTurn() {
        if (this.view.getName1().getText().equals(this.model.getCurrentPlayer().toString())) {
            this.view.getName1().getStyleClass().add("turnTrue");
            this.view.getName2().getStyleClass().remove("turnTrue");
        } else {
            this.view.getName2().getStyleClass().add("turnTrue");
            this.view.getName1().getStyleClass().remove("turnTrue");
        }
    }

    private void callNPC() {
        if (!this.model.getHuman() && this.model.getGameEnded()) {
            ExecutorService pool = Executors.newSingleThreadExecutor();
            Future<?> future = pool.submit(model::npcMove);

            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                future.cancel(true);
            }

            this.updateView();
            this.setGameOver();
        }
    }

    private void setGameOver() {
        try {
            if (this.model.winCheck()) {
                this.model.addScore(false, this.model.getCurrentPlayer());
                this.model.saveGameProgress(this.model.getCurrentPlayer().toString());
                gameEndPopup("Win", String.format("%s has won\nDo you want to play again?", this.model.getCurrentPlayer().toString()));
            } else if (this.model.drawCheck()) {
                this.model.addScore(true);
                this.model.saveGameProgress("Draw");

                gameEndPopup("Draw", "It's a draw!\nDo you want to play again?");
            } else {
                this.model.updateParameters();
                currentTurn();
            }
        } catch (GameException e) {
            errorPopup(e.getMessage());
        }
    }

    private void exitPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit?");
        alert.setContentText("Are you sure you want to quit?\nAny unsaved progress will be lost.");
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
                    this.view.getScene().setRoot(newGameView);
                    new GamePresenter(newGameView, newGameModel);
                } catch (GameException e) {
                    this.errorPopup(e.getMessage());
                }
            } else {
                gotoMenu();
            }
        }
    }

    private void returnPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Return?");
        alert.setContentText("Are you sure you want to return to the menu?\nAny unsaved progress will be lost.");
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
        menuView.getScene().getStylesheets().remove("file:resources/stylesheets/game.css");
        menuView.getScene().getStylesheets().add("file:resources/stylesheets/menu.css");
        menuView.getScene().getWindow().setHeight(700);
        menuView.getScene().getWindow().setWidth(900);
        if (this.view.getPlayer() != null) {
            if (this.view.getPlayer().isAutoPlay()) {
                this.view.getPlayer().stop();
            }
        }
    }

    private void errorPopup(String e) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(String.format("Sorry, it seems like something went wrong.%nPlease try again later%n%n(Error: %s)", e));
        error.show();
    }
}
