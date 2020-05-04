package com.company;

public class Game {
    private Board board;
    private int gameId;
    private Player player1;
    private Player player2;


    public Game(Player player) {
        player1 = player;
        player2 = null;

        board = new Board();
    }

    public int getGameId() {
        return gameId;
    }

    public boolean join(Player player) {
        if(player2 == null) {
            player2 = player;
            return true;
        }

        return false;
    }

    public int totalPlayers() {
        if(player1 == null) {
            return 0;
        }

        if(player2 == null) {
            return 1;
        }

        return 2;
    }

    public int isPlayerInGame(Player player) {
        if(player == player1) return 1;
        if(player == player2) return 2;

        return 0;
    }

    public boolean exit(Player player) {
        if(isPlayerInGame(player) > 0) {
            if(player1 == player) {
                player1 = player2;
            }

            player2 = null;
            board.reset();
            return true;
        }

        return false;
    }

    public boolean makeMove(Player player, int row, int column) {
        int ord = isPlayerInGame(player);

        if(ord > 0) {
            if(ord == 1) return board.makeMove(row, column, 'X');
            if(ord == 2) return board.makeMove(row, column, 'O');
        }

        return false;
    }
}