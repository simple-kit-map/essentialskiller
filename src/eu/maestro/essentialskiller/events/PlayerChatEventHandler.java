package eu.maestro.essentialskiller.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEventHandler implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();
		event.setFormat(player.getName() + ": " + event.getMessage());
	}
}
