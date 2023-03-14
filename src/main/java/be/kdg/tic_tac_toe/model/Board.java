package be.kdg.tic_tac_toe.model;

/**
 * Holds the bord that is used by the @game class and the MVP to display the game.
 */
public class Board {

    private static int width;
    private static int length;
    private final Piece[][] pieces;

    /**
     * the constructor of the board class
     *
     * @param width  this is the width of the board
     * @param length this is the length of the board
     */
    Board(int width, int length) {
        if (width == length && width == 3 || width == 5 || width == 7) {
            Board.width = width;
            Board.length = length;
        } else {
            Board.width = 3;
            Board.length = 3;
        }
        this.pieces = new Piece[Board.width][Board.length];
    }

    /**
     * getter for the board size
     *
     * @return the size of the board
     */
    static int getSize() {
        return Board.width;
    }

    /**
     * function to check if the move is valid
     *
     * @param y the y coordinate of the move
     * @param x the x coordinate of the move
     * @return boolean if the move is valid or not
     */
    boolean validMove(int y, int x) {
        return this.pieces[y][x] == null;
    }

    /**
     * function to place a piece on the board
     *
     * @param currentSort the sort of the piece
     * @param y           the y coordinate of the move
     * @param x           the x coordinate of the move
     */
    void place(Sort currentSort, int y, int x) {
        this.pieces[y][x] = new Piece(currentSort, y, x);
    }

    /**
     * function to check if the game is won
     *
     * @param sort the sort of the piece
     * @return boolean if the game is won or not
     */
    boolean win(Sort sort) {
        //define the trigger
        int trigger = (getSize() == 3) ? 3 : 4;

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

    /**
     * function is used by the win function to run down the rest of the board
     * @see Board#win(Sort)
     *
     * @param sort the sort of the piece
     * @param y    the y coordinate of the move (int)
     * @param x    the x coordinate of the move (int)
     * @param yy   the y coordinate of the move (int)
     * @param xx   the x coordinate of the move (int)
     * @param trigger the trigger (int)
     * @param index the index (int)
     * @return boolean if the game is won or not
     */
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

    /**
     * Used by the repeat function to check if the move is out of bounds
     * @see Board#repeat(Sort, int, int, int, int, int, int)
     *
     * @param y y coordinate (int)
     * @param x x coordinate (int)
     * @return boolean if the move is out of bounds or not
     */
    private boolean outOfBounds(int y, int x) {
        return pieces.length <= y || pieces.length <= x || y < 0 || x < 0;
    }

    /**
     * function to check if the game is a draw
     *
     * @return boolean if the game is a draw or not
     */
    boolean draw() {
        for (Piece[] piece : pieces) {
            for (Piece value : piece) {
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *get the array of pieces
     *
     * @return the board in its pieces array
     */
    public Piece[][] getPieces() {
        return this.pieces;
    }

    /**
     * function to set a piece on the board
     * Do not use this function to place a piece on the board, use the place function instead
     * @see Board#place(Sort, int, int)
     * Function is used by the NPC's MiniMax algorithm to get the best move
     * @see NPC
     *
     * @param y the y coordinate of the piece (int)
     * @param x the x coordinate of the piece (int)
     * @param sort the sort of the piece (Sort)
     */
    void setPiece(int y, int x, Sort sort) {
        this.pieces[y][x] = new Piece(sort, y, x);
    }

    /**
     * function to set a piece on the board to null
     * Do not use this function to remove a piece from the board
     * Function is used by the NPC's MiniMax algorithm to get the best move
     * @see NPC
     *
     * @param y the y coordinate of the piece (int)
     * @param x the x coordinate of the piece (int)
     */
    void setPieceNull(int y, int x) {
        this.pieces[y][x] = null;
    }
}
