package de.dynex.sg.commands;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:30
*/

import de.dynex.sg.InfinitySG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCMD implements CommandExecutor {

  private InfinitySG infinitySG;

  public StatsCMD(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player)sender;
      if(command.getName().equalsIgnoreCase("stats")) {
        if(args.length == 0) {
          int kills = infinitySG.stats.getKills(player.getName());
          int deaths = infinitySG.stats.getDeaths(player.getName());
          int rang = infinitySG.stats.getRang(player.getName());
          double kd = Double.valueOf(kills) / Double.valueOf(deaths);
          Math.round(kd);
          player.sendMessage("§7----------<§3Infinity§cSG-Stats§7>§7----------");
          player.sendMessage(infinitySG.prefix + "§7Platz §e" + rang);
          player.sendMessage(infinitySG.prefix + "§7Kills §e" + kills);
          player.sendMessage(infinitySG.prefix + "§7Deaths §e" + deaths);
          player.sendMessage(infinitySG.prefix + "§7K/D §e" + kd);
          player.sendMessage("§7----------<§3Infinity§cSG-Stats§7>§7----------");
        } else if(args.length == 1) {
          String target = args[0];
          if(infinitySG.stats.playerExits(target)) {
            int kills = infinitySG.stats.getKills(target);
            int deaths = infinitySG.stats.getDeaths(target);
            int rang = infinitySG.stats.getRang(target);
            double kd = Double.valueOf(kills) / Double.valueOf(deaths);
            Math.round(kd);
            player.sendMessage("§7----------<§3Infinity§cSG-Stats§7>§7----------");
            player.sendMessage(infinitySG.prefix + "§7Stats von §e" + target);
            player.sendMessage(infinitySG.prefix + "§7Platz §e" + rang);
            player.sendMessage(infinitySG.prefix + "§7Kills §e" + kills);
            player.sendMessage(infinitySG.prefix + "§7Deaths §e" + deaths);
            player.sendMessage(infinitySG.prefix + "§7K/D §e" + kd);
            player.sendMessage("§7----------<§3Infinity§cSG-Stats§7>§7----------");
          } else {
            player.sendMessage(infinitySG.prefix + "§7Der Spieler hat §cnie §3Infinity§cSG §7gespielt!");
          }
        }
      }
    }
    return false;
  }
}
