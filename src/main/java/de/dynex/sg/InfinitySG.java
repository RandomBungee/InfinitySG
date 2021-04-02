package de.dynex.sg;

import de.dynex.sg.command.*;
import de.dynex.sg.listener.*;
import de.dynex.sg.mysql.*;
import de.dynex.sg.config.*;
import de.dynex.sg.util.*;
import java.util.ArrayList;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinitySG extends JavaPlugin {
  public InfinitySG() {}

  public InfinitySG infinitySG;
  public String prefix = "§8| §3§lInfinity§cSG §8» §r";
  public Locations locations;
  public ItemBuilder itemBuilder;
  public ConfigManager configManager;
  public MySQL mySQL;
  public ArrayList<Player> build;
  public ChestFill chestFill;

  @Override
  public void onEnable() {
    initFunctions();
    initCommands();
    initListeners();
    System.out.println("Plugin enabled");
  }

  @Override
  public void onDisable() {
    mySQL.close();
    System.out.println("Plugin Disabled");
  }

  private void initFunctions() {
    infinitySG = this;
    configManager = new ConfigManager();
    configManager.createFile();
    locations = new Locations();
    itemBuilder = new ItemBuilder();
    mySQL = new MySQL(configManager.yamlConfiguration.getString("MySQL.user"),
        configManager.yamlConfiguration.getString("MySQL.password"),
        configManager.yamlConfiguration.getString("MySQL.data"),
        configManager.yamlConfiguration.getString("MySQL.host"),
        "3306");
    build = new ArrayList<>();
    chestFill = new ChestFill(this);
    chestFill.onRefillTime();

    mySQL.connect();
    mySQL.createTable();
    ChestFill.addItems();

    for (World worlds : Bukkit.getWorlds()) {
      Bukkit.getWorld(worlds.getName()).setStorm(false);
      Bukkit.getWorld(worlds.getName()).setThunderDuration(18000);
      Bukkit.getWorld(worlds.getName()).setGameRuleValue("doFireTick", "false");
      Bukkit.getWorld(worlds.getName()).setDifficulty(Difficulty.EASY);
    }
  }

  private void initListeners() {
    getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    getServer().getPluginManager().registerEvents(new CanceledInteractsListener(this), this);
    getServer().getPluginManager().registerEvents(new MoveListener(), this);
    getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    getServer().getPluginManager().registerEvents(new ChestFill(this), this);
  }

  private void initCommands() {
    getCommand("build").setExecutor(new BuildCommand(this));
    getCommand("setup").setExecutor(new SetupCommand(this));
    getCommand("stats").setExecutor(new StatsCommand(this));
  }
}
