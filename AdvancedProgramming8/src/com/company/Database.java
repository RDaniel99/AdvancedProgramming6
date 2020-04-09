package com.company;

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

    public void predefinedCreateTables() throws SQLException {
        Statement stmt=con.createStatement();
        String sql = "create table ARTISTS (\n" +
                "  ID number generated by default as identity(start with 1) not null,\n" +
                "  NAME varchar2(100) not null,\n" +
                "  COUNTRY varchar2(100) null,\n" +
                "  primary key (ID)\n" +
                ")";

        stmt.executeUpdate(sql);

        sql = "create table ALBUMS (\n" +
                "  ID number generated by default as identity(start with 1) not null,\n" +
                "  NAME varchar2(100) not null,\n" +
                "  ARTIST_ID number not null,\n" +
                "  RELEASE_YEAR number null,\n" +
                "  foreign key (ARTIST_ID)\n" +
                "  references ARTISTS on delete restrict,\n" +
                "  primary key (ID)\n" +
                ")";

        stmt.executeUpdate(sql);
    }
}
