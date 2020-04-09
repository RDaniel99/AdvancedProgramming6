package com.company;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Database {
    private static Connection con;
    private static Database instance;

    public static Database getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Database() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","STUDENT");
    }

    public Connection getConnection() {
        return con;
    }

    public void insertSomeData() throws SQLException {
        Statement stmt = con.createStatement();
        /*
        String[] names = {"Ana", "Bob", "Carl", "Dan"};

        for(int i = 1; i <= 4; i++) {
            String sql = "insert into artists(IdArtist, NUME, COUNTRY) values(" + i + ", \'" + names[i - 1] + "\', \'Romania\')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }

        */

        String[] names = {"Album1", "Album2", "Album3"};

        for(int i = 1; i <= 3; i++) {
            String sql = "insert into albums(IdAlbum, NUME, ARTIST_ID, RELEASE_YEAR) values(" +
                    i + ", \'" + names[i - 1] + "\', " + i + ", " + (1990 + i) + ")";

            System.out.println(sql);
            stmt.executeUpdate(sql);
        }
    }

    public void predefinedCreateTables() throws SQLException {
        Statement stmt=con.createStatement();
        String sql = "create table ARTISTS (\n" +
                "  IdArtist number not null,\n" +
                "  NUME varchar2(100) not null,\n" +
                "  COUNTRY varchar2(100) null\n" +
                ")";

        stmt.executeUpdate(sql);

        sql = "create table ALBUMS (\n" +
                "  IdAlbum number not null,\n" +
                "  NUME varchar2(100) not null,\n" +
                "  ARTIST_ID number not null,\n" +
                "  RELEASE_YEAR number null\n" +
                ")";

        stmt.executeUpdate(sql);
    }
}
