package eu.maestro.essentialskiller.commands;

import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.maestro.essentialskiller.EssentialsKillerPlugin;

public class BackCommandHandler implements CommandExecutor {
	
	private EssentialsKillerPlugin plugin;
	
	public BackCommandHandler(EssentialsKillerPlugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("back").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		final Player player = (Player)sender;
		UUID uuid = player.getUniqueId();
		
		if (plugin.lastDeathPos.containsKey(uuid)){
			player.teleport(plugin.lastDeathPos.get(uuid));
		} else {
			player.sendMessage("No last death position found, FYI Last death positions don't persist across server restarts");
		}
		return true;
	}
}
