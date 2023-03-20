package be.kdg.tic_tac_toe.view.settings;

import be.kdg.tic_tac_toe.model.PlayersSave;
import be.kdg.tic_tac_toe.model.SaveFileException;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


public class SettingsPresenter {
    private final PlayersSave model;
    private final SettingsView view;

    public SettingsPresenter(PlayersSave model, SettingsView view) {
        this.model = model;
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBack().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            this.view.getScene().setRoot(homeView);
            new HomePresenter(homeView);
            homeView.getScene().getStylesheets().remove("file:resources/stylesheets/settings.css");
            homeView.getScene().getStylesheets().add("file:resources/stylesheets/home.css");
        });

        this.view.getClearPlayers().setOnAction(actionEvent -> {
            Optional<ButtonType> result = warningPopup("players");
            if (result.isPresent() && result.get() == ButtonType.OK)
                try {
                    this.model.clearSave();
                    this.InfoPopup("Players");
                } catch (SaveFileException e) {
                    ErrorPopup(e.getMessage());
                }
        });
    }

    private void updateView() {
    }

    private Optional<ButtonType> warningPopup(String file){
        Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setContentText(String.format("Are you sure you want to clear the %s file?", file));
        return warning.showAndWait();
    }

    private void ErrorPopup(String msg){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(String.format("Sorry, it seems like something went wrong.%nPlease try again later%n%n(Error:%s)", msg));
        error.show();
    }

    private void InfoPopup(String msg){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText(String.format("The %s file has been cleared", msg));
        info.show();
    }
}
