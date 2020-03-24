package de.dynex.sg.utils;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:15
*/

import de.dynex.sg.InfinitySG;
import de.dynexapi.mysql.api.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Score {

  private InfinitySG infinitySG;

  public Score(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @SuppressWarnings("deprecation")
  public void setScoreboard(Player player) {
    Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective objective = sb.getObjective("aaa");
    if (objective == null) {
      objective = sb.registerNewObjective("aaa", "bbb");
    }
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

    objective.setDisplayName("§6DynexMC §8| §3Infinity§cSG");

    objective.getScore("§a").setScore(15);
    objective.getScore("§f§6").setScore(14);
    objective.getScore("§c● §8§l▎ §7Rang").setScore(13);
    objective.getScore("§8» " + "SOON").setScore(12);
    objective.getScore("§d").setScore(11);
    objective.getScore("§6● §8§l▎ §7Coins").setScore(10);
    objective.getScore(updateTeam(sb, "Coins", "§8» §e" + CoinsAPI.getCoins(player.getName()), "§e",
        ChatColor.GREEN)).setScore(9);
    objective.getScore("§c").setScore(8);
    objective.getScore("§e● §8§l▎ §7Online").setScore(7);
    objective.getScore(updateTeam(sb, "User", "§8» §e" + Bukkit.getOnlinePlayers().size() + "", "",
        ChatColor.GOLD)).setScore(6);
    objective.getScore("§4").setScore(5);
    objective.getScore("§a● §8§l▎ §7K/D").setScore(4);
    objective.getScore(updateTeam(sb, "KD",
        "§8» §e" + infinitySG.stats.getKills(player.getName()) + "§7/" + infinitySG.stats
            .getDeaths(player.getName()), "", ChatColor.BLACK)).setScore(3);
    objective.getScore("§a§l").setScore(2);
    objective.getScore("§8§m--------------- ").setScore(1);
    player.setScoreboard(sb);
  }

  @SuppressWarnings("deprecation")
  public void updateScoreboard(Player player) {
    if (player.getScoreboard() == null) {
      setScoreboard(player);
    }
    Scoreboard sb = player.getScoreboard();
    Objective objective = sb.getObjective("aaa");

    objective.getScore(updateTeam(sb, "Coins", "§8» §e" + CoinsAPI.getCoins(player.getName()), "§e",
        ChatColor.GREEN)).setScore(9);
    objective.getScore(updateTeam(sb, "User", "§8» §e" + Bukkit.getOnlinePlayers().size() + "", "",
        ChatColor.GOLD)).setScore(6);
    objective.getScore(updateTeam(sb, "KD",
        "§8» §e" + infinitySG.stats.getKills(player.getName()) + "§7/" + infinitySG.stats
            .getDeaths(player.getName())
            + "", "",
        ChatColor.BLACK)).setScore(3);
  }

  public Team getTeam(Scoreboard sb, String Team, String prefix, String suffix) {
    Team team = sb.getTeam(Team);
    if (team == null) {
      team = sb.registerNewTeam(Team);
    }
    team.setPrefix(prefix);
    team.setSuffix(suffix);
    return team;
  }

  public String updateTeam(Scoreboard sb, String Team, String prefix, String suffix,
      ChatColor entry) {
    Team team = sb.getTeam(Team);
    if (team == null) {
      team = sb.registerNewTeam(Team);
    }
    team.setPrefix(prefix);
    team.setSuffix(suffix);
    team.addEntry(entry.toString());
    return entry.toString();
  }

  public void startScheduler() {
    new BukkitRunnable() {

      @Override
      public void run() {
        for (Player all : Bukkit.getOnlinePlayers()) {
          updateScoreboard(all);
        }
      }
    }.runTaskTimer(infinitySG.infinitySG, 20, 20 * 5);
  }

}
