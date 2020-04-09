package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class AlbumController {
    private String mName;
    private int mArtistId;
    private int mReleaseYear;

    public AlbumController(String name, int artistId, int releaseYear) {
        mName = name;
        mArtistId = artistId;
        mReleaseYear = releaseYear;
    }

    public boolean findByArtistId(int artistId) throws SQLException, ClassNotFoundException {
        Connection con = Database.getInstance().getConnection();
        Statement stmt = con.createStatement();
        String sql = "select name, release_year from albums where artist_id = " + artistId + ';';
        ResultSet result = stmt.executeQuery(sql);

        if(result.next()) {
            mName = result.getString(0);
            mArtistId = artistId;
            mReleaseYear = parseInt(result.getString(1));
            return true;
        }

        return false;
    }

    public String getmName() {
        return mName;
    }

    public int getmArtistId() {
        return mArtistId;
    }

    public int getmReleaseYear() {
        return mReleaseYear;
    }
}
