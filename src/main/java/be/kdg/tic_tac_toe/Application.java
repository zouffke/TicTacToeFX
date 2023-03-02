package be.kdg.tic_tac_toe;

import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.home.HomePresenter;
import be.kdg.tic_tac_toe.view.home.HomeView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        HomeView view = new HomeView();
        new HomePresenter(view, new Model());

        Scene scene = new Scene(view);

        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.setWidth(900);
        stage.setHeight(700);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}