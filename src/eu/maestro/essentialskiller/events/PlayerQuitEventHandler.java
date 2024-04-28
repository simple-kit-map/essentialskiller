package eu.maestro.essentialskiller.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerQuitEventHandler implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.RED + "- " + ChatColor.GRAY + player.getName());
	}
}
