package com.github.infinitysg.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
  public String username;
  public String password;
  public String database;
  public String host;
  public String port;
  public static Connection connection;

  public MySQL(
    String username,
    String password,
    String database,
    String host,
    String port
  ) {
    this.username = username;
    this.password = password;
    this.database = database;
    this.host = host;
    this.port = port;
  }

  public void connect() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(
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
            "CREATE TABLE IF NOT EXISTS player_stats(unique_id VARCHAR(100), kills INT, deaths INT)")
            .executeUpdate();
        System.out.println("[Succsesfully] Tables was created in Database");
      } catch (SQLException e) {
        System.out.println("[Error] Error while creating table" + e);
      }
    }
  }
}
