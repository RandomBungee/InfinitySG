package de.dynex.sg.utils;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:15
*/

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

  public ItemStack setItem(String Display, Material Material, int Anzahl, int Shorts) {
    ItemStack istack52 = new ItemStack(Material, Anzahl, (short) Shorts);
    ItemMeta istackMeta52 = istack52.getItemMeta();
    istackMeta52.setDisplayName(Display);
    istack52.setItemMeta(istackMeta52);
    return istack52;
  }

  private ItemStack setArmor(String Display, Material Material) {
    ItemStack istack52 = new ItemStack(Material);
    ItemMeta istackMeta52 = istack52.getItemMeta();
    istackMeta52.setDisplayName(Display);
    istack52.setItemMeta(istackMeta52);
    return istack52;
  }

  public ItemStack setItemwithLore(String Display, Material Material, String lores,
      int Anzahl, int Shorts) {
    ItemStack istack52 = new ItemStack(Material, Anzahl, (short) Shorts);
    ItemMeta istackMeta52 = istack52.getItemMeta();
    istackMeta52.setDisplayName(Display);
    List<String> lore = new ArrayList();
    lore.add(lores);
    istackMeta52.setLore(lore);
    istack52.setItemMeta(istackMeta52);
    return istack52;
  }

  public ItemStack colorArmor(String Display, Material m, Color color) {
    ItemStack i = new ItemStack(m);
    LeatherArmorMeta im = (LeatherArmorMeta) i.getItemMeta();
    im.setColor(color);
    im.setDisplayName(Display);
    i.setItemMeta(im);
    return i;
  }

  public ItemStack Skull(String Display, int Anzahl, int Shorts, String Owner) {
    ItemStack stack = new ItemStack(Material.SKULL_ITEM, Anzahl, (short) 3);
    SkullMeta meta = (SkullMeta) stack.getItemMeta();
    meta.setOwner(Owner);
    meta.setDisplayName(Display);
    stack.setItemMeta(meta);
    return stack;
  }

  public void setStartItems(Player player) {
    player.getInventory().setItem(0, setItem("§7Starter-Kit", Material.WOOD_SWORD, 1, 0));
    player.getInventory().setHelmet(setArmor("§7Starter-Kit", Material.LEATHER_HELMET));
    player.getInventory().setChestplate(setArmor("§7Starter-Kit", Material.LEATHER_CHESTPLATE));
    player.getInventory().setLeggings(setArmor("§7Starter-Kit", Material.LEATHER_LEGGINGS));
    player.getInventory().setBoots(setArmor("§7Starter-Kit", Material.LEATHER_BOOTS));
  }

}
