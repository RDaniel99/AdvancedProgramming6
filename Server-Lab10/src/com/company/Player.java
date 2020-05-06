package com.company;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Player {
    private int playerId;
    private int gameId;
    ClientThread clientThread;
    ArrayList<String> notifications;

    public Player(GameServer server, Socket socket) {
        clientThread = new ClientThread(server, socket, this);
        gameId = -1;
        playerId = -1;
        notifications = new ArrayList<>();
    }

    public void start() {
        clientThread.start();
    }

    public int getGameId() {
        return gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Player)) {
            return false;
        }

        Player player = (Player) obj;

        return player.getPlayerId() == getPlayerId();
    }

    public void addNotification(String notification) throws IOException {
        notifications.add(notification);
        clientThread.printNotifications();
    }

    public boolean haveNotifications() {
        return notifications.size() > 0;
    }

    public String getNotification() {
        if(haveNotifications()) {
            String notif = notifications.get(0);
            notifications.remove(0);
            return notif;
        }

        return "";
    }
}
