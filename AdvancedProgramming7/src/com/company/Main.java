package com.company;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class Main {
    public static final Object lock = new Object();

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

	        System.out.println("Type (1 - manual, 2 - random, 3 - smart): ");
	        int playerType = scanner.nextInt();
            Player player;
	        switch(playerType) {
                case 1:
                    player = new ManualPlayer(playerName);
                    game.addPlayer(player);
                    break;
                case 2:
                    player = new RandomPlayer(playerName);
                    game.addPlayer(player);
                    break;
                case 3:
                    player = new SmartPlayer(playerName);
                    game.addPlayer(player);
                    break;
                default:
                    System.out.println("Wrong type! Reintroduce the info about this player");
                    i--;
                    break;
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

	    System.out.println(game.getPlayer(0).getName() + " starts!");
        while(!game.isGameOver()) {
            int mutare = -1;

            game.printOptions();
            Player player = game.getPlayer(game.getCurrPlayer());
            if(player.getType() == 1) {
                ManualPlayer currPlayer = ((ManualPlayer) player);
                currPlayer.start();
                synchronized (lock) {
                    lock.wait();
                }

                mutare = currPlayer.getMove();
            }
            else if(player.getType() == 2) {
                RandomPlayer currPlayer = ((RandomPlayer) player);
                currPlayer.setTokens(game.getTotalTokens());
                currPlayer.start();
                synchronized (lock) {
                    lock.wait();
                }

                mutare = currPlayer.getMove();
            }
            else {
                SmartPlayer currPlayer = ((SmartPlayer) player);
                currPlayer.setTokens(game.getTokens());
                currPlayer.setBlankOnBoard(game.getTotalTokens() - game.getTokens().size());
                currPlayer.start();
                synchronized (lock) {
                    lock.wait();
                }

                mutare = currPlayer.getMove();
            }

            if(game.canMove(mutare)) {
                game.move(mutare);
            }
            else {
                System.out.println("Wrong move. Re-move");
            }


            sleep(2000);
            clearScreen();
        }

        boolean flag = false;
        for(int i = 0; i < numberOfPlayers; i++) {
            Player player = game.getPlayer(i);

            if(player.getScore() >= length) {
                System.out.println(player.getName() + " wins! His tokens: ");
                System.out.println(player);
                flag = true;
                break;
            }
        }

        if(!flag) {
            System.out.println("Draw. Scores: ");

            for(int i = 0; i < numberOfPlayers; i++) {
                Player player = game.getPlayer(i);

                System.out.println("Player " + player.getName() + ": " + player.getScore());
            }
        }

        boolean debug = true;
        if(debug) {
            for(int i = 0; i < numberOfPlayers; i++) {
                System.out.println(game.getPlayer(i));
                System.out.println("Score: " + game.getPlayer(i).getScore());
            }
        }
    }
}
