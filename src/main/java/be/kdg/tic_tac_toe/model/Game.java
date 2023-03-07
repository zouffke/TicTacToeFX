package be.kdg.tic_tac_toe.model;

import java.util.concurrent.TimeUnit;

public class Game {

    private Contribution contribution;
    private final Board board;
    private boolean validMove;
    private Player currentPlayer;
    private Sort currentSort;
    private int count;
    private final int playerChoice;
    private final int boardChoice;

    public Game(int boardChoice, int playerChoice) {
        this.count = 1;
        this.validMove = false;
        this.playerChoice = playerChoice;
        this.boardChoice = boardChoice;

        this.board = setBoard(boardChoice);
    }

    public void setPlayers(int choice, String name1, String name2) throws GameException {
        if (name1.isBlank() || name2.isBlank()){
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
        } else {
            throw new GameException("This is an invalid move");
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

    public String getPlayer1(){
        return this.contribution.getName(1);
    }

    public String getPlayer2(){
        return this.contribution.getName(2);
    }

}