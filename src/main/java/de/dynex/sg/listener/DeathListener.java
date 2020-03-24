package de.dynex.sg.listener;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:49
*/

import de.dynex.sg.InfinitySG;
import de.dynexapi.mysql.api.CoinsAPI;
import org.bukkit.Bukkit;
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

  private InfinitySG infinitySG;

  public DeathListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();
    event.setDeathMessage(null);
    for (ItemStack itemStack :
        event.getDrops()) {
      Bukkit.getWorld(player.getWorld().getName()).dropItem(player.getLocation(), itemStack);
    }
    event.getDrops().clear();
    if (player.getKiller() != null) {
      Player killer = player.getKiller();
      player.setVelocity(new Vector().multiply(0));
      killer
          .sendMessage(infinitySG.prefix + "§7Du hast " + player.getDisplayName() + " §7getötet!");
      player.sendMessage(infinitySG.prefix + killer.getDisplayName() + " §7hat dich getötet!");
      infinitySG.stats.addKils(killer.getName(), 1);
      infinitySG.stats.addDeaths(player.getName(), 1);
      CoinsAPI.updateCoins(player.getName(), CoinsAPI.getCoins(player.getName()) + 5);
      killer.sendMessage(infinitySG.prefix + "§7Du hast für den Kill §a+20 §7Coins bekommen");
      killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 3, 1));
    } else {
      player.setVelocity(new Vector().multiply(0));
      player.sendMessage(infinitySG.prefix + "§7Du bist gestorben!");
      infinitySG.stats.addDeaths(player.getName(), 1);
      infinitySG.itemBuilder.setStartItems(player);
    }
    new BukkitRunnable() {
      @Override
      public void run() {
        player.spigot().respawn();
        player.teleport(infinitySG.locations.getLocation("spawn"));
      }
    }.runTaskLaterAsynchronously(infinitySG.infinitySG, 3);
  }

  @EventHandler
  public void onRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    player.getInventory().clear();
    infinitySG.itemBuilder.setStartItems(player);
  }
}
