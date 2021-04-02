package com.github.infinitysg.listener;

import com.github.infinitysg.InfinitySG;
import com.github.infinitysg.mysql.MySQL;
import com.github.infinitysg.mysql.api.StatsPlayer;
import com.github.infinitysg.mysql.api.StatsPlayerRepository;
import de.dynex.sg.*;
import de.dynex.sg.mysql.*;
import de.dynex.sg.mysql.api.*;
import com.github.infinitysg.util.UUIDFetcher;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class DeathListener implements Listener {
  private final InfinitySG infinitySG;
  private final StatsPlayer statsPlayer;

  public DeathListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
    this.statsPlayer = StatsPlayerRepository.create(MySQL.connection);
  }

  @EventHandler
  public void playerDeath(PlayerDeathEvent playerDeathEvent) {
    Player player = playerDeathEvent.getEntity();
    String playerUniqueId = UUIDFetcher.getUUID(player.getName()).toString();
    int playerKills = statsPlayer.kills(playerUniqueId);
    int playerDeaths = statsPlayer.deaths(playerUniqueId);
    playerDeathEvent.setDeathMessage(null);
    for (ItemStack itemStack :
      playerDeathEvent.getDrops()) {
      Bukkit.getWorld(player.getWorld().getName()).dropItem(player.getLocation(), itemStack);
    }
    player.teleport(infinitySG.locations.getLocation("spawn"));
    if (player.getKiller() != null) {
      Player killer = player.getKiller();
      String killerUniqueId = UUIDFetcher.getUUID(player.getName()).toString();
      int killerKills = statsPlayer.kills(playerUniqueId);
      int killerDeaths = statsPlayer.deaths(playerUniqueId);
      player.setVelocity(new Vector().multiply(0));
      killer
          .sendMessage(infinitySG.prefix + "§7Du hast " + player.getDisplayName() + " §7getötet!");
      player.sendMessage(infinitySG.prefix + killer.getDisplayName() + " §7hat dich getötet!");
      statsPlayer.change(killerUniqueId, killerKills + 1, killerDeaths);
      statsPlayer.change(playerUniqueId, playerKills, playerDeaths + 1);
      killer.sendMessage(infinitySG.prefix + "§7Du hast für den Kill §a+5 §7Coins bekommen");
      killer.addPotionEffect(new PotionEffect(
        PotionEffectType.REGENERATION, 20 * 3, 1));
    } else {
      player.setVelocity(new Vector().multiply(0));
      player.sendMessage(infinitySG.prefix + "§7Du bist gestorben!");
      statsPlayer.change(playerUniqueId, playerKills, playerDeaths + 1);
      infinitySG.itemBuilder.setStartItems(player);
    }
    player.teleport(infinitySG.locations.getLocation("spawn"));
    new BukkitRunnable() {
      @Override
      public void run() {
        player.spigot().respawn();
        player.teleport(infinitySG.locations.getLocation("spawn"));
      }
    }.runTaskLaterAsynchronously(infinitySG.infinitySG, 2);
  }

  @EventHandler
  public void playerInitialAfterRespawn(PlayerRespawnEvent playerRespawnEvent) {
    Player player = playerRespawnEvent.getPlayer();
    player.getInventory().clear();
    infinitySG.itemBuilder.setStartItems(player);
  }
}
