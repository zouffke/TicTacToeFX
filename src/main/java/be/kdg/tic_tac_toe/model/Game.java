package be.kdg.tic_tac_toe.model;

import java.util.concurrent.TimeUnit;


public class Game {
    private final Board board;
    private final int playerChoice;
    private final int boardChoice;
    private Contribution contribution;
    private boolean validMove;
    private Player currentPlayer;
    private Sort currentSort;
    private boolean firstPlayer = true;
    private final PlayersSave playersSave;
    private final GamesSave gamesSave;
    private boolean gameEnded = false;

    public Game(int boardChoice, int playerChoice) throws GameException {
        //vars initializeren, valid move begint op false omdat ze eerst moeten checkken of het true is
        this.validMove = false;
        this.playerChoice = playerChoice;
        this.boardChoice = boardChoice;

        try {
            this.playersSave = new PlayersSave();
            this.gamesSave = new GamesSave();
        } catch (SaveFileException e) {
            throw new GameException(e.getMessage());
        }
    // het gekozen bord genereren
        this.board = setBoard(boardChoice);
    }

    private Board setBoard(int option) {
        //de bordsize kiezen
        //kiezen ze 5 dan moet de width en lengt op 5 gezet worden
        //kiezen ze 7 dan moet de width en lengt op 5 gezet worden
        //hij staat default op 3x3
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
        //exception handeling als de namen niet zijn ingevuld word er een exception gegooid die zegt dat de namen
        //ingevuld moeten worden om door te gaan
        if (name1.isBlank() || name2.isBlank()) {
            throw new GameException("Please write your name in the given field(s) to continue");
        }

        if (name1.equalsIgnoreCase(name2)) {
            throw new GameException("Please write a different name for player 2");
        }

        if (choice == 1) {
            //als keuze 1 is dan spelen we pvp en moeten er dus 2 namen gegeven worden
            this.contribution = new Contribution(name1, name2);

        } else if (choice == 2) {
            // als de keuze 2 is dan word de ai op false gezet wat bedenkt dat het de makkelijke is --> random bot
            this.contribution = new Contribution(name1, false);
        // als 1 en 2 niet gekozen zijn word de ai op true gezet en gaat de minimax in werking
        } else {
            this.contribution = new Contribution(name1, true);
        }

        this.contribution.setSorts();
        // hier word bepaald wie met wat speelt (X, O)
        System.out.printf("\n%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));
        try {
            this.gamesSave.setContribution(this.contribution);
            playersSave.writePlayers(this.contribution.getName(1), this.contribution.getName(2), this.contribution.getPlayer(2) instanceof Human);
            gamesSave.initGameSave();
        } catch (SaveFileException e) {
            throw new GameException(e.getMessage());
        }
    }

    public void saveGameProgress(String winner) throws GameException {
        try {
            this.gamesSave.saveGameProgress(winner);
        } catch (SaveFileException e){
            throw new GameException(e.getMessage());
        }
    }

    public void updateParameters() {
        if (this.firstPlayer) {
            this.currentPlayer = this.contribution.getSort(1).equals("X") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.X;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.toString());
        } else {
            this.currentPlayer = this.contribution.getSort(1).equals("O") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.O;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.toString());
        }
        this.firstPlayer = !this.firstPlayer;
    }

    public void npcMove() {
        NPC npc = (NPC) currentPlayer;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
           this.gamesSave.addMove(npc.playNPC(this.board, this.currentSort));
        }
    }

    public void place(int x, int y) throws GameException {
        //checkt of de zet die gemaakt word valid is zo niet gooit een exception
        validMove = board.validMove(y, x);

        if (validMove) {
            this.board.place(this.currentSort, y, x);
            this.gamesSave.addMove(String.format("%s=%d-%d;", this.currentSort, y, x));
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
        this.gameEnded = this.board.win(this.currentSort);
        return this.gameEnded;
    }

    public boolean drawCheck() {
        this.gameEnded = this.board.draw();
        return this.gameEnded;
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

    public boolean getHuman(){
        return this.currentPlayer instanceof Human;
    }

    public boolean getGameEnded() {
        return !this.gameEnded;
    }

}