package de.dynex.sg.commands;

import de.dynex.sg.InfinitySG;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
CheatCMD

07.05.2020

Class by RandomBungee
*/
public class CheatCMD implements CommandExecutor {

    private InfinitySG infinitySG;

    public CheatCMD(InfinitySG infinitySG) {
        this.infinitySG = infinitySG;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(command.getName().equalsIgnoreCase("cheat")) {
                if (player.hasPermission("isg.cheat")) {
                    if(infinitySG.cheat == 1) {
                        infinitySG.cheat = 1;
                        player.sendMessage(infinitySG.prefix + "§7Es werden keine Stats mehr in MySQL gespeichert!");
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.sendMessage(infinitySG.prefix + "§7Es werden keine Stats in der Datenbank gespeichert!");
                        }
                    } else {
                        infinitySG.cheat = 0;
                        player.sendMessage(infinitySG.prefix + "§7Es werden wieder Stats in MySQL gespeichert!");
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.sendMessage(infinitySG.prefix + "§7Es werden wieder Stats in der Datenbank gespeichert!");
                        }
                    }
                } else player.sendMessage(infinitySG.prefix + "§cDafür hast du keine Rechte!");
            }
        }
        return false;
    }
}
