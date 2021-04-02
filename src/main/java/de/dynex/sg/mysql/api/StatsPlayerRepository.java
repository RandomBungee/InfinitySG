package de.dynex.sg.mysql.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsPlayerRepository implements StatsPlayer {
  private final Connection connection;

  private StatsPlayerRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(
    String uniqueId,
    int kills,
    int deaths
  ) {
    try {
      PreparedStatement preparedStatement =
        connection.prepareStatement("INSERT INTO player_stats (unique_id,kills,deaths) VALUES (?,?,?);");
      createStatement(preparedStatement, uniqueId, kills, deaths);
      updateAndClose(preparedStatement);
    } catch (SQLException cantCreatePlayer) {
      System.err.println("Can´t create Player in MySQL-Database: " + cantCreatePlayer.getMessage());
    }
  }

  private void createStatement(
    PreparedStatement preparedStatement,
    String uniqueId,
    int kills,
    int deaths
  ) throws SQLException {
    preparedStatement.setString(1, uniqueId);
    preparedStatement.setInt(2, kills);
    preparedStatement.setInt(3, deaths);
  }

  @Override
  public void change(
    String uniqueId,
    int kills,
    int deaths
  ) {
    try {
      PreparedStatement preparedStatement =
        connection.prepareStatement("UPDATE player_stats SET kills = ?, deaths = ? WHERE unique_id = ?");
      changeStatement(preparedStatement, uniqueId, kills, deaths);
      updateAndClose(preparedStatement);
    } catch (SQLException cantChangePlayer) {
      System.err.println("Can´t change Player in MySQL-Database: " + cantChangePlayer.getMessage());
    }
  }

  private void changeStatement(
    PreparedStatement preparedStatement,
    String uniqueId,
    int kills,
    int deaths
  ) throws SQLException {
    preparedStatement.setInt(1, kills);
    preparedStatement.setInt(2, deaths);
    preparedStatement.setString(3, uniqueId);
  }

  @Override
  public int kills(String uniqueId) {
    try {
      PreparedStatement preparedStatement =
        connection.prepareStatement("SELECT * FROM stats_player WHERE unique_id = ?");
      return killsStatement(preparedStatement, uniqueId);
    } catch (SQLException cantCatchKills) {
      System.err.println("Can´t catch Kills form MySQL-Database: " + cantCatchKills.getMessage());
    }
    return 0;
  }

  private int killsStatement(
    PreparedStatement preparedStatement,
    String uniqueId
  ) throws SQLException {
    preparedStatement.setString(1, uniqueId);
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next()) {
      return resultSet.getInt("kills");
    }
    return 0;
  }

  @Override
  public int deaths(String uniqueId) {
    try {
      PreparedStatement preparedStatement =
        connection.prepareStatement("SELECT * FROM stats_player WHERE unique_id = ?");
      return killsStatement(preparedStatement, uniqueId);
    } catch (SQLException cantCatchKills) {
      System.err.println("Can´t catch Deaths form MySQL-Database: " + cantCatchKills.getMessage());
    }
    return 0;
  }

  private int deathsStatement(
    PreparedStatement preparedStatement,
    String uniqueId
  ) throws SQLException {
    preparedStatement.setString(1, uniqueId);
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next()) {
      return resultSet.getInt("deaths");
    }
    return 0;
  }

  @Override
  public int rank(String uniqueId) {
    try {
      PreparedStatement preparedStatement =
        connection.prepareStatement("SELECT * FROM Stats ORDER BY Kills DESC WHERE unique_id = ?");
      return rankStatement(preparedStatement, uniqueId);
    } catch (SQLException cantSortRank) {
      System.err.println("Can´t sort Rank by Kills: " + cantSortRank.getMessage());
    }
    return 0;
  }

  public int rankStatement(
    PreparedStatement preparedStatement,
    String uniqueId
  ) throws SQLException {
    int count = -1;
    preparedStatement.setString(1, uniqueId);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      count = resultSet.getRow();
      break;
    }
    return count;
  }

  private void updateAndClose(PreparedStatement preparedStatement) throws SQLException {
    preparedStatement.executeUpdate();
    preparedStatement.close();
  }

  public static StatsPlayerRepository create(Connection connection) {
    return new StatsPlayerRepository(connection);
  }
}
