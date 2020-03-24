package de.dynex.sg.utils;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:15
*/

import de.dynex.sg.InfinitySG;
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

  private HashMap<Location, Inventory> chests = new HashMap<>();
  public static ArrayList<ItemStack> items = new ArrayList<>();
  public int i = 160;
  private InfinitySG infinitySG;

  public ChestFill(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
      if (e.getClickedBlock().getType().equals(Material.BEACON)) {
        Location location = e.getClickedBlock().getLocation();
        if (chests.containsKey(location)) {
          e.setCancelled(true);
          p.openInventory(chests.get(location));
          return;
        } else {
          e.setCancelled(true);
          Random random = new Random();
          int l = random.nextInt(9);
          Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, "§3Infinity§cSG");
          while (l != 0) {
            l--;
            Random random1 = new Random();
            Random random2 = new Random();
            int n1 = random1.nextInt(27);
            int n2 = random2.nextInt(items.size() - 1);
            inventory.setItem(n1, items.get(n2));
          }
          chests.put(location, inventory);
          p.openInventory(chests.get(location));
          return;
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
    items.add(new ItemStack(Material.DIAMOND_BOOTS));
    items.add(new ItemStack(Material.STONE_SWORD));
    items.add(new ItemStack(Material.GOLD_SWORD));
    items.add(new ItemStack(Material.IRON_SWORD));
    items.add(new ItemStack(Material.DIAMOND_AXE));
    items.add(new ItemStack(Material.WOOD_AXE));
    items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
    items.add(new ItemStack(Material.IRON_CHESTPLATE));
    items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
    items.add(new ItemStack(Material.CHAINMAIL_HELMET));
    items.add(new ItemStack(Material.GOLD_HELMET));
    items.add(new ItemStack(Material.IRON_HELMET));
    items.add(new ItemStack(Material.LEATHER_HELMET));
    items.add(new ItemStack(Material.GOLD_LEGGINGS));
    items.add(new ItemStack(Material.IRON_LEGGINGS));
    items.add(new ItemStack(Material.GOLD_LEGGINGS));
    items.add(new ItemStack(Material.LEATHER_LEGGINGS));
    items.add(new ItemStack(Material.CHAINMAIL_BOOTS));
    items.add(new ItemStack(Material.IRON_BOOTS));
    items.add(new ItemStack(Material.GOLD_BOOTS));
    items.add(new ItemStack(Material.LEATHER_BOOTS));
    items.add(new ItemStack(Material.DIAMOND_BOOTS));
    items.add(new ItemStack(Material.BREAD, 4));
    items.add(new ItemStack(Material.FISHING_ROD));
    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
    items.add(new ItemStack(Material.FLINT_AND_STEEL));
    items.add(new ItemStack(Material.COOKED_BEEF, 6));
    items.add(new ItemStack(Material.COOKED_BEEF, 6));
    items.add(new ItemStack(Material.BAKED_POTATO, 10));
    items.add(new ItemStack(Material.BAKED_POTATO, 10));
    items.add(new ItemStack(Material.BOW));
    items.add(new ItemStack(Material.BOW));
    items.add(new ItemStack(Material.BOW));
    items.add(new ItemStack(Material.ARROW, 2));
    items.add(new ItemStack(Material.ARROW, 2));
    items.add(new ItemStack(Material.ARROW, 2));
    items.add(new ItemStack(Material.GOLDEN_APPLE));
    items.add(new ItemStack(Material.GOLDEN_APPLE));
    items.add(new ItemStack(Material.SNOW_BALL, 10));
  }

}
