package be.kdg.tic_tac_toe;

import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        //create HomeView and Homepresenter
        HomeView view = new HomeView();
        new HomePresenter(view);

        //Create the scene
        Scene scene = new Scene(view);
        //scene maken
        stage.setScene(scene);
        scene.getStylesheets().add("file:resources/stylesheets/home.css");
        //titel van de scene bepalen
        stage.setTitle("Tic Tac Toe");
        //de afmetingen van de scene bepalen
        stage.setWidth(900);
        stage.setHeight(700);
        //hierdoor kan je het scherm niet meer resizen
        stage.setResizable(false);
        //zorgen dat je de scene kan zien
        stage.show();
    }
}