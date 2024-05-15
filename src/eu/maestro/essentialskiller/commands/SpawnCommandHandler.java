package eu.maestro.essentialskiller.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommandHandler implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
        Location location = new Location(Bukkit.getWorld("world"), -54.5, 71, -69.5, 0, 0);

		final Player player = (Player)sender;
		player.teleport(location);
		return true;
	}
}
