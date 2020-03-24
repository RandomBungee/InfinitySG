package de.dynex.sg.mysql;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:21
*/

import de.dynex.sg.InfinitySG;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

  public String username;
  public String password;
  public String database;
  public String host;
  public String port;
  public Connection connection;

  public MySQL(String username, String password, String database, String host, String port) {
    this.username = username;
    this.password = password;
    this.database = database;
    this.host = host;
    this.port = port;
  }

  public void connect() {
    if (connection == null) {
      try {
        connection = (Connection) DriverManager.getConnection(
            "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true",
            this.username, this.password);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void close() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      connection = null;
    } else {
      System.out.println("Es existiert keine Verbindung!");
    }
  }


  public boolean isConnected() {
    return connection != null;
  }

  public void createTable() {
    if (isConnected()) {
      try {
        connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS Stats(UUID VARCHAR(100), Kills INT(100), Deaths INT(100))")
            .executeUpdate();
        System.out.println("[Succsesfully] Tables was created in Database");
      } catch (SQLException e) {
        System.out.println("[Error] Error while creating table" + e);
      }
    }
  }

  public void update(String gry) {
    if (isConnected()) {
      try {
        connection.createStatement().executeUpdate(gry);
      } catch (SQLException e) {
        System.out.println("[Error] Error while updating query" + e);
      }
    }
  }

  public ResultSet getResult(String gry) {
    if (isConnected()) {
      try {
        return connection.createStatement().executeQuery(gry);
      } catch (SQLException e) {
        System.out.println("[Error] Error while getting Result" + e);
      }
    }
    return null;
  }

}
