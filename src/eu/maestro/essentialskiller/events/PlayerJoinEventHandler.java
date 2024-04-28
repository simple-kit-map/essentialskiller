package eu.maestro.essentialskiller.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoinEventHandler implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		event.setJoinMessage(ChatColor.GREEN + "- " + ChatColor.GRAY + player.getName());
	}
	
}
