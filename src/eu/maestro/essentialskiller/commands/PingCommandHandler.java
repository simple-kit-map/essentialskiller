package eu.maestro.essentialskiller.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PingCommandHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length == 0) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only a player can use this command");
				return true;
			}
			
			final Player player = (Player)sender;
			player.sendMessage("Your ping is: " + player.getPing());
			return true;
		}
		else if(args.length == 1) {
			
			final Player player = (Player)sender.getServer().getPlayer(args[0]);
			
			if(player == null) {
				sender.sendMessage(ChatColor.RED + args[0] + " not found");
				return true;
			}
			
			sender.sendMessage(args[0] + "'s ping is: " + player.getPing());
			return true;
		}
		
		return false;
	}

}
