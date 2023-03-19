package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Game;
import be.kdg.tic_tac_toe.model.GameException;
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
    // attributen aanmaken
    private final MenuView view;

    public MenuPresenter(MenuView view) {
        // attributen initialiseren
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // gaat terug naar het homescherm als er op de knop terug wordt gedrukt
        this.view.getTerug().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            new HomePresenter(homeView);

            this.view.getScene().setRoot(homeView);
            homeView.getScene().getStylesheets().remove("file:resources/stylesheets/menu.css");
            homeView.getScene().getStylesheets().add("file:resources/stylesheets/home.css");
        });

        //play aanroepen als erop gedrukt word, naar de play scene gaan
        this.view.getPlay().setOnAction(actionEvent -> {
            // vars maken
            int size;
            int playerOption;
            boolean music = false;

            // checken welke optie is geselecteerd
            if (this.view.getPvP().isSelected()) {
                playerOption = 1;
            } else if (this.view.getPvE().isSelected()) {
                playerOption = 2;
            } else if (this.view.getUltraNightmare().isSelected()) {
                playerOption = 3;
                music = true;
            } else {
                // als er geen optie is geselecteerd dan krijg je een popup
                warningPopup("Please select a gamemode before you start the game");
                return;
            }

            // checken welke optie is geselecteerd
            if (this.view.getDrie().isSelected()) {
                size = 3;
            } else if (this.view.getVijf().isSelected()) {
                size = 5;
            } else if (this.view.getZeven().isSelected()) {
                size = 7;
            } else {
                warningPopup("Please select a board size before you start the game");
                return;
            }

            try {
                // instantie van game aanmaken
                Game gameModel = new Game(size, playerOption);

                // playerView aanmaken en presenter aanmaken
                PlayerView playerView = new PlayerView(playerOption);
                // nieuwe stage aanmaken
                Stage playerStage = new Stage();
                // nieuwe playerPresenter aanmaken en parameters meegeven
                PlayerPresenter playerPresenter = new PlayerPresenter(playerView, gameModel, playerStage);
                //ppt
                playerStage.initOwner(this.view.getScene().getWindow());
                playerStage.initModality(Modality.APPLICATION_MODAL);
                //scene aanmaken
                playerStage.setScene(new Scene(playerView));
                // grootte van de scene bepalen
                playerStage.setHeight(210);
                playerStage.setWidth(290);
                // de scene centreren
                playerStage.setX(this.view.getScene().getWindow().getX() + (this.view.getScene().getWindow().getWidth() / 2 - playerStage.getWidth() / 2));
                playerStage.setY(this.view.getScene().getWindow().getY() + (this.view.getScene().getWindow().getHeight() / 2 - playerStage.getHeight() / 2));
                // de scene laten zien
                playerStage.showAndWait();

                // als de namen zijn ingevuld dan gaat de gameView openen
                if (playerPresenter.isNamesFilled()) {
                    GameView gameView = new GameView(size, music);

                    this.view.getScene().setRoot(gameView);
                    new GamePresenter(gameView, gameModel);

                    gameView.getScene().getStylesheets().remove("file:resources/stylesheets/menu.css");
                    gameView.getScene().getStylesheets().add("file:resources/stylesheets/game.css");

                    gameView.getScene().getWindow().sizeToScene();
                }
            } catch (GameException e){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText(String.format("Sorry, it seems like something went wrong.%nPlease try again later%n%n(Error:%s)", e.getMessage()));
                error.show();
            }
        });
    }

    private void updateView() {
    }

    private void warningPopup(String message) {
        // popup aanmaken die een warning geeft en de meegegeven message laat zien
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
