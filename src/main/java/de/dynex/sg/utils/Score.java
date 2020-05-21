package de.dynex.sg.utils;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:15
*/

import de.dynex.sg.InfinitySG;
import de.dynexapi.mysql.api.CoinsAPI;
import de.dynexapi.mysql.api.DyePlayer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Score {

  private InfinitySG infinitySG;

  public Score(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  public void setScoreboard(Player player) {
    DyePlayer dyePlayer = new DyePlayer(player.getName());
    Scoreboard scoreboard = new Scoreboard();
    ScoreboardObjective scoreboardObjective = scoreboard.registerObjective("§InfinitySG", IScoreboardCriteria.b);
    PacketPlayOutScoreboardObjective objectiveCreate = new PacketPlayOutScoreboardObjective(scoreboardObjective, 0);
    PacketPlayOutScoreboardObjective objectiveRemove = new PacketPlayOutScoreboardObjective(scoreboardObjective, 1);
    PacketPlayOutScoreboardDisplayObjective displayObjective = new PacketPlayOutScoreboardDisplayObjective(1, scoreboardObjective);
    scoreboardObjective.setDisplayName("§InfinitySG");

    ScoreboardScore scoreboardScore0 = new ScoreboardScore(scoreboard, scoreboardObjective, "§a");
    ScoreboardScore scoreboardScore1 = new ScoreboardScore(scoreboard, scoreboardObjective, "§c● §8§l▎ §7Rang");
    ScoreboardScore scoreboardScore2 = new ScoreboardScore(scoreboard, scoreboardObjective, "§8» §7" + dyePlayer.getColor() + dyePlayer.getPrefix());
    ScoreboardScore scoreboardScore3 = new ScoreboardScore(scoreboard, scoreboardObjective, "§b");
    ScoreboardScore scoreboardScore4 = new ScoreboardScore(scoreboard, scoreboardObjective, "§6● §8§l▎ §7Coins");
    ScoreboardScore scoreboardScore5 = new ScoreboardScore(scoreboard, scoreboardObjective, "§8» §6" + CoinsAPI.getCoins(player.getName()));
    ScoreboardScore scoreboardScore6 = new ScoreboardScore(scoreboard, scoreboardObjective, "§c");
    ScoreboardScore scoreboardScore7 = new ScoreboardScore(scoreboard, scoreboardObjective, "§e● §8§l▎ §7Online");
    ScoreboardScore scoreboardScore8 = new ScoreboardScore(scoreboard, scoreboardObjective, "§8» §e" + Bukkit.getOnlinePlayers().size()
            + "§7/§e" + Bukkit.getMaxPlayers());
    ScoreboardScore scoreboardScore9 = new ScoreboardScore(scoreboard, scoreboardObjective, "§d");
    ScoreboardScore scoreboardScore10 = new ScoreboardScore(scoreboard, scoreboardObjective, "§a● §8§l▎ §7K/D");
    ScoreboardScore scoreboardScore11 = new ScoreboardScore(scoreboard, scoreboardObjective, "§8» §a" + infinitySG.stats.getKills(player.getName())
            +  "§7/§a" + infinitySG.stats.getDeaths(player.getName()));
    ScoreboardScore scoreboardScore12 = new ScoreboardScore(scoreboard, scoreboardObjective, "§e");
    ScoreboardScore scoreboardScore13 = new ScoreboardScore(scoreboard, scoreboardObjective, "§8§m--------------- ");

    scoreboardScore0.setScore(14);
    scoreboardScore1.setScore(13);
    scoreboardScore2.setScore(12);
    scoreboardScore3.setScore(11);
    scoreboardScore4.setScore(10);
    scoreboardScore5.setScore(9);
    scoreboardScore6.setScore(8);
    scoreboardScore7.setScore(7);
    scoreboardScore8.setScore(6);
    scoreboardScore9.setScore(5);
    scoreboardScore10.setScore(4);
    scoreboardScore11.setScore(3);
    scoreboardScore12.setScore(2);
    scoreboardScore13.setScore(1);

    PacketPlayOutScoreboardScore registerScore0 = new PacketPlayOutScoreboardScore(scoreboardScore0);
    PacketPlayOutScoreboardScore registerScore1 = new PacketPlayOutScoreboardScore(scoreboardScore1);
    PacketPlayOutScoreboardScore registerScore2 = new PacketPlayOutScoreboardScore(scoreboardScore2);
    PacketPlayOutScoreboardScore registerScore3 = new PacketPlayOutScoreboardScore(scoreboardScore3);
    PacketPlayOutScoreboardScore registerScore4 = new PacketPlayOutScoreboardScore(scoreboardScore4);
    PacketPlayOutScoreboardScore registerScore5 = new PacketPlayOutScoreboardScore(scoreboardScore5);
    PacketPlayOutScoreboardScore registerScore6 = new PacketPlayOutScoreboardScore(scoreboardScore6);
    PacketPlayOutScoreboardScore registerScore7 = new PacketPlayOutScoreboardScore(scoreboardScore7);
    PacketPlayOutScoreboardScore registerScore8 = new PacketPlayOutScoreboardScore(scoreboardScore8);
    PacketPlayOutScoreboardScore registerScore9 = new PacketPlayOutScoreboardScore(scoreboardScore9);
    PacketPlayOutScoreboardScore registerScore10 = new PacketPlayOutScoreboardScore(scoreboardScore10);
    PacketPlayOutScoreboardScore registerScore11 = new PacketPlayOutScoreboardScore(scoreboardScore11);
    PacketPlayOutScoreboardScore registerScore12 = new PacketPlayOutScoreboardScore(scoreboardScore12);
    PacketPlayOutScoreboardScore registerScore13 = new PacketPlayOutScoreboardScore(scoreboardScore13);

    sendPacket(player, objectiveRemove);
    sendPacket(player, objectiveCreate);
    sendPacket(player, displayObjective);
    sendPacket(player, registerScore0);
    sendPacket(player, registerScore1);
    sendPacket(player, registerScore2);
    sendPacket(player, registerScore3);
    sendPacket(player, registerScore4);
    sendPacket(player, registerScore5);
    sendPacket(player, registerScore6);
    sendPacket(player, registerScore7);
    sendPacket(player, registerScore8);
    sendPacket(player, registerScore9);
    sendPacket(player, registerScore10);
    sendPacket(player, registerScore11);
    sendPacket(player, registerScore12);
    sendPacket(player, registerScore13);
  }

  private static void sendPacket(Player player, Packet<?> packet) {
    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
  }

}
