package be.kdg.tic_tac_toe.view.about;


import be.kdg.tic_tac_toe.model.Model;


public class AboutPresenter {
    //vars aanmaken
    private final AboutView view;

    public AboutPresenter(AboutView view) {
        //vars initializeren en addEventHandlers en updateView aanroepen
        this.view = view;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
    }

    private void updateView() {
    }
}
