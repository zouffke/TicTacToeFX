package be.kdg.tic_tac_toe.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.APPEND;

public class Game {
// de functies naar de files waar de scores worden opgeslagen
    private static final Path gamesSave = Paths.get("resources" + File.separator
            + "saveFiles" + File.separator
            + "games.txt");
    private static final Path players = Paths.get("resources" + File.separator
            + "saveFiles" + File.separator
            + "players.txt");
    //vars aanmaken
    private final Board board;
    private final int playerChoice;
    private final int boardChoice;
    private Contribution contribution;
    private boolean validMove;
    private Player currentPlayer;
    private Sort currentSort;
    private int count;
    private final StringBuilder moves;

    public Game(int boardChoice, int playerChoice) {
        //vars initializeren, valid move begint op false omdat ze eerst moeten checkken of het true is
        this.count = 1;
        this.validMove = false;
        this.playerChoice = playerChoice;
        this.boardChoice = boardChoice;
        this.moves = new StringBuilder();

        //als de file gamesSave niet bestaat moet je die aanmaken met de positie van het afgelopen spel
        if (!Files.exists(gamesSave)) {
            try {
                Files.createFile(gamesSave);
                System.out.println("File created: " + gamesSave.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // als de file wel al bestaat moet je dit teruggeven en de positie van het bord bij dat spel opslaan
        } else {
            System.out.println("File already exists: " + gamesSave.getFileName());
        }
    //als de player nog niet opgeslagen is dan moet je die aanmaken met de opgegeven naam
        if (!Files.exists(players)) {
            try {
                Files.createFile(players);
                System.out.println("File created: " + players.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //als de speler al bestaat moet je de score bij die naam wegschrijven
        } else {
            System.out.println("File already exists: " + players.getFileName());
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
        if (choice == 1) {
            //als keuze 1 is dan spelen we pvp en moeten er dus 2 namen gegeven worden
            this.contribution = new Contribution(name1, name2);

        } else if (choice == 2) {
            // als de keuze 2 is dan word de ai op false gezet wat bedenkt dat het de makkelijke is --> random bot
            //TODO change the second option to an easier NPC
            this.contribution = new Contribution(name1, false);
        // als 1 en 2 niet gekozen zijn word de ai op true gezet en gaat de minimax in werking
        } else {
            this.contribution = new Contribution(name1, true);
        }

        this.contribution.setSorts();
        // hier word bepaald wie met wat speelt (X, O)
        System.out.printf("\n%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));
        //vanaf nu worden de gegevens opgeslagen
        this.writePlayers();
        this.initGameSave();
    }

    private void writePlayers() {
        boolean name1 = false;
        boolean name2 = false;
        //create the scanner
        try (Scanner scanner = new Scanner(players)) {
            //loop through the file
            while (scanner.hasNextLine()) {
                //read the line
                String line = scanner.nextLine();
                //check if the line contains the player and score
                if (line.contains("player") && line.contains("score")) {
                    //check if the name is already in the file
                    if (checkPlayerName(line, 1)) {
                        name1 = true;
                    } else if (checkPlayerName(line, 2)) {
                        name2 = true;
                    }
                }
            }
            //if the name is not in the file, add it
            if (!name1) {
                Files.write(players, String.format("player:%s;score:0%n", this.contribution.getName(1)).getBytes(), APPEND);
            }
            if (!name2 && this.contribution.getPlayer(2) instanceof Human) {
                Files.write(players, String.format("player:%s;score:0%n", this.contribution.getName(2)).getBytes(), APPEND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameSave() {
        //TODO deze dingen commentareren
        int gameNumber = 0;
        try (Scanner scanner = new Scanner(gamesSave)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("game") && line.contains("board")) {
                    gameNumber = Integer.parseInt(line.split(";")[0].split(":")[1]);
                    gameNumber++;
                }
            }

            moves.append(String.format("game:%d;dateStamp:%s;player1:%s;AS:%s;player2:%s;AS:%s;board:%dx%d%n"
                    , gameNumber
                    , LocalDateTime.now()
                    , this.contribution.getName(1)
                    , this.contribution.getSort(1)
                    , this.contribution.getName(2)
                    , this.contribution.getSort(2)
                    , Board.getSize()
                    , Board.getSize()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGameProgress(boolean draw) {
        this.saveGameProgress(draw, null);
    }

    public void saveGameProgress(boolean draw, Player winner) {
        try {
            Files.write(gamesSave, String.format("%s%ngameEnd:%s%n", this.moves, draw ? "draw" : winner.toString()).getBytes(), APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateParameters() {
        if (this.count++ == 1) {
            this.currentPlayer = this.contribution.getSort(1).equals("X") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.X;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.toString());
        } else {
            this.currentPlayer = this.contribution.getSort(1).equals("O") ? this.contribution.getPlayer(1) : this.contribution.getPlayer(2);
            this.currentSort = Sort.O;
            System.out.printf("\n%s's beurt;\n", this.currentPlayer.toString());
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
        //checkt of de zet die gemaakt word valid is zo niet gooit een exception
        validMove = board.validMove(y, x);

        if (validMove) {
            this.board.place(this.currentSort, y, x);
            moves.append(String.format("%s:%d-%d;", this.currentSort, y, x));
        } else {
            throw new GameException("This is an invalid move");
        }
    }

    public void addScore(boolean draw) {
        this.addScore(draw, null);
    }

    public void addScore(boolean draw, Player winner) {
        int score = 0;
        if (draw) {
            score = 1;
        } else {
            if (this.contribution.getPlayer(2) instanceof NPC && winner instanceof Human) {
                score = 3;
            } else if (this.contribution.getPlayer(2) instanceof Human) {
                score = 2;
            }
        }

        try (Scanner scanner = new Scanner(players)) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                //read the line
                String line = scanner.nextLine();
                //check if the line contains the player and score
                if (line.contains("player") && line.contains("score")) {
                    if (draw) {
                        if (checkPlayerName(line, 1)) {
                            sb.append(String.format("player:%s;score:%d%n", this.contribution.getName(1), modifyScore(score, line)));
                        } else if (checkPlayerName(line, 2)) {
                            sb.append(String.format("player:%s;score:%d%n", this.contribution.getName(2), modifyScore(score, line)));
                        } else {
                            sb.append(String.format("%s%n", line));
                        }
                    } else if (winner.toString().equalsIgnoreCase(line.split(";")[0].split(":")[1]) && winner instanceof Human) {
                        sb.append(String.format("player:%s;score:%d%n", winner.toString(), modifyScore(score, line)));
                    } else {
                        sb.append(String.format("%s%n", line));
                    }
                } else {
                    sb.append(String.format("%s%n", line));
                }
            }

            Files.write(players, sb.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkPlayerName(String line, int index) {
        return line.split(";")[0].
                split(":")[1].
                replace(" ", "").
                equalsIgnoreCase(this.contribution.
                        getName(index).replace(" ", ""))
                && this.contribution.getPlayer(index) instanceof Human;
    }

    private int modifyScore(int score, String line) {
        return Integer.parseInt(line.split(";")[1].split(":")[1]) + score;
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

    public String getPlayer1() {
        return this.contribution.getName(1);
    }

    public String getPlayer2() {
        return this.contribution.getName(2);
    }

}