package be.kdg.tic_tac_toe.model;

import java.util.Random;

public class NPC extends Player {
    //de vars initializeren
    private static final int MIN = -1000;
    private static final int MAX = 1000;
    private Random random;
    private final boolean ai;


    NPC(boolean ai) {
        //geef een naam aan de ai genaamd bot
        super("Bot");
        // de ai die meegegeven word is de ai van de vars
        this.ai = ai;
        // als deze ai niet degene is die meegegeven is dan moet je een random gebruiken
        if (!ai){
             this.random = new Random();
        }
    }

    String playNPC(Board board, Sort currentSort) {
        //als de keuze ai is dan moet de minimax aangesproken worden en de beste move uitgevoerd worden
        if (ai){
           return this.bestMove(board, currentSort);
            //word de ai niet aangesproken dan moet de makkelijke computer spelen en dus een gewone random gooien voor de plaats
        }else{
           return this.randomMove(board, currentSort);
        }

    }

    private String randomMove(Board board, Sort currentSort){
        // de size van het bord is zo groot als de lengte van de pieces aan mekaar
      int size = board.getPieces().length;
      int x;
      int y;
      //zolang de pieces niet gelijk zijn aan null dan moet je een random x en y waarde kiezen binnen de size om een piece op te zetten
      do {
           x = random.nextInt(size);
           y = random.nextInt(size);
      }while (board.getPieces()[y][x] != null);
    // de functie place word aangeroepen en de current sort(x of O) word geplaatst op de gekozen y en x coordinaten
      board.place(currentSort, y, x);
        return String.format("%s:%d-%d;", currentSort, y, x);
    }

    private boolean movesAble(Board board) {
        //het bord opnieuw tekenen met al de veranderingen
        return board.draw();
    }

    private int evaluation(Board board, Sort ownSort, Sort opponent) {
        //minimax
        if (board.win(ownSort)) {
            return +10;
        } else if (board.win(opponent)) {
            return -10;
        }
        return 0;
    }

    //determine the best value to play
    private int minimax(Board board, int depth, boolean max, Sort ownSort, int alpha, int beta) {
        Piece[][] pieces = board.getPieces();

        Sort opponent = Sort.oppositSort(ownSort);
        int score;
        //get the score of the current board
        if (max) {
            score = evaluation(board, ownSort, opponent);
        } else {
            score = -evaluation(board, opponent, ownSort);
        }


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
                        int value = minimax(board, ++depth, false, ownSort, alpha, beta);

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
                        int value = minimax(board, ++depth, true, ownSort, alpha, beta);

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

    private String bestMove(Board board, Sort ownSort) {
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
        board.place(ownSort, column, row);
        return String.format("%s:%d-%d;", ownSort, column, row);
    }
}