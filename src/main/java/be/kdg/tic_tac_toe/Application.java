package be.kdg.tic_tac_toe;
import be.kdg.tic_tac_toe.view.application.View;
import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.application.Presenter;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        View view = new View();
        Scene scene = new Scene(view);
        new Presenter(view, new Model());

        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.setWidth(300);
        stage.setHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}