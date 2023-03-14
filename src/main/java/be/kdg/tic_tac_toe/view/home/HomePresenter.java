package be.kdg.tic_tac_toe.view.home;

import be.kdg.tic_tac_toe.view.highscore.HighscorePresenter;
import be.kdg.tic_tac_toe.view.highscore.HighscoreView;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import javafx.application.Platform;

public class HomePresenter {

    //attributen aanmaken
    private final HomeView view;

    //constructor waarin vieuw en model worden aangeroepen en waar addEventHandlers en updateView worden aangeroepen
    public HomePresenter(HomeView view) {
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //quiting the program
        //de knop quit word opgehaald en als er op gedrukt word dan ga je weg van het huidige platform
        this.view.getQuit().setOnAction(actionEvent -> Platform.exit());

        //going to menu screen
        //play aanroepen als erop gedrukt word, naar de play scene gaan
        this.view.getPlay().setOnAction(actionEvent -> {
            //instantie van menuvieuw aanmaken
            MenuView menuView = new MenuView();
            //instantie menuPresenter aanmaken
            new MenuPresenter(menuView);
            // scene van de huidige vieuw oproepen en de vieuw veranderen naar de nieuwe (menu vieuw)
            view.getScene().setRoot(menuView);
        });

        //als je op de knop highscore klikt dan gaat die naar de vieuw van highscore en maakt een nieuwe scene aan
        this.view.getHighscore().setOnAction(action -> {
            HighscoreView highscoreView = new HighscoreView();
            new HighscorePresenter(highscoreView);
            view.getScene().setRoot(highscoreView);
        });

    }

    private void updateView() {
    }
}
