package be.kdg.tic_tac_toe.view.game;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameView extends BorderPane {

    private MenuItem exit;
    private MenuItem rules;
    private Node[][]

    public GameView(int size){
        initializeNodes();
        layoutNodes(size);
    }

    private void initializeNodes(){
        this.exit = new MenuItem("Exit");
        this.rules = new MenuItem("Rules");
    }

    private void layoutNodes(int size){
        //menus
        Menu gameMenu = new Menu("Game", null, this.exit);
        Menu aboutMenu = new Menu("About", null, this.rules);

        MenuBar bar = new MenuBar(gameMenu, aboutMenu);

        this.setTop(bar);

        //game
        GridPane grid = new GridPane();
        grid.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Circle circle = new Circle(20);
        grid.add(circle, 0, 0);
        this.setCenter(grid);
        BorderPane.setMargin(grid, new Insets(50));
    }

    public MenuItem getExit() {
        return exit;
    }

    public MenuItem getRules() {
        return rules;
    }
}
