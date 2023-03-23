package be.kdg.tic_tac_toe.model;

import java.time.LocalDateTime;

public class GameSaveObjects {
    private final int gameNumber;
    private final LocalDateTime date;
    private final Player player1;
    private final Sort sort1;
    private final Player player2;
    private final Sort sort2;
    private final String board;
    private String moves;
    private Player winner;

    GameSaveObjects(int gameNumber, LocalDateTime date, Player player1, Sort sort1, Player player2, Sort sort2, String board, String moves, Player winner) {
        this.gameNumber = gameNumber;
        this.date = date;
        this.player1 = player1;
        this.sort1 = sort1;
        this.player2 = player2;
        this.sort2 = sort2;
        this.board = board;
        this.moves = moves;
        this.winner = winner;
    }

    void setMoves(String moves){
        this.moves = moves;
    }

    void setWinner(Player winner) {
        this.winner = winner;
    }

    String getHeading(){
        return String.format("game=%d;dateStamp=%s;player1=%s;sort1=%s;player2=%s;sort2=%s;board=%s"
                , gameNumber
                , date
                , player1.toString()
                , sort1.toString()
                , player2.toString()
                , sort2.toString()
                , board);
    }

    public String getMoves(){
        return this.moves;
    }

    String getWinner(){
        return String.format("GameEnd=%s", this.winner.toString());
    }

    public LocalDateTime getDate() {
        return this.date;
    }
    public int getGameNumber(){
        return this.gameNumber;
    }
}
