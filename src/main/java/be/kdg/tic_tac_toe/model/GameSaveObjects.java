package be.kdg.tic_tac_toe.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameSaveObjects {
    private final int gameNumber;
    private final LocalDateTime date;
    private final Player player1;
    private final Sort sort1;
    private final Player player2;
    private final Sort sort2;
    private final String board;
    private final List<String> moves;
    private Player winner;

    GameSaveObjects(int gameNumber, LocalDateTime date, Player player1, Sort sort1, Player player2, Sort sort2, String board, List<String> moves, Player winner) {
        this.gameNumber = gameNumber;
        this.date = date;
        this.player1 = player1;
        this.sort1 = sort1;
        this.player2 = player2;
        this.sort2 = sort2;
        this.board = board;
        this.moves = Objects.requireNonNullElseGet(moves, ArrayList::new);
        this.winner = winner;
    }

    void setWinner(Player winner) {
        this.winner = winner;
    }

    String getHeading() {
        return String.format("game=%d;dateStamp=%s;player1=%s;sort1=%s;player2=%s;sort2=%s;board=%s"
                , gameNumber
                , date
                , player1.toString()
                , sort1.toString()
                , player2.toString()
                , sort2.toString()
                , board);
    }

    public void addMove(String move) {
        this.moves.add(move);
    }

    public int getMoveAmount() {
        return this.moves.size();
    }

    public String getMoves() {
        StringBuilder moves = new StringBuilder();
        for (String move : this.moves) {
            moves.append(move);
        }
        return moves.toString();
    }

    public String getMove(int index) {
        return this.moves.get(index);
    }

    String getWinner() {
        return String.format("GameEnd=%s", this.winner.toString());
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public int getGameNumber() {
        return this.gameNumber;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Sort getSort1() {
        return this.sort1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public Sort getSort2() {
        return this.sort2;
    }

    public Player getWinnerPlayer() {
        return this.winner;
    }

    public String getBoard(){
        return this.board;
    }
}
