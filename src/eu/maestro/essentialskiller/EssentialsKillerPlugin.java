package eu.maestro.essentialskiller;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.maestro.essentialskiller.commands.PingCommandHandler;
import eu.maestro.essentialskiller.events.PlayerChatEventHandler;
import eu.maestro.essentialskiller.events.PlayerCommandPreprocessEventHandler;
import eu.maestro.essentialskiller.events.PlayerJoinEventHandler;
import eu.maestro.essentialskiller.events.PlayerQuitEventHandler;

public class EssentialsKillerPlugin extends JavaPlugin {
	
	private static EssentialsKillerPlugin instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.registerCommands();
		this.registerEvents();
		
		System.out.println("essentialskiller enabled !");
	}
	
	private void registerCommands() {
		this.getCommand("ping").setExecutor(new PingCommandHandler());
	}
	
	private void registerEvents() {
		final PluginManager pluginManager = this.getServer().getPluginManager();
		
		pluginManager.registerEvents(new PlayerJoinEventHandler(), this);
		pluginManager.registerEvents(new PlayerQuitEventHandler(), this);
		pluginManager.registerEvents(new PlayerChatEventHandler(), this);
		pluginManager.registerEvents(new PlayerCommandPreprocessEventHandler(), this);
	}
	
	
	@Override
	public void onDisable() {
		System.out.println("essentialskiller disabled !");
	}
	
	
	public static EssentialsKillerPlugin getInstance() {
		return instance;
	}
	
}
