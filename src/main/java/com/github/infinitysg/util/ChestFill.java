package com.github.infinitysg.util;

import com.github.infinitysg.InfinitySG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestFill implements Listener {
  private final HashMap<Location, Inventory> chests = new HashMap<>();
  public static ArrayList<ItemStack> items = new ArrayList<>();
  public int i = 160;
  private final InfinitySG infinitySG;

  public ChestFill(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  public Integer random(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
  }

  @EventHandler
  public void playerOpenChest(PlayerInteractEvent playerInteractEvent) {
    Player player = playerInteractEvent.getPlayer();
    if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
      if (playerInteractEvent.getClickedBlock().getType().equals(Material.BEACON)) {
        Location location = playerInteractEvent.getClickedBlock().getLocation();
        if (chests.containsKey(location)) {
          playerInteractEvent.setCancelled(true);
          player.openInventory(chests.get(location));
        } else {
          playerInteractEvent.setCancelled(true);
          Random random = new Random();
          int l = random.nextInt(15);
          Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, "§3Infinity§cSG");
          while (l != 0) {
            l--;
            Random random2 = new Random();
            int n2 = random2.nextInt(random(4, 10));
            inventory.setItem(random(6, 16), items.get(n2));
          }
          chests.put(location, inventory);
          player.openInventory(chests.get(location));
        }
      }
    }
  }

  public void onRefillTime() {
    Bukkit.getScheduler().scheduleSyncRepeatingTask(infinitySG.infinitySG, new Runnable() {
      @Override
      public void run() {
        if (i != 0) {
          for (Player all : Bukkit.getOnlinePlayers()) {
            Title.sendActionBar(all, "§3» §8§l▎ §2 Alle Beacons werden in §6§l" + i
                + " §2Sekunden aufgefüllt! §8§l▎ §3«");
          }
        } else {
          chests.clear();
          i = 180;
          Bukkit.getScheduler().cancelAllTasks();
          onRefillTime();
        }
        i--;
      }
    }, 20, 20);
  }

  public static void addItems() {
   //TODO: Add Items
  }
}
