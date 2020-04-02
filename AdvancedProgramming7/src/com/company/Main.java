package com.company;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    private static void clearScreen() {
        for(int i = 0; i <= 15; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of tokens = ");
	    int tokens = scanner.nextInt();

	    System.out.print("Max value (m) = ");
	    int maxValue = scanner.nextInt();

	    System.out.print("Winning length = ");
	    int length = scanner.nextInt();

	    Board board = new Board(tokens, maxValue, length);

	    clearScreen();

	    System.out.println("Initial board: ");
	    System.out.println(board);
	    System.out.println();

	    Game game = new Game(board);

	    System.out.print("Number of players = ");
	    int numberOfPlayers = scanner.nextInt();

	    for(int i = 1; i <= numberOfPlayers; i++) {
	        System.out.print("Player " + i + ": ");
	        String playerName = scanner.next();

	        System.out.println("Type (1 - mutual, 2 - random, 3 - smart): ");
	        int playerType = scanner.nextInt();

            if (playerType == 1) {
                ManualPlayer player = new ManualPlayer(playerName);
                game.addPlayer(player);
            } else {
                System.out.println("Wrong type! Reintroduce the info about this player");
                i--;
            }
        }

	    clearScreen();
	    System.out.println("Prepare...");

	    for(int i = 3; i > 0; --i) {
	        System.out.println(i + "...");
	        sleep(1000);
        }

	    System.out.println("GO!");

	    sleep(1000);
    }
}
