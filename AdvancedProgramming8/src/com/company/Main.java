package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database db = Database.getInstance();

        //db.predefinedCreateTables();
        //db.insertSomeData();
        ArtistController Carl = new ArtistController(null, null);
        if(Carl.findByName("Carl")) {
            System.out.println(Carl.getmName() + " " + Carl.getmCountry());
        }
        else {
            System.out.println("Artist not found");
        }

        AlbumController AlbumBob = new AlbumController(null, 0, 0);
        if(AlbumBob.findByArtistId(2)) {
            System.out.println(AlbumBob.getmName() + " " + AlbumBob.getmReleaseYear());
        }
        else {
            System.out.println("The given artist doesn't have any album");
        }
    }
}
