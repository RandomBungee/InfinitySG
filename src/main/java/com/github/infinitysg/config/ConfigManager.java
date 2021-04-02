package com.github.infinitysg.config;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

  public File file = new File("plugins/InfinitySG", "mysql.yml");
  public YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

  public void createFile() {
    if(!file.exists()) {
      yamlConfiguration.set("MySQL.host", "localhost");
      yamlConfiguration.set("MySQL.data", "database");
      yamlConfiguration.set("MySQL.user", "root");
      yamlConfiguration.set("MySQL.password", "password");
      try {
        yamlConfiguration.save(file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
