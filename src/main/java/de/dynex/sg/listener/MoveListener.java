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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveListener implements Listener {

  InfinitySG infinitySG;

  public MoveListener(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    if(player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
      Vector vector = player.getLocation().getDirection().multiply(0.9D).setY(0.8D).normalize();
      player.setVelocity(vector);
    }
    if (player.getLocation().getBlock().getType() == Material.GOLD_PLATE) {
      Vector vector = player.getLocation().getDirection().multiply(2D).setY(1D);
      player.setVelocity(vector);
    }
  }
}
