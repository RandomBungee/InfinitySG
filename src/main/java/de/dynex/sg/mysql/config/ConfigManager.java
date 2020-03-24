package de.dynex.sg.mysql.config;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:22
*/

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
