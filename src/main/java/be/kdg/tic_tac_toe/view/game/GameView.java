package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.FigureType;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameView extends BorderPane {

    //game
    private MenuItem exit;
    private MenuItem back;

    //about
    private MenuItem rules;
    private Figure[][] figures;

    public GameView(int boardSize) {
        initializeNodes(boardSize);
        layoutNodes();
    }

    private void initializeNodes(int boardSize) {
        this.exit = new MenuItem("Exit");
        this.back = new MenuItem("Return");
        this.rules = new MenuItem("Rules");
        this.figures = new Figure[boardSize][boardSize];

    }

    private void layoutNodes() {
        //menus
        Menu gameMenu = new Menu("Game", null, this.back, this.exit);
        Menu aboutMenu = new Menu("About", null, this.rules);

        MenuBar bar = new MenuBar(gameMenu, aboutMenu);

        this.setTop(bar);

        //game
        GridPane grid = new GridPane();

        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures[i].length; j++) {
                figures[i][j] = new Figure(i, j);
                grid.add(figures[i][j], i, j);
            }
        }

        figures[0][0].setFigureType(FigureType.CROSS);
        figures[0][1].setFigureType(FigureType.CIRCLE);

        grid.setGridLinesVisible(true);

        //grid.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        BorderPane.setMargin(grid, new Insets(50));
        this.setCenter(grid);
    }

    public MenuItem getExit() {
        return this.exit;
    }

    public MenuItem getRules() {
        return this.rules;
    }

    public MenuItem getBack(){
        return this.back;
    }

    public Figure[][] getFigures() {
        return this.figures;
    }


}
