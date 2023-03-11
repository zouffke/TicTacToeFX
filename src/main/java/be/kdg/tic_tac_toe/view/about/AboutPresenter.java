package be.kdg.tic_tac_toe.view.about;


import be.kdg.tic_tac_toe.model.Model;


public class AboutPresenter {
    //vars aanmaken
    private final AboutView view;
    private final Model model;

    public AboutPresenter(AboutView view, Model model) {
        //vars initializeren en addEventHandlers en updateView aanroepen
        this.view = view;
        this.model = model;

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {


    }

    private void updateView() {
    }
}
