package de.dynex.sg;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:12
*/

import de.dynex.sg.commands.BuildCMD;
import de.dynex.sg.commands.SetupCMD;
import de.dynex.sg.commands.StatsCMD;
import de.dynex.sg.listener.DeathListener;
import de.dynex.sg.listener.EnvormentListener;
import de.dynex.sg.listener.JoinListener;
import de.dynex.sg.listener.MoveListener;
import de.dynex.sg.mysql.MySQL;
import de.dynex.sg.mysql.api.Stats;
import de.dynex.sg.mysql.config.ConfigManager;
import de.dynex.sg.utils.*;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinitySG extends JavaPlugin {

  public InfinitySG infinitySG;
  public String prefix = "§8| §3§lInfinity§cSG §8» §r";
  public Locations locations;
  public ItemBuilder itemBuilder;
  public ConfigManager configManager;
  public MySQL mySQL;
  public Stats stats;
  public ArrayList<Player> build;
  public ChestFill chestFill;
  public Score score;
  public int cheat = 0;

  @Override
  public void onEnable() {
    initFunctions();
    initCommands();
    initListeners();
    getServer().getConsoleSender().sendMessage(prefix + "§7Plugin wurde gestartet!");
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
    stats = new Stats(this);
    build = new ArrayList<>();
    chestFill = new ChestFill(this);
    score = new Score(this);
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
    getServer().getPluginManager().registerEvents(new EnvormentListener(this), this);
    getServer().getPluginManager().registerEvents(new MoveListener(this), this);
    getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    getServer().getPluginManager().registerEvents(new ChestFill(this), this);
  }

  private void initCommands() {
    getCommand("build").setExecutor(new BuildCMD(this));
    getCommand("setup").setExecutor(new SetupCMD(this));
    getCommand("stats").setExecutor(new StatsCMD(this));
  }
}
