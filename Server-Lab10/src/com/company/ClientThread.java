package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket socket = null ;
    private GameServer server = null;
    private Player parent = null;
    public ClientThread (GameServer server, Socket socket, Player parent) {
        this.socket = socket ;
        this.server = server;
        this.parent = parent;
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

    public String decodeReq(String request) {
        if(request.equals("stop")) {
            ExecuteStopCommand();
            return "exit";
        }

        System.out.println("Server received the request " + request);
        return "Executing command " + request + "!";
    }

    public void ExecuteStopCommand() {
        server.removePlayer(parent);
        System.out.println("Thread stopped");
    }
}
