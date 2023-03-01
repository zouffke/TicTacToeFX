package be.kdg.tic_tac_toe.view.menu;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;

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
    }

    private void updateView(){
    }
}
