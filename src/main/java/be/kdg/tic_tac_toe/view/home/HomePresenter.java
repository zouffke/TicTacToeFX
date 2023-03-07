package be.kdg.tic_tac_toe.view.home;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import javafx.application.Platform;

public class HomePresenter {

    //attributen aanmaken
    private final HomeView view;
    private final Model model;

    //constructor waarin vieuw en model worden aangeroepen en waar addEventHandlers en updateView worden aangeroepen
    public HomePresenter(HomeView view, Model model) {
        this.view = view;
        this.model = model;

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
            new MenuPresenter(menuView, model);

            // scene van de huidige vieuw oproepen en de vieuw veranderen naar de nieuwe (menu vieuw)
            view.getScene().setRoot(menuView);
        });
    }

    private void updateView() {
    }
}
