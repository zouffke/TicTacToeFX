package be.kdg.tic_tac_toe.model;

public class GameException extends Exception {
    //eigen exeption voor als er iets fout ging
    GameException(String message) {
        super(message);
    }
}
