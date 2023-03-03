package be.kdg.tic_tac_toe.model;

import java.util.Arrays;

public class Board {

    private static int width;
    private static int length;
    private final Piece[][] pieces;


    public Board(int width, int length) {
        if (width == length && width == 3 || width == 5 || width == 7) {
            Board.width = width;
            Board.length = length;
        } else {
            Board.width = 3;
            Board.length = 3;
        }
        this.pieces = new Piece[Board.width][Board.length];
    }

    public boolean validMove(int y, int x) {
        return this.pieces[y][x] == null;
    }

    public void place(Sort currentSort, int y, int x) {
        this.pieces[y][x] = new Piece(currentSort, y, x);
    }

    public boolean win(Sort sort) {
        //define the trigger
        int trigger = (getSize() == 3) ? 3: 4;

        for (int y = 0; y < this.pieces.length; y++) {
            for (int x = 0; x < this.pieces[y].length; x++) {
                if (this.pieces[y][x] != null && this.pieces[y][x].equalsSort(sort)) {
                    for (int yy = -1; yy <= 1; yy++) {
                        for (int xx = -1; xx <= 1; xx++) {
                            //out of bounds check
                            if (!(yy + y < 0 || xx + x < 0 || yy + y >= this.pieces.length || xx + x >= this.pieces.length || (xx == 0 && yy == 0))) {
                                if (repeat(sort, y, x, yy, xx, trigger, 0)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean repeat(Sort sort, int y, int x, int yy, int xx, int trigger, int index) {
        if (index == trigger - 1) {
            return true;
        } else {
            int counterY = 0;
            int counterX = 0;
            if (yy != 0) {
                if (yy > 0) {
                    counterY = index;
                } else {
                    counterY = Math.negateExact(index);
                }
            }
            if (xx != 0) {
                if (xx > 0) {
                    counterX = index;
                } else {
                    counterX = Math.negateExact(index);
                }
            }
            if (outOfBounds(y + yy + counterY, x + xx + counterX)) {
                return false;
            } else {
                try {
                    if (pieces[y + yy + counterY][x + xx + counterX].equalsSort(sort)) {
                        return repeat(sort, y, x, yy, xx, trigger, ++index);
                    } else {
                        return false;
                    }
                } catch (NullPointerException e) {
                    return false;
                }
            }
        }
    }

    private boolean outOfBounds(int y, int x) {
        return pieces.length <= y || pieces.length <= x || y < 0 || x < 0;
    }

    public boolean draw() {
        for (Piece[] piece : pieces) {
            for (Piece value : piece) {
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getSize() {
        return Board.width;
    }


    public Piece[][] getPieces() {
        return this.pieces;
    }

    public void setPiece(int y, int x, Sort sort) {
        this.pieces[y][x] = new Piece(sort, y, x);
    }

    public void setPieceNull(int y, int x) {
        this.pieces[y][x] = null;
    }
}
