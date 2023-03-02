package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.model.FigureType;
import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

public class GameView extends BorderPane {

    //game
    private MenuItem exit;
    private MenuItem back;

    //about
    private MenuItem rules;
    private Figure[][] figures;
    private final MediaPlayer player;

    public GameView(int boardSize, boolean music) {
        initializeNodes(boardSize);
        Media media = new Media(Paths.get("src/main/java/be/kdg/tic_tac_toe/source/At_Dooms_Gate.mp3").toUri().toString());
        player = new MediaPlayer(media);

        if (music){
            player.setAutoPlay(true);
            player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
            player.play();
        }

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

    public MediaPlayer getPlayer(){
        return this.player;
    }


}
