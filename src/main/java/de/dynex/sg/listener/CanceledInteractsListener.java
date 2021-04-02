package de.dynex.sg.listener;

import de.dynex.sg.InfinitySG;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CanceledInteractsListener implements Listener {
  private final InfinitySG infinitySG;

  public CanceledInteractsListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void playerTryToPlace(BlockPlaceEvent blockPlaceEvent) {
    blockPlaceEvent.setCancelled(
      blockPlaceEvent.getBlock().getType() != Material.FIRE && !infinitySG.build.contains(blockPlaceEvent.getPlayer())
    );
  }

  @EventHandler
  public void playerTryToBreak(BlockBreakEvent blockBreakEvent) {
    blockBreakEvent.setCancelled(true);
    blockBreakEvent.setCancelled(!infinitySG.build.contains(blockBreakEvent.getPlayer()));
  }

  @EventHandler
  public void weatherChangeCancel(WeatherChangeEvent weatherChangeEvent) {
    weatherChangeEvent.setCancelled(true);
  }

  @EventHandler
  public void mobSpawnCancel(CreatureSpawnEvent creatureSpawnEvent) {
    creatureSpawnEvent.setCancelled(true);
  }

  @EventHandler
  public void blockFallDamage(EntityDamageEvent entityDamageEvent) {
    if (entityDamageEvent.getEntity() instanceof Player) {
      Player player = (Player) entityDamageEvent.getEntity();
      if (player.getLocation().getY() >= 170 || entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FALL) {
        entityDamageEvent.setCancelled(true);
      }
    }
  }

  @EventHandler
  public void spawnProtectionInHigh(EntityDamageByBlockEvent entityDamageByBlockEvent) {
    if (entityDamageByBlockEvent.getEntity() instanceof Player) {
      Player player = (Player) entityDamageByBlockEvent.getEntity();
      entityDamageByBlockEvent.setCancelled(player.getLocation().getY() >= 170);
    }
  }
}
