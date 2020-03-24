package de.dynex.sg.commands;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:37
*/

import de.dynex.sg.InfinitySG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {

  private InfinitySG infinitySG;

  public BuildCMD(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player)sender;
      if(command.getName().equalsIgnoreCase("build")) {
        if(player.hasPermission("infinitysg.build")) {
          if(infinitySG.build.contains(player)) {
            infinitySG.build.remove(player);
            player.sendMessage(infinitySG.prefix + "§7Du hast den Build-Modus §cverlassen§7!");
          } else {
            infinitySG.build.add(player);
            player.sendMessage(infinitySG.prefix + "§7Du hast den Build-Modus §abetreten§7!");
          }
        } else {
          player.sendMessage(infinitySG.prefix + "§7Dafür hast du §ckeine §7Rechte!");
        }
      }
    }
    return false;
  }
}
