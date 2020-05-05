package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Integer.parseInt;

class ClientThread extends Thread {
    private Socket socket = null ;
    private GameServer server = null;
    private Player parent = null;
    private int parityJoin;
    public ClientThread (GameServer server, Socket socket, Player parent) {
        this.socket = socket ;
        this.server = server;
        this.parent = parent;
        parityJoin = 0;
    }

    public void run () {
        try {
            // Get the request from the input stream: client → server
            while(true) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();

                String answer  = decodeReq(request);

                // Send the response to the oputput stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(answer);
                out.flush();

                if(answer.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) { System.err.println (e); }
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }

    public String decodeReq(String request) throws IOException {
        System.out.println("Server received the request " + request);

        if(isInteger(request)) {
            if(parityJoin == 1) {
                return ExecuteJoinCommand(parseInt(request));
            }
        }

        parityJoin = 0;
        if(request.equals("stop")) {
            return ExecuteStopCommand();
        }

        if(request.equals("show")) {
            return ExecuteShowCommand();
        }

        if(request.equals("create")) {
            return ExecuteCreateCommand();
        }

        if(request.equals("join")) {
            return ExecuteJoinCommand(-1);
        }

        if(request.equals("display")) {
            return ExecuteDisplayCommand();
        }

        return "Unknown command " + request + "!";
    }

    public String ExecuteStopCommand() {
        server.removePlayer(parent);
        System.out.println("Thread stopped");
        return "exit";
    }

    public String ExecuteShowCommand() {
        return server.getGames();
    }

    public String ExecuteCreateCommand() {
        return server.addGame(parent);
    }

    public String ExecuteJoinCommand(int gameId) throws IOException {
        if(parityJoin == 0) {
            parityJoin = 1;
            return "Game id to join: ";
        }

        parityJoin = 0;

        return server.joinGame(parent, gameId);
    }

    public String ExecuteDisplayCommand() {
        return server.displayGame(parent.getGameId());
    }
}
