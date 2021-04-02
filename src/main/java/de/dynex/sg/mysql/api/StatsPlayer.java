package de.dynex.sg.mysql.api;

public interface StatsPlayer {
  void create(String uniqueId, int kills, int deaths);

  void change(String uniqueId, int kills, int deaths);

  int kills(String uniqueId);

  int deaths(String uniqueId);

  int rank(String uniqueId);
}
