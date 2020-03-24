package de.dynex.sg.listener;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:21
*/

import de.dynex.sg.InfinitySG;
import javafx.scene.chart.BubbleChart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {

  private InfinitySG infinitySG;

  public JoinListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    if(!infinitySG.stats.playerExits(player.getName())) {
      infinitySG.stats.createPlayer(player.getName());
    }
    event.setJoinMessage(null);
    player.teleport(infinitySG.locations.getLocation("spawn"));
    player.sendMessage("§7Wilkommen in §3Infinity§cSG §7viel Spaß beim Spielen!");
    player.setHealth(20);
    player.setFoodLevel(20);
    player.getInventory().clear();
    player.getInventory().setArmorContents(null);
    player.setFireTicks(0);
    infinitySG.itemBuilder.setStartItems(player);
    for(Player all : Bukkit.getOnlinePlayers()) {
      infinitySG.score.setScoreboard(all);
    }
    infinitySG.score.startScheduler();
    new BukkitRunnable() {
      @Override
      public void run() {
        for(Player all : Bukkit.getOnlinePlayers()) {
          all.sendMessage(infinitySG.prefix + player.getDisplayName() + " §7hat §3Infinity§cSG §7betreten!");
        }
      }
    }.runTaskLaterAsynchronously(infinitySG.infinitySG, 5);
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    event.setQuitMessage(infinitySG.prefix + player.getDisplayName() + " §7hat §3Infinity§cSG §7verlassen!");
  }
}
