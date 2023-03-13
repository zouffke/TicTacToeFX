package be.kdg.tic_tac_toe.view.game;

import be.kdg.tic_tac_toe.view.models.Figure;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Paths;

public class GameView extends BorderPane {

    //game
    private MenuItem exit;
    private MenuItem back;
    //about
    private MenuItem rules;
    //game view
    private Figure[][] figures;
    private Label name1;
    private Label name2;
    private final MediaPlayer player;
    private final boolean music;
    private final int boardSize;

    public GameView(int boardSize, boolean music) {
        this.boardSize = boardSize;
        this.music = music;
        initializeNodes(boardSize);


        if (music) {
            Media media = new Media(Paths.get("src" + File.separator
                            + "main" + File.separator
                            + "java" + File.separator
                            + "be" + File.separator
                            + "kdg" + File.separator
                            + "tic_tac_toe" + File.separator
                            + "resources" + File.separator
                            + "At_Dooms_Gate.mp3")
                    .toUri()
                    .toString());

            player = new MediaPlayer(media);
            player.setAutoPlay(true);
            player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
            player.play();
        } else {
            player = null;
        }
        layoutNodes();
    }

    private void initializeNodes(int boardSize) {
        this.exit = new MenuItem("Exit");
        this.back = new MenuItem("Return");
        this.rules = new MenuItem("Rules");
        this.figures = new Figure[boardSize][boardSize];
        this.name1 = new Label();
        this.name2 = new Label();
    }

    private void layoutNodes() {
        //menus
        Menu gameMenu = new Menu("Game", null, this.back, this.exit);
        Menu aboutMenu = new Menu("About", null, this.rules);

        MenuBar bar = new MenuBar(gameMenu, aboutMenu);

        VBox screenTop = new VBox();

        this.setTop(screenTop);

        //game
        GridPane grid = new GridPane();

        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures[i].length; j++) {
                figures[i][j] = new Figure(i, j);
                grid.add(figures[i][j], i, j);
            }
        }

        grid.setGridLinesVisible(true);

        //grid.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        BorderPane.setMargin(grid, new Insets(50));
        this.setCenter(grid);

        //current players
        BorderPane players = new BorderPane();

        players.setLeft(name1);
        players.setRight(name2);
        name1.setPadding(new Insets(5, 0, 0, 15));
        name2.setPadding(new Insets(5, 15, 0, 0));

        name1.setStyle("-fx-border-color: black; -fx-padding: 10px 20px 30px");
        name2.setStyle("-fx-border-color: black;");

        screenTop.getChildren().setAll(bar, players);
    }

    public MenuItem getExit() {
        return this.exit;
    }

    public MenuItem getRules() {
        return this.rules;
    }

    public MenuItem getBack() {
        return this.back;
    }

    public Figure[][] getFigures() {
        return this.figures;
    }

    public MediaPlayer getPlayer() {
        return this.player;
    }

    public boolean getMusic() {
        return this.music;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    Label getName1() {
        return this.name1;
    }

    Label getName2() {
        return this.name2;
    }
}
