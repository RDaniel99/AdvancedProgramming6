package com.company;

public class Game {
    private Board board;
    private int gameId;
    private Player player1;
    private Player player2;
    private int whoTurns;

    public Game(Player player) {
        player1 = player;
        player2 = null;
        whoTurns = 1;

        board = new Board();
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean join(Player player) {
        if(gameId < 0)
            return false;

        if(player2 == null) {
            player2 = player;
            player.setGameId(gameId);
            return true;
        }

        return false;
    }

    public int getWhoTurns() {
        return whoTurns;
    }

    public void nextPlayer() {
        whoTurns = 3 - whoTurns;
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

        if(ord == getWhoTurns()) {
            if (ord > 0) {
                if (ord == 1) return board.makeMove(row, column, 'X');
                if (ord == 2) return board.makeMove(row, column, 'O');
            }
        }

        return false;
    }

    public String displayBoard() {
        return board.toString();
    }

    public int totalInGame() {
        if(player2 == null) {
            if(player1 == null) {
                return 0;
            }

            return 1;
        }

        return 2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getOtherPlayer(Player player) {
        if(isPlayerInGame(player) == 0) {
            return null;
        }

        if(player == player1) return player2;

        return player1;
    }
}
