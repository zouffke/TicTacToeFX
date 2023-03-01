package be.kdg.tic_tac_toe.view.home;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.MenuPresenter;
import be.kdg.tic_tac_toe.view.menu.MenuView;
import javafx.application.Platform;

public class HomePresenter {

    private final HomeView view;
    private final Model model;

    public HomePresenter(HomeView view, Model model) {
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //quiting the program
        this.view.getQuit().setOnAction(actionEvent -> {
            Platform.exit();
        });

        //going to menu screen
        this.view.getPlay().setOnAction(actionEvent -> {
            MenuView menuView = new MenuView();
            new MenuPresenter(menuView, model);

            view.getScene().setRoot(menuView);
        });
    }

    private void updateView() {
    }
}
