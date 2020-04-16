package com.company;

public class Artist {
    private int id;
    private String nume;
    private String country;

    public Artist(int _id, String _nume, String _country) {
        id = _id;
        nume = _nume;
        country = _country;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getNume() {
        return nume;
    }
}
