package de.dynex.sg.listener;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:55
*/

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

public class EnvormentListener implements Listener {

  private InfinitySG infinitySG;

  public EnvormentListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent e) {
    if (e.getBlock().getType() == Material.FLINT_AND_STEEL || infinitySG.build.contains(e.getPlayer())) {
      e.setCancelled(false);
    } else {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent e) {
    e.setCancelled(true);
    if (infinitySG.build.contains(e.getPlayer())) {
      e.setCancelled(false);
    } else {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onWeather(WeatherChangeEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onMobSpawn(CreatureSpawnEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      Player p = (Player) e.getEntity();
      if (p.getLocation().getY() >= 170 || e.getCause() == EntityDamageEvent.DamageCause.FALL) {
        e.setCancelled(true);
      } else {
      }
    }

  }

  @EventHandler
  public void onDamageEntityByBlock(EntityDamageByBlockEvent e) {
    if (e.getEntity() instanceof Player) {
      Player p = (Player) e.getEntity();
      if (p.getLocation().getY() >= 170) {
        e.setCancelled(true);
      } else {
        e.setCancelled(false);
      }
    }
  }
}
