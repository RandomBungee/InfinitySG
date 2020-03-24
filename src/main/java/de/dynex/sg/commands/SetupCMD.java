package de.dynex.sg.commands;
/*
Class was created by RandomBungee
On 23.03.2020
At 22:16
*/

import de.dynex.sg.InfinitySG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCMD implements CommandExecutor {

  private InfinitySG infinitySG;

  public SetupCMD(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player)sender;
      if(command.getName().equals("setup")) {
        if(player.hasPermission("infinitysg.setup")) {
          infinitySG.locations.setLocation("spawn", player.getLocation());
          player.sendMessage(infinitySG.prefix + "§7Du hast den §eSpawn §agesetzt§7!");
        } else {
          player.sendMessage(infinitySG.prefix + "§7Dafür hast du §ckeine §7Rechte!");
        }
      }
    }
    return false;
  }
}
