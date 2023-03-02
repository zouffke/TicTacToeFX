package be.kdg.tic_tac_toe.model;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    Contribution contribution;
    Board board;
    boolean loop;

    public Game(int boardChoice, int playerChoice) {

        this.contribution = setPlayerTypes(playerChoice);

        //TODO loop the game
        //do {
            this.board = setBoard(boardChoice);

            play(contribution, board, keyboard);

            //loop = again(keyboard);

        //} while (loop);
    }

    public static Contribution setPlayerTypes(int option) {
        return getPlayers(, option);
    }

    public static Contribution getPlayers(int choice) {
        if (choice == 1) {
            String name1;
            String name2;

            //TODO give the option to change player name
                name1 = "Temp 1";

            do {
                System.out.print("Geef de naam van speler 2: ");
                name2 = keyboard.nextLine();
            } while (name2.isEmpty() || !Character.isAlphabetic(name2.charAt(0)));

            return new Contribution(name1, name2);
        } else if (choice == 2){
            String name;

            do {
                System.out.print("Geef de naam van speler 1: ");
                name = keyboard.nextLine();
            } while (name.isEmpty() || !Character.isAlphabetic(name.charAt(0)));

            return new Contribution(name);
        } else {
            return;
        }
    }

    public static Board setBoard(int option) {

        switch (option) {
            case 2 -> {
                return new Board(5, 5);
            }
            case 3 -> {
                return new Board(7, 7);
            }
            default -> {
                return new Board(3, 3);
            }
        }
    }


    public static void play(Contribution contribution, Board board, Scanner keyboard) {
        int count = 1;
        boolean validMove;
        Player currentPlayer;
        Sort currentSort;

        contribution.setSorts();

        System.out.printf("\n%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));

        board.drawBoard();

        do {
            if (count++ == 1) {
                currentPlayer = contribution.getSort(1).equals("X") ? contribution.getPlayer(1) : contribution.getPlayer(2);
                currentSort = Sort.X;
                System.out.printf("\n%s's beurt;\n", currentPlayer.getNAME());
            } else {
                currentPlayer = contribution.getSort(1).equals("O") ? contribution.getPlayer(1) : contribution.getPlayer(2);
                currentSort = Sort.O;
                System.out.printf("\n%s's beurt;\n", currentPlayer.getNAME());
                count = 1;
            }

            if (currentPlayer instanceof NPC npc) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    npc.playNPC(board, currentSort);
                }

            } else {
                do {
                    validMove = board.place(splisten(keyboard), currentSort, true);
                } while (!validMove);


                board.drawBoard();


            }
        } while (!winCheck(board, currentPlayer, currentSort));
    }

    public static Coordinaat splisten(Scanner keyboard) {
        boolean validMove;
        String input;
        do {
            validMove = false;
            System.out.print("Waar wilt u een stuk plaatsen?\nLocatie: ");
            input = keyboard.nextLine();
            if (!input.isEmpty() && input.matches("[0-9]" + "-" + "[0-9]")) {
                validMove = true;
            } else {
                System.out.println("Verkeerde notatie");
            }

        } while (!validMove);
        int y = Integer.parseInt(input.substring(0, 1));
        int x = Integer.parseInt(input.substring(2, 3));
        return new Coordinaat(y, x);
    }

    public static boolean winCheck(Board board, Player currentPlayer, Sort currentSort) {

        if (board.win(currentSort)) {
            System.out.printf("%s heeft gewonnen\n", currentPlayer.getNAME());
            return true;
        } else if (board.draw()) {
            System.out.println("It's a Draw!\n");
            return true;
        }
        return false;
    }

    public static boolean again(Scanner keyboard) {
        System.out.println("\nWilt u nog eens spelen? Y/N");
        return keyboard.nextLine().toUpperCase().contains("Y");
    }

}


