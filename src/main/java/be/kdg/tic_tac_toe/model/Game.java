package be.kdg.tic_tac_toe.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.APPEND;

public class Game {

    private static final Path gamesSave = Paths.get("src" + File.separator +
            "main" + File.separator +
            "java" + File.separator +
            "be" + File.separator +
            "kdg" + File.separator +
            "tic_tac_toe" + File.separator +
            "resources" + File.separator +
            "games.txt");
    private static final Path players = Paths.get("src" + File.separator +
            "main" + File.separator +
            "java" + File.separator +
            "be" + File.separator +
            "kdg" + File.separator +
            "tic_tac_toe" + File.separator +
            "resources" + File.separator +
            "players.txt");
    private final Board board;
    private final int playerChoice;
    private final int boardChoice;
    private Contribution contribution;
    private boolean validMove;
    private Player currentPlayer;
    private Sort currentSort;
    private int count;
    private final StringBuilder moves;

    public Game(int boardChoice, int playerChoice) {
        this.count = 1;
        this.validMove = false;
        this.playerChoice = playerChoice;
        this.boardChoice = boardChoice;
        this.moves = new StringBuilder();

        if (!Files.exists(gamesSave)) {
            try {
                Files.createFile(gamesSave);
                System.out.println("File created: " + gamesSave.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists: " + gamesSave.getFileName());
        }

        if (!Files.exists(players)) {
            try {
                Files.createFile(players);
                System.out.println("File created: " + players.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists: " + players.getFileName());
        }

        this.board = setBoard(boardChoice);
    }

    private Board setBoard(int option) {
        switch (option) {
            case 5 -> {
                return new Board(5, 5);
            }
            case 7 -> {
                return new Board(7, 7);
            }
            default -> {
                return new Board(3, 3);
            }
        }
    }

    public void setPlayers(int choice, String name1, String name2) throws GameException {
        if (name1.isBlank() || name2.isBlank()) {
            throw new GameException("Please write your name in the given field(s) to continue");
        }
        if (choice == 1) {
            this.contribution = new Contribution(name1, name2);

        } else if (choice == 2) {
            //TODO change the second option to an easier NPC
            this.contribution = new Contribution(name1);

        } else {
            this.contribution = new Contribution(name1);
        }

        this.contribution.setSorts();

        System.out.printf("\n%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));
        this.writePlayers();
        this.initGameSave();
    }

    private void writePlayers() {
        boolean name1 = false;
        boolean name2 = false;
        //create the scanner
        try (Scanner scanner = new Scanner(players)) {
            //loop through the file
            while (scanner.hasNextLine()) {
                //read the line
                String line = scanner.nextLine();
                //check if the line contains the player and score
                if (line.contains("player") && line.contains("score")) {
                    line = line.split(";")[0].split(":")[1];
                }

                //check if the name is already in the file
                if (line.replace(" ", "").equalsIgnoreCase(this.contribution.getName(1).replace(" ", ""))) {
                    name1 = true;
                } else if (line.replace(" ", "").equalsIgnoreCase(this.contribution.getName(2).replace(" ", ""))) {
                    name2 = true;
                }
            }
            //if the name is not in the file, add it
            if (!name1) {
                Files.write(players, String.format("player:%s;score:0%n", this.contribution.getName(1)).getBytes(), APPEND);
            }
            if (!name2) {
                Files.write(players, String.format("player:%s;score:0%n", this.contribution.getName(2)).getBytes(), APPEND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameSave() {
        int gameNumber = 0;
        try (Scanner scanner = new Scanner(gamesSave)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("game") && line.contains("board")) {
                    gameNumber = Integer.parseInt(line.split(";")[0].split(":")[1]);
                    gameNumber++;
                }
            }

            Files.write(gamesSave, String.format("game:%d;dateStamp:%s;player1:%s;AS:%s;player2:%s;AS:%s;board:%dx%d%n"
                            , gameNumber
                            , LocalDateTime.now()
                            , this.contribution.getName(1)
                            , this.contribution.getSort(1)
                            , this.contribution.getName(2)
                            , this.contribution.getSort(2)
                            , Board.getSize()
                            , Board.getSize()).getBytes()
                    , APPEND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGameProgress(boolean draw) {
        this.saveGameProgress(draw, null);
    }

    public void saveGameProgress(boolean draw, Player winner) {
        try {
            Files.write(gamesSave, String.format("%s%ngameEnd:%s%n", this.moves, draw ? "draw" : winner.getNAME()).getBytes(), APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateParameters() {
        if (this.count++ == 1) {
            this.currentPlayer = this.contribution.getSort(1).equals("X") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.X;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.getNAME());
        } else {
            this.currentPlayer = this.contribution.getSort(1).equals("O") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.O;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.getNAME());
            this.count = 1;
        }
    }

    public void npcMove() {
        NPC npc = (NPC) currentPlayer;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            npc.playNPC(this.board, this.currentSort);
        }
    }

    public void place(int x, int y) throws GameException {
        validMove = board.validMove(y, x);

        if (validMove) {
            this.board.place(this.currentSort, y, x);
            moves.append(String.format("%s:%d-%d;", this.currentSort, y, x));
        } else {
            throw new GameException("This is an invalid move");
        }
    }

    public void addScore(boolean draw) {
        this.addScore(draw, null);
    }

    public void addScore(boolean draw, Player winner) {
        int score = 0;
        if (draw) {
            score = 1;
        } else {
            if (this.contribution.getPlayer(2) instanceof NPC && winner instanceof Human) {
                score = 3;
            } else if (this.contribution.getPlayer(2) instanceof Human) {
                score = 2;
            }
        }

        try (Scanner scanner = new Scanner(players)) {
            int pscore;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                //read the line
                String line = scanner.nextLine();
                //check if the line contains the player and score
                if (!(line.contains("player") && line.contains("score"))) {
                    sb.append(String.format("%s%n", line));
                } else if (draw) {
                    if (line.replace(" ", "").equalsIgnoreCase(this.contribution.getName(1).replace(" ", ""))) {
                        pscore = Integer.parseInt(line.split(";")[1].split(":")[1]);
                        pscore += score;
                        sb.append(String.format("player:%s;score:%d%n", this.contribution.getName(1), pscore));
                    } else if (line.replace(" ", "").equalsIgnoreCase(this.contribution.getName(2).replace(" ", ""))) {
                        pscore = Integer.parseInt(line.split(";")[1].split(":")[1]);
                        pscore += score;
                        sb.append(String.format("player:%s;score:%d%n", this.contribution.getName(2), pscore));
                    }
                } else if (winner.getNAME().equalsIgnoreCase(line.split(";")[0].split(":")[1])) {
                    pscore = Integer.parseInt(line.split(";")[1].split(":")[1]);
                    pscore += score;
                    sb.append(String.format("player:%s;score:%d%n", winner.getNAME(), pscore));
                } else {
                    sb.append(String.format("%s%n", line));
                }
            }
            Files.write(players, sb.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean winCheck() {
        return this.board.win(this.currentSort);
    }

    public boolean drawCheck() {
        return this.board.draw();
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getPlayerChoice() {
        return this.playerChoice;
    }

    public int getBoardChoice() {
        return this.boardChoice;
    }

    public String getPlayer1() {
        return this.contribution.getName(1);
    }

    public String getPlayer2() {
        return this.contribution.getName(2);
    }

}