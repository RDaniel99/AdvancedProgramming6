package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtistController {
    private String mName;
    private String mCountry;

    public ArtistController(String name, String country) {
        mName = name;
        mCountry = country;
    }

    public boolean findByName(String name) throws SQLException, ClassNotFoundException {
        Connection con = Database.getInstance().getConnection();
        Statement stmt = con.createStatement();
        String sql = "select name, country from artists where name = " + '\'' + name + '\'' + ';';
        ResultSet result = stmt.executeQuery(sql);

        if(result.next()) {
            mName = name;
            mCountry = result.getString(1);
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
}
