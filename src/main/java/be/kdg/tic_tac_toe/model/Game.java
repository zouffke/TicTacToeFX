package be.kdg.tic_tac_toe.model;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private Contribution contribution;
    private final Board board;
    private boolean validMove;
    private boolean gameOver;
    private Player currentPlayer;
    private Sort currentSort;
    private int count;

    public Game(int boardChoice, int playerChoice) {
        this.gameOver = false;
        this.count = 1;
        this.contribution = setPlayers(playerChoice);
        this.contribution.setSorts();
        this.validMove = false;

        System.out.printf("\n%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));

        this.board = setBoard(boardChoice);
        this.updateParameters();
    }

    private Contribution setPlayers(int choice) {
        if (choice == 1) {
            String name1;
            String name2;

            //TODO give the option to change player name
            name1 = "Temp 1";
            name2 = "Temp 2";

            return new Contribution(name1, name2);

        } else if (choice == 2) {
            //TODO change the second option to an easier NPC
            String name;
            name = "Temp 1";

            return new Contribution(name);
        } else {
            String name;
            name = "Temp 1";

            return new Contribution(name);
        }
    }

    public Board setBoard(int option) {
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

    private void updateParameters() {
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

        this.updateParameters();
    }

    public void place(int x, int y) throws BoardException {
        validMove = board.validMove(y, x);

        if (validMove) {
            this.board.place(this.currentSort, y, x);
        } else {
            throw new BoardException("This is an invalid move");
        }
        this.updateParameters();
    }

    public static boolean winCheck(Board board, Player currentPlayer, Sort currentSort) {

        if (board.win(currentSort)) {
            System.out.printf("%s heeft gewonnen\n", currentPlayer.getNAME());
            return true;
        } else if (board.draw()) {
            System.out.println("It's a Draw!\n");
            return true;
        }
        return false;
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}