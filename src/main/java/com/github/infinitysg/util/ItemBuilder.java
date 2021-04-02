package com.github.infinitysg.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
  public ItemBuilder() {}


  private ItemStack setItem(
    String displayName,
    Material material
  ) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(displayName);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public void setStartItems(Player player) {
    player.getInventory().setItem(0, setItem("§7Starter-Kit", Material.WOOD_SWORD));
    player.getInventory().setHelmet(setItem("§7Starter-Kit", Material.LEATHER_HELMET));
    player.getInventory().setChestplate(setItem("§7Starter-Kit", Material.LEATHER_CHESTPLATE));
    player.getInventory().setLeggings(setItem("§7Starter-Kit", Material.LEATHER_LEGGINGS));
    player.getInventory().setBoots(setItem("§7Starter-Kit", Material.LEATHER_BOOTS));
  }

}
