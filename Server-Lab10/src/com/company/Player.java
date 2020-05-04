package com.company;

public class Player {
    private int playerId;
    private int gameId;

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
}
