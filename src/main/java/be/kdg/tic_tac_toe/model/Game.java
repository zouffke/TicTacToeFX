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
    private final Board board;
    private final int playerChoice;
    private final int boardChoice;
    private Contribution contribution;
    private boolean validMove;
    private Player currentPlayer;
    private Sort currentSort;
    private int count;
    private final PlayersSave playersSave;
    private final GamesSave gamesSave;

    public Game(int boardChoice, int playerChoice) throws GameException {
        this.count = 1;
        this.validMove = false;
        this.playerChoice = playerChoice;
        this.boardChoice = boardChoice;

        try {
            this.playersSave = new PlayersSave();
            this.gamesSave = new GamesSave(this.contribution);
        } catch (SaveFileException e) {
            throw new GameException(e.getMessage());
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
            this.contribution = new Contribution(name1, false);

        } else {
            this.contribution = new Contribution(name1, true);
        }

        this.contribution.setSorts();

        System.out.printf("\n%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));
        try {
            playersSave.writePlayers(this.contribution.getName(1), this.contribution.getName(2), this.contribution.getPlayer(2) instanceof Human);
            gamesSave.initGameSave();
        } catch (SaveFileException e) {
            throw new GameException(e.getMessage());
        }
    }

    public void saveGameProgress(boolean draw) {
        this.saveGameProgress(draw, null);
    }

    public void saveGameProgress(boolean draw, Player winner) {
        try {
            Files.write(gamesSave, String.format("%s%ngameEnd:%s%n", this.moves, draw ? "draw" : winner.toString()).getBytes(), APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateParameters() {
        if (this.count++ == 1) {
            this.currentPlayer = this.contribution.getSort(1).equals("X") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.X;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.toString());
        } else {
            this.currentPlayer = this.contribution.getSort(1).equals("O") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.O;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.toString());
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

    public void addScore(boolean draw) throws GameException {
        this.addScore(draw, null);
    }

    public void addScore(boolean draw, Player winner) throws GameException {
        try {
            playersSave.updateScore(draw, winner, this.contribution);
        } catch (SaveFileException e) {
            throw new GameException(e.getMessage());
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