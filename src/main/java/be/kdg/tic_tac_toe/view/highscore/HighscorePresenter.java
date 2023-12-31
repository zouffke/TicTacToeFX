package be.kdg.tic_tac_toe.view.highscore;

import be.kdg.tic_tac_toe.model.PlayersSave;
import be.kdg.tic_tac_toe.model.SaveFileException;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;

public class HighscorePresenter {
    private final HighscoreView view;
    private final PlayersSave model;

    public HighscorePresenter(HighscoreView view, PlayersSave model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getTerug().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            new HomePresenter(homeView);

            this.view.getScene().setRoot(homeView);
            homeView.getScene().getStylesheets().remove("file:resources/stylesheets/highscores.css");
            homeView.getScene().getStylesheets().add("file:resources/stylesheets/home.css");
        });
    }

    private void updateView() {
        for (int i = 0; i < this.view.getScores().length; i++) {
            try {
                int width = 30;
                char fill = '.';
                String topad = this.model.getPlayer(i);
                String padded = topad + new String(new char[width - topad.length()]).replace("\0", String.valueOf(fill));

                this.view.getScores()[i].setText(String.format("%s%d", padded, this.model.getScore(i)).replace(" ", "."));
            } catch (SaveFileException e) {
                this.view.getScores()[i].setText("No scores yet");
            }
        }
    }
}


