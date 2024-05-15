package eu.maestro.essentialskiller.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.minecraft.server.v1_7_R4.MinecraftServer;

public class PlayerCommandPreprocessEventHandler implements Listener {
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		
		final Player player = event.getPlayer();
		final String command = event.getMessage().replace("/", "");
		/*
		if(command.equals("tps")) {
			double[] recentTps = MinecraftServer.getServer().recentTps;
			double totalTps = 0.0;

			for(double tps : recentTps) {
				totalTps += tps;
			}
			
			final double averageTps = totalTps / recentTps.length;
			
			player.sendMessage("tps: " + averageTps);
			event.setCancelled(true);
			return;
		}
		*/
	}
	
}
