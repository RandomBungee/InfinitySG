package com.github.infinitysg.command;

import com.github.infinitysg.InfinitySG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {
  private final InfinitySG infinitySG;

  public BuildCommand(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @Override
  public boolean onCommand(
    CommandSender commandSender,
    Command command,
    String label,
    String[] args
  ) {
    if(!checkCommandComponents(commandSender, command)) {
      Player player = (Player)commandSender;
      if(infinitySG.build.contains(player)) {
        infinitySG.build.remove(player);
        player.sendMessage(infinitySG.prefix + "§7Du hast den Build-Modus §cverlassen§7!");
      } else {
        infinitySG.build.add(player);
        player.sendMessage(infinitySG.prefix + "§7Du hast den Build-Modus §abetreten§7!");
      }
    }
    return false;
  }

  private boolean checkCommandComponents(
    CommandSender commandSender,
    Command command
  ) {
    if(!(commandSender instanceof Player)) {
      commandSender.sendMessage(infinitySG.prefix + "§cDu musste ein Spieler sein!");
      return true;
    }
    if(!command.getName().equalsIgnoreCase("build")) {
      return true;
    }
    if(!commandSender.hasPermission("infinitysg.command.build")) {
      commandSender.sendMessage(infinitySG.prefix + "§7Dafür hast du §ckeine §7Rechte!");
      return true;
    }
    return false;
  }
}
