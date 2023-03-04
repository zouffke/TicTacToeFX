package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Game;
import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.game.GamePresenter;
import be.kdg.tic_tac_toe.view.game.GameView;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;
import be.kdg.tic_tac_toe.view.players.PlayerPresenter;
import be.kdg.tic_tac_toe.view.players.PlayerView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPresenter {
    private final MenuView view;
    private final Model model;
    public MenuPresenter(MenuView view, Model model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers(){
        // gaat terug naar het homescherm
        this.view.getTerug().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            new HomePresenter(homeView, model);

            this.view.getScene().setRoot(homeView);
    });

        //play
        this.view.getPlay().setOnAction(actionEvent -> {
            int size;
            int playerOption;
            boolean music = false;

            if (this.view.getPvP().isSelected()){
                playerOption = 1;
            } else if (this.view.getPvE().isSelected()) {
                playerOption = 2;
            } else if (this.view.getUltraNightmare().isSelected()){
                playerOption = 3;
                music = true;
            } else {
                warningPopup("Please select a gamemode before you start the game");
                return;
            }

            if (this.view.getDrie().isSelected()){
                size = 3;
            } else if (this.view.getVijf().isSelected()){
                size = 5;
            } else if (this.view.getZeven().isSelected()){
                size = 7;
            } else {
                warningPopup("Please select a board size before you start the game");
                return;
            }

            Game gameModel = new Game(size, playerOption);

            PlayerView playerView = new PlayerView(playerOption);
            Stage playerStage = new Stage();
            PlayerPresenter playerPresenter = new PlayerPresenter(playerView, gameModel, playerStage);
            playerStage.initOwner(this.view.getScene().getWindow());
            playerStage.initModality(Modality.APPLICATION_MODAL);
            playerStage.setScene(new Scene(playerView));
            playerStage.setHeight(200);
            playerStage.setWidth(290);
            playerStage.setX(this.view.getScene().getWindow().getX() + (this.view.getScene().getWindow().getWidth() / 2 - playerStage.getWidth() / 2));
            playerStage.setY(this.view.getScene().getWindow().getY() + (this.view.getScene().getWindow().getHeight() / 2 - playerStage.getHeight() / 2));
            playerStage.showAndWait();

            if (playerPresenter.isNamesFilled()) {
                GameView gameView = new GameView(size, music);
                new GamePresenter(gameView, gameModel);

                this.view.getScene().setRoot(gameView);
                gameView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView(){
    }

    private void warningPopup(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
