package com.github.infinitysg.util;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Locations {
  public Locations() {}

  public File file = new File("plugins/InfinitySG", "locations.yml");
  public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

  public void setLocation(
    String name,
    Location loc
  ) {
    cfg.set(name + ".world", loc.getWorld().getName());
    cfg.set(name + ".x", loc.getX());
    cfg.set(name + ".y", loc.getY());
    cfg.set(name + ".z", loc.getZ());
    cfg.set(name + ".yaw", loc.getYaw());
    cfg.set(name + ".pitch", loc.getPitch());
    saveCfg();
  }

  public Location getLocation(String name) {
    Location loc;
    try {
      World w = Bukkit.getWorld(cfg.getString(name + ".world"));
      double x = cfg.getDouble(name + ".x");
      double y = cfg.getDouble(name + ".y");
      double z = cfg.getDouble(name + ".z");
      loc = new Location(w, x, y, z);
      loc.setYaw(cfg.getInt(name + ".yaw"));
      loc.setPitch(cfg.getInt(name + ".pitch"));
    } catch (Exception ex) {
      loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
    }
    return loc;
  }

  public void saveCfg() {
    try {
      cfg.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
