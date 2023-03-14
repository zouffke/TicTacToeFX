package be.kdg.tic_tac_toe.view.about;


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
