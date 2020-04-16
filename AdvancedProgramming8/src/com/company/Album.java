package com.company;

public class Album {
    private int id;
    private int artistId;
    private String nume;
    private int releaseYear;

    public Album(int _id, int _artistId, String _nume, int _releaseYear) {
        id = _id;
        artistId = _artistId;
        nume = _nume;
        releaseYear = _releaseYear;
    }

    public String getNume() {
        return nume;
    }

    public int getId() {
        return id;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
