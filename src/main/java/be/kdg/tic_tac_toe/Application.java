package be.kdg.tic_tac_toe;
import be.kdg.tic_tac_toe.view.game.GamePresenter;
import be.kdg.tic_tac_toe.view.game.GameView;
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
        Scene scene = new Scene(view);
        new HomePresenter(view, new Model());

        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.sizeToScene();
        stage.setWidth(900);
        stage.setHeight(700);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}