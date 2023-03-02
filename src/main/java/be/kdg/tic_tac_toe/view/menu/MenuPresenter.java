package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Game;
import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.game.GamePresenter;
import be.kdg.tic_tac_toe.view.game.GameView;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;
import javafx.scene.control.Alert;

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
                //TODO model
                playerOption = 1;
            } else if (this.view.getPvE().isSelected()) {
                //TODO model
                playerOption = 2;
            } else if (this.view.getUltraNightmare().isSelected()){
                //TODO Model
                playerOption = 3;
                music = true;
            } else {
                warningPopup("Please select a gamemode before you start the game");
                return;
            }

            if (this.view.getDrie().isSelected()){
                //TODO give size to model
                size = 3;
            } else if (this.view.getVijf().isSelected()){
                //TODO give size to model
                size = 5;
            } else if (this.view.getZeven().isSelected()){
                //TODO give size to model
                size = 7;
            } else {
                warningPopup("Please select a board size before you start the game");
                return;
            }

            GameView gameView = new GameView(size, music);
            Game gameModel = new Game(size, playerOption);
            new GamePresenter(gameView, gameModel);

            this.view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
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
