package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtistController {
    private int mId;
    private String mName;
    private String mCountry;

    public ArtistController(String name, String country) {
        mName = name;
        mCountry = country;
    }

    public int getRankingInChart(String name) throws SQLException, ClassNotFoundException {
        if(findByName(name)) {
            Connection con = Database.getInstance().getConnection();
            Statement stmt = con.createStatement();
            String sql = "select albumid, ranking from charts order by ranking";
            ResultSet result = stmt.executeQuery(sql);

            int position = 1;
            while(result.next()) {
                if(result.getInt(1) == mId) {
                    break;
                }

                position++;
            }

            return position;
        }

        return -1;
    }

    public boolean findByName(String name) throws SQLException, ClassNotFoundException {
        Connection con = Database.getInstance().getConnection();
        Statement stmt = con.createStatement();
        String sql = "select nume, country, idartist from artists where nume = " + '\'' + name + '\'';
        ResultSet result = stmt.executeQuery(sql);

        if(result.next()) {
            mId = result.getInt(3);
            mName = name;
            mCountry = result.getString(2);
            return true;
        }

        return false;
    }

    public String getmCountry() {
        return mCountry;
    }

    public String getmName() {
        return mName;
    }

    public int getmId() {
        return mId;
    }
}
