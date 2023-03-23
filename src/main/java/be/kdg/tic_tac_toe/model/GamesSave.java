package be.kdg.tic_tac_toe.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class GamesSave {
    private static final String template = """
            //\\\\GAME SAVE FILE//\\\\
            //\\\\DO NOT EDIT THIS FILE MANUALLY//\\\\
            //\\\\THIS FILE IS AUTOMATICALLY GENERATED//\\\\
            //\\\\REMOVING LINES WILL CAUSE ERRORS EVEN BLANK LINES//\\\\
            //\\\\ANY CHANGE CAN CAUSE CORRUPTION OF YOUR SAVE FILE//\\\\
            //\\\\WHEN YOU EDIT THIS FILE, MAKE SURE YOU HAVE A BACKUP AND ALWAYS LEAVE ONE LINE EMPTY AT THE BOTTOM OF THIS FILE//\\\\
            ========================================================================================================================
            """;

    private static final Path gamesSave = Paths.get("resources" + File.separator
            + "saveFiles" + File.separator
            + "games.txt");
    private final List<GameSaveObjects> gamesList;
    private Contribution contribution;
    private int gameNumber;
    private final StringBuilder moves;

    public GamesSave() throws SaveFileException {
        this.gamesList = new ArrayList<>();
        this.moves = new StringBuilder();
        this.gameNumber = 0;

        SaveFiles.checkFile(gamesSave);
        this.fillList();
    }

    private void fillList() throws SaveFileException {
        try (Scanner scanner = new Scanner(gamesSave)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("game") && line.contains("board")) {
                    gameNumber = this.getGameNumber(line);
                    gameNumber++;
                    this.addGameLine(line, scanner.nextLine(), scanner.nextLine());
                }
            }
        } catch (IOException e) {
            throw new SaveFileException("File could not be read: " + gamesSave.getFileName());
        }
    }

    private void addGameLine(String heading, String moves, String winner) {
        this.gamesList.add(this.createObject(
                Integer.parseInt(SaveFiles.getSubString(heading, 0))
                , LocalDateTime.parse(SaveFiles.getSubString(heading, 1))
                , new Player(SaveFiles.getSubString(heading, 2))
                , Sort.valueOf(SaveFiles.getSubString(heading, 3))
                , new Player(SaveFiles.getSubString(heading, 4))
                , Sort.valueOf(SaveFiles.getSubString(heading, 5))
                , SaveFiles.getSubString(heading, 6)
                , moves
                , new Player(SaveFiles.getSubString(winner, 0))));
    }

    private GameSaveObjects createObject(int gameNumber, LocalDateTime date, Player player1, Sort sort1, Player player2, Sort sort2, String board, String moves, Player winner) {
        return new GameSaveObjects(gameNumber, date, player1, sort1, player2, sort2, board, moves, winner);
    }

    void initGameSave() {
        this.gamesList.add(this.createObject(
                this.gameNumber
                , LocalDateTime.now()
                , this.contribution.getPlayer(1)
                , Sort.valueOf(this.contribution.getSort(1))
                , this.contribution.getPlayer(2)
                , Sort.valueOf(this.contribution.getSort(2))
                , String.format("%dx%d", Board.getSize(), Board.getSize())
                , null
                , null));
    }

    private int getGameNumber(String line) {
        return Integer.parseInt(SaveFiles.getSubString(line, 0));
    }

    void addMove(String move) {
        this.moves.append(move);
    }

    void saveGameProgress(String winner) throws SaveFileException {
        this.gamesList.get(this.gameNumber).setMoves(this.moves.toString());
        this.gamesList.get(this.gameNumber).setWinner(new Player(winner));

        try {
            Files.write(gamesSave, String.format("%s%n%s%n%s%n"
                    , this.gamesList.get(gameNumber).getHeading()
                    , this.gamesList.get(gameNumber).getMoves()
                    , this.gamesList.get(gameNumber).getWinner()).getBytes(), APPEND);
        } catch (IOException e) {
            throw new SaveFileException("File could not be written: " + gamesSave.getFileName());
        }
    }

    void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public void clearSave() throws SaveFileException {
        try {
            Files.write(gamesSave, template.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveFileException("An Error occurred in: " + gamesSave.getFileName());
        }
    }

    public int getSize() {
        return this.gamesList.size();
    }

    public GameSaveObjects getGame(int index) {
        return this.gamesList.get(index);
    }
}
