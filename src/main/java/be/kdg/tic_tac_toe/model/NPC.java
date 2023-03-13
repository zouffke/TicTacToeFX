package be.kdg.tic_tac_toe.model;

public class NPC extends Player {
    private static final int MIN = -1000;
    private static final int MAX = 1000;

    NPC() {
        super("Bot");
    }

    void playNPC(Board board, Sort currentSort) {
        this.bestMove(board, currentSort);
    }

    private boolean movesAble(Board board) {
        return board.draw();
    }

    private int evaluation(Board board, Sort ownSort, Sort opponent) {
        if (board.win(ownSort)) {
            return 10;
        } else if (board.win(opponent)) {
            return -10;
        }
        return 0;
    }

    //determine the best value to play
    private int minimax(Board board, int depth, boolean max, Sort ownSort, int alpha, int beta) {
        Piece[][] pieces = board.getPieces();

        //get the score of the current board
        Sort opponent = Sort.oppositSort(ownSort);
        int score = evaluation(board, ownSort, opponent);


        if (score == 10) {
            return score - depth;
        } else if (score == -10) {
            return score + depth;
        } else if (movesAble(board)) {
            return 0;
            //if the NPC is the maximizer
        } else if (max) {
            int best = MIN;

            for (int y = 0; y < pieces.length; y++) {
                for (int x = 0; x < pieces[y].length; x++) {
                    if (pieces[y][x] == null) {
                        //place a temporary piece on the copy board
                        board.setPiece(y, x, ownSort);

                        //calculate the value of this move
                        int value = minimax(board, depth + 1, false, ownSort, alpha, beta);

                        //determine the best move
                        best = Math.max(best, value);
                        alpha = Math.max(alpha, best);

                        //set the piece on the board back to null
                        board.setPieceNull(y, x);

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return best;
            //if the NPC is the minimizer
        } else {
            int best = MAX;

            for (int y = 0; y < pieces.length; y++) {
                for (int x = 0; x < pieces[y].length; x++) {
                    if (pieces[y][x] == null) {
                        //place a temporary piece on the copy board
                        board.setPiece(y, x, opponent);

                        //calculate the value of this move
                        int value = minimax(board, depth + 1, true, ownSort, alpha, beta);

                        best = Math.min(best, value);
                        beta = Math.min(beta, best);

                        //set the piece on the board back to null
                        board.setPieceNull(y, x);

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return best;
        }
    }

    private void bestMove(Board board, Sort ownSort) {
        Piece[][] pieces = board.getPieces();

        boolean max;
        //is the NPC the maximizer?
        max = ownSort.equals(Sort.X);

        int bestVal = MIN;
        int column = -1;
        int row = -1;
        System.out.println("Thinking...");
        for (int y = 0; y < pieces.length; y++) {
            for (int x = 0; x < pieces[y].length; x++) {
                if (pieces[y][x] == null) {

                    board.setPiece(y, x, ownSort);

                    int moveVal = minimax(board, 0, max, ownSort, MIN, MAX);

                    board.setPieceNull(y, x);

                    if (moveVal > bestVal) {
                        row = x;
                        column = y;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println("\nBest move is " + bestVal);
        board.place(ownSort, column, row);
    }
}