package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private Connection con = null;

    public PlayerController() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    @GetMapping
    public void getPlayers() throws SQLException {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                "student", "STUDENT");

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from players");
        while(rs.next()) {
            System.out.println(rs.getString("playerName"));
        }
    }

    @PostMapping
    public int createPlayer(@RequestParam String name) throws SQLException {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                "student", "STUDENT");

        Statement stmt = con.createStatement();
        String query = "insert into players(playersName) values(\'" + name + "\')";

        return stmt.executeUpdate(query);
    }

    @PutMapping("/{oldName}")
    public int updatePlayer(@PathVariable String oldName, @RequestParam String newName) throws SQLException {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                "student", "STUDENT");

        Statement stmt = con.createStatement();
        String query = "update players set playerName = " + "\'" + newName + "\' WHERE playerName = " + "\'" + oldName + "\'";

        return stmt.executeUpdate(query);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deletePlayer(@PathVariable String name) throws SQLException {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                "student", "STUDENT");

        Statement stmt = con.createStatement();
        String query = "delete from players where playerName = " + "\'" + name + "\'";

        int rs = stmt.executeUpdate(query);
        if(rs > 0) return new ResponseEntity<>("Player removed", HttpStatus.OK);

        return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
    }
}
