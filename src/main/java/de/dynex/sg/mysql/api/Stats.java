package de.dynex.sg.mysql.api;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:21
*/

import de.dynex.sg.InfinitySG;
import de.dynex.sg.utils.UUIDFetcher;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stats {

  private InfinitySG infinitySG;

  public Stats(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  public boolean playerExits(String name) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    try {
      ResultSet st = infinitySG.mySQL.getResult("SELECT * FROM Stats WHERE UUID = '" + uuid + "'");

      return st.next();

    } catch (SQLException e) {
      System.err.println("Ein feheler ist aufegtreten:" + e);
    }
    return false;

  }

  public void createPlayer(String name) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    if (!(playerExits(name))) {
      infinitySG.mySQL.update("INSERT INTO Stats (UUID, Kills, Deaths) VALUES ('" + uuid + "', 0, 0);");
    } else {

    }

  }

  public void setKils(String name, Integer kills) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    if (playerExits(name)) {
      infinitySG.mySQL.update("UPDATE Stats SET Kills= '" + kills + "' WHERE UUID = '" + uuid + "'");
    } else {
      createPlayer(name);
      setKils(name, kills);
    }

  }

  public void setDeaths(String name, Integer death) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    if (playerExits(name)) {
      infinitySG.mySQL.update("UPDATE Stats SET Deaths= '" + death + "' WHERE UUID = '" + uuid + "'");
    } else {
      createPlayer(name);
      setKils(name, death);
    }

  }

  public void addKils(String name, Integer kills) {
    if (playerExits(name)) {
      setKils(name, Integer.valueOf(getKills(name)).intValue() + kills.intValue());
    } else {
      createPlayer(name);
      addKils(name, kills);
    }

  }

  public void addDeaths(String name, Integer deaths) {
    if (playerExits(name)) {
      setDeaths(name, Integer.valueOf(getDeaths(name)).intValue() + deaths.intValue());
    } else {
      createPlayer(name);
      addKils(name, deaths);
    }

  }

  public int getKills(String name) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    if (playerExits(name)) {
      try {
        ResultSet rs = infinitySG.mySQL.getResult("SELECT * FROM Stats WHERE UUID = '" + uuid + "'");
        if (rs.next()) {
          return rs.getInt("Kills");
        }
      } catch (SQLException e) {
        System.err.println("Ein feheler ist aufegtreten:" + e);
      }

    }
    return 0;


  }

  public int getDeaths(String name) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    if (playerExits(name)) {
      try {
        ResultSet rs = infinitySG.mySQL.getResult("SELECT * FROM Stats WHERE UUID = '" + uuid + "'");
        if (rs.next()) {
          return rs.getInt("Deaths");
        }

      } catch (SQLException e) {
        System.err.println("Ein feheler ist aufegtreten:" + e);
      }

    }

    return 0;
  }

  public int getRang(String name) {
    String uuid = UUIDFetcher.getUUID(name).toString();
    Integer count = -1;
    if (playerExits(name)) {
      try {
        ResultSet rs = infinitySG.mySQL.getResult("SELECT * FROM Stats ORDER BY Kills DESC");
        while (rs.next()) {
          String name1 = rs.getString("UUID");
          if (name1.equalsIgnoreCase(uuid)) {
            count = rs.getRow();
            break;
          }
        }
      } catch (SQLException e) {
        System.err.println("Ein fehler ist aufgetreten: " + e);
      }
    }

    return count;
  }

}
