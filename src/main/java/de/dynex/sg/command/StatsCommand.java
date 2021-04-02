package de.dynex.sg.command;

import de.dynex.sg.InfinitySG;
import de.dynex.sg.mysql.MySQL;
import de.dynex.sg.mysql.api.StatsPlayer;
import de.dynex.sg.mysql.api.StatsPlayerRepository;
import de.dynex.sg.util.UUIDFetcher;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
  private final InfinitySG infinitySG;
  private final StatsPlayer statsPlayer;

  public StatsCommand(InfinitySG infinitySG) {
    this.infinitySG = infinitySG;
    this.statsPlayer = StatsPlayerRepository.create(MySQL.connection);
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
      if(args.length == 0) {
        sendStatsMap(player, player.getName());
      } else if(args.length == 1) {
        String target = args[0];
        String uniqueId = UUIDFetcher.getUUID(target).toString();
        sendStatsMap(player, uniqueId);
      }
    }
    return false;
  }

  private boolean checkCommandComponents(
    CommandSender commandSender,
    Command command
  ) {
    if(!command.getName().equalsIgnoreCase("stats")) {
      return true;
    }
    if(!(commandSender instanceof Player)) {
      commandSender.sendMessage(infinitySG.prefix + "§cDu musste ein Spieler sein!");
      return true;
    }
    return false;
  }

  private void sendStatsMap(Player player, String uniqueId) {
    int kills = statsPlayer.kills(uniqueId);
    int deaths = statsPlayer.deaths(uniqueId);
    int rang = statsPlayer.rank(uniqueId);
    double kd = Double.valueOf(kills) / Double.valueOf(deaths);
    String playerName = UUIDFetcher.getName(UUID.fromString(uniqueId));
    player.sendMessage("§7----------<§3Infinity§cSG-Stats§7>§7----------");
    player.sendMessage(infinitySG.prefix + "§7Stats von §e" + playerName);
    player.sendMessage(infinitySG.prefix + "§7Platz §e" + rang);
    player.sendMessage(infinitySG.prefix + "§7Kills §e" + kills);
    player.sendMessage(infinitySG.prefix + "§7Deaths §e" + deaths);
    player.sendMessage(infinitySG.prefix + "§7K/D §e" + kd);
    player.sendMessage("§7----------<§3Infinity§cSG-Stats§7>§7----------");
  }
}
