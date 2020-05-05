package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    public static final int PORT = 8100;
    ServerSocket serverSocket = null;
    ArrayList<Player> players;
    ArrayList<Game> games;
    private int totalPlayers = 0;
    private int totalGames = 0;

    public GameServer() throws IOException {
        players = new ArrayList<Player>();
        games = new ArrayList<Game>();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();

                Player newPlayer = new Player(this, socket);
                totalPlayers++;
                newPlayer.setPlayerId(totalPlayers);
                players.add(newPlayer);
                // Execute the client's request in a new thread
                newPlayer.start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            assert serverSocket != null;
            serverSocket.close();
        }
    }

    public void removePlayer(Player player) {
        if(player.getGameId() > -1) {
            for(Game game: games) {
                if (game.getGameId() == player.getGameId()) {
                    game.exit(player);
                    if(game.totalPlayers() == 0) {
                        games.remove(game);
                    }

                    break;
                }
            }
        }

        players.remove(player);
    }

    public String getGames() {
        StringBuilder buffer = new StringBuilder();

        for(Game game: games) {
            buffer.append("Game ").append(game.getGameId());
            buffer.append(" | Total players: ").append(game.totalPlayers());
            buffer.append('\n');
        }

        if(buffer.toString().isEmpty()) {
            buffer.append("No games created\n");
        }

        return buffer.toString();
    }

    public String addGame(Player player) {
        if(player.getGameId() > 0) {
            return "You already are in a game.\n";
        }

        Game game = new Game(player);
        totalGames++;
        player.setGameId(totalGames);
        game.setGameId(totalGames);
        games.add(game);

        return "Game with id = " + totalGames + " is created and you are in. Waiting for other players\n";
    }
}
