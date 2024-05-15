package eu.maestro.essentialskiller.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.maestro.essentialskiller.SavedLocation;
import net.md_5.bungee.api.ChatColor;

public class WarpCommandHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "usage: /warp <warp>");
			
			sender.sendMessage(
				ChatColor.GRAY + "There are " +
				SavedLocation.locations.size() +
				" warps available:"
			);

			for (SavedLocation loc: SavedLocation.locations) {
				sender.sendMessage("- " + loc.getName());
			}
			return true;
		}

		sender.sendMessage("warping to `" + args[0] + "`");
		for (SavedLocation loc: SavedLocation.locations) {

			if (args[0].equalsIgnoreCase(loc.getName())) {
				final Player player = (Player)sender;
				Location loc_to_tp_to = loc.getLocation();
				sender.sendMessage(loc_to_tp_to.toString());
				player.teleport(loc_to_tp_to);
				return true;
			}
		}
		
		sender.sendMessage("Failed to find warp `" + args[0] + "`");

		return true;
	}

}
