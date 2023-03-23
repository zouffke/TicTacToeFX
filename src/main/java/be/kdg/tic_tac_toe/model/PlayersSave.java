package be.kdg.tic_tac_toe.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

import static java.nio.file.StandardOpenOption.APPEND;

public class PlayersSave {
    private static final Path players = Paths.get("resources" + File.separator + "saveFiles" + File.separator + "players.txt");

    private final TreeSet<String> playersMap;
    private static final String template = """
            //\\\\PLAYERS FILE//\\\\
            //\\\\DO NOT EDIT THIS FILE MANUALLY//\\\\
            //\\\\THIS FILE IS AUTOMATICALLY GENERATED//\\\\
            //\\\\REMOVING LINES WILL CAUSE ERRORS EVEN BLANK LINES//\\\\
            //\\\\ANY CHANGE CAN CAUSE CORRUPTION OF YOUR SAVE FILE//\\\\
            //\\\\WHEN YOU EDIT THIS FILE, MAKE SURE YOU HAVE A BACKUP AND ALWAYS LEAVE ONE LINE EMPTY AT THE BOTTOM OF THIS FILE//\\\\
            ========================================================================================================================
            """;

    public PlayersSave() throws SaveFileException {
        playersMap = new TreeSet<>((o1, o2) -> {
            int compare = SaveFiles.getSubString(o2, 1).compareTo(SaveFiles.getSubString(o1, 1));
            if (compare == 0) {
                return SaveFiles.getSubString(o1, 0).compareTo(SaveFiles.getSubString(o2, 0));
            } else {
                return compare;
            }
        });
        SaveFiles.checkFile(players);
        this.fillList();
    }

    void writePlayers(String playerName1, String playerName2, boolean human) throws SaveFileException {
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
                    if (checkName(line, playerName1, human)) {
                        name1 = true;
                    } else if (checkName(line, playerName2, human)) {
                        name2 = true;
                    }
                }
            }
            //if the name is not in the file, add it
            if (!name1) {
                Files.write(players, String.format("player=%s;score=0%n", playerName1).getBytes(), APPEND);
            }
            if (!name2 && human) {
                Files.write(players, String.format("player=%s;score=0%n", playerName2).getBytes(), APPEND);
            }

            this.fillList();
        } catch (IOException e) {
            throw new SaveFileException("File could not be written: " + players.getFileName());
        }
    }

    private boolean checkName(String line, String name, boolean human) {
        return this.getPlayer(line).equalsIgnoreCase(name.replace(" ", "")) && human;
    }

    private void fillList() throws SaveFileException {
        try (Scanner scanner = new Scanner(players)) {
            //loop through the file
            while (scanner.hasNextLine()) {
                //read the line
                String line = scanner.nextLine();
                //check if the line contains the player and score
                if (line.contains("player") && line.contains("score")) {
                    playersMap.add(line);
                }
            }
        } catch (IOException e) {
            throw new SaveFileException("An Error occurred in: " + players.getFileName());
        }
    }

    void updateScore(boolean draw, Player winner, Contribution contribution) throws SaveFileException {
        int score = 0;
        if (draw) {
            score = 1;
        } else {
            if (contribution.getPlayer(2) instanceof NPC && winner instanceof Human) {
                score = 3;
            } else if (contribution.getPlayer(2) instanceof Human) {
                score = 2;
            }
        }

        try (Scanner scanner = new Scanner(players)) {
            //loop through the file
            while (scanner.hasNextLine()) {
                //read the line
                String line = scanner.nextLine();

                //check if the line contains the player and score
                if (line.contains("player") && line.contains("score")) {
                    if (draw) {
                        if (checkName(line, contribution.getName(1), true)) {
                            this.addScore(contribution.getName(1), score);
                        } else if (checkName(line, contribution.getName(2), contribution.getPlayer(2) instanceof Human)) {
                            this.addScore(contribution.getName(2), score);
                        }
                    } else {
                        if (checkName(line, winner.toString(), winner instanceof Human)) {
                            this.addScore(winner.toString(), score);
                        }
                    }
                }
            }

            Files.write(players, template.getBytes());
            for (String line : playersMap) {
                Files.write(players, String.format("%s%n", line).getBytes(), APPEND);
            }

        } catch (IOException e) {
            throw new SaveFileException("An Error occurred in: " + players.getFileName());
        }
    }

    private void addScore(String player, int score) {
        for (int i = 0; i < playersMap.size(); i++) {
            if (checkName(playersMap.toArray()[i].toString(), player, true)) {
                String line = playersMap.toArray()[i].toString();
                int newScore = this.getScore(line) + score;
                playersMap.remove(line);
                playersMap.add(String.format("player=%s;score=%d", player, newScore));
                return;
            }
        }
    }

    private String getPlayer(String line) {
        return SaveFiles.getSubString(line, 0);
    }

    private int getScore(String line) {
        return Integer.parseInt(SaveFiles.getSubString(line, 1));
    }

    public String getPlayer(int index) throws SaveFileException {
        try {
            return SaveFiles.getSubString(playersMap.toArray()[index].toString(), 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SaveFileException("There are no players in the file to display here.");
        }
    }

    public int getScore(int index) {
        return Integer.parseInt(SaveFiles.getSubString(playersMap.toArray()[index].toString(), 1));
    }

    public void clearSave() throws SaveFileException {
        try {
            Files.write(players, template.getBytes());
        } catch (IOException e) {
            throw new SaveFileException("An Error occurred in: " + players.getFileName());
        }
    }
}
