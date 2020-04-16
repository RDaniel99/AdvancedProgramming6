package com.company;

public class Charts {
    private int albumId;
    private int ranking;

    public Charts(int _albumId, int _ranking) {
        albumId = _albumId;
        ranking = _ranking;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getRanking() {
        return ranking;
    }
}
