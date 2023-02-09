package be.kdg.tic_tac_toe;
import be.kdg.tic_tac_toe.view.menu.View; //temp
import be.kdg.tic_tac_toe.model.Model;
import be.kdg.tic_tac_toe.view.menu.Presenter; //temp
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        View view = new View();
        Scene scene = new Scene(view);
        //temp//
        new Presenter(view, new Model());

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