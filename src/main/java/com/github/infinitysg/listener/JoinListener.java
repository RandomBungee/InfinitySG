package com.github.infinitysg.listener;

import com.github.infinitysg.InfinitySG;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {
  private final InfinitySG infinitySG;

  public JoinListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void initialPlayerJoin(PlayerJoinEvent playerJoinEvent) {
    Player player = playerJoinEvent.getPlayer();
    playerJoinEvent.setJoinMessage(null);
    player.teleport(infinitySG.locations.getLocation("spawn"));
    player.sendMessage("§7Wilkommen in §3Infinity§cSG §7viel Spaß beim Spielen!");
    player.setHealth(20);
    player.setFoodLevel(20);
    player.getInventory().clear();
    player.getInventory().setArmorContents(null);
    player.setFireTicks(0);
    infinitySG.itemBuilder.setStartItems(player);
    new BukkitRunnable() {
      @Override
      public void run() {
        for(Player all : Bukkit.getOnlinePlayers()) {
          all.sendMessage(infinitySG.prefix + player.getDisplayName() +  " §7hat §3Infinity§cSG §7betreten!");
        }
      }
    }.runTaskLaterAsynchronously(infinitySG.infinitySG, 1);
  }

  @EventHandler
  public void deinitialionPlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    event.setQuitMessage(infinitySG.prefix + player.getDisplayName() + " §7hat §3Infinity§cSG §7verlassen!");
  }
}
