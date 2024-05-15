package eu.maestro.essentialskiller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Item;

import eu.maestro.essentialskiller.commands.BackCommandHandler;
import eu.maestro.essentialskiller.commands.BackCommandHandler;
import eu.maestro.essentialskiller.commands.PingCommandHandler;
import eu.maestro.essentialskiller.commands.SpawnCommandHandler;
import eu.maestro.essentialskiller.commands.WarpCommandHandler;
import eu.maestro.essentialskiller.events.PlayerChatEventHandler;
import eu.maestro.essentialskiller.events.PlayerCommandPreprocessEventHandler;
import eu.maestro.essentialskiller.events.PlayerJoinEventHandler;
import eu.maestro.essentialskiller.events.PlayerQuitEventHandler;
import eu.maestro.essentialskiller.handler.manager.FilesManager;

public class EssentialsKillerPlugin extends JavaPlugin implements Listener {
	
	public static EssentialsKillerPlugin instance;
	
	private FilesManager filemanager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		
		this.registerCommands();
		this.registerEvents();
		getServer().getPluginManager().registerEvents((Listener) this, this);
		
		filemanager = new FilesManager(this);
		
		System.out.println("essentialskiller enabled !");
	}
	
	private void registerCommands() {
		this.getCommand("ping").setExecutor(new PingCommandHandler());
		this.getCommand("spawn").setExecutor(new SpawnCommandHandler());
		this.getCommand("warp").setExecutor(new WarpCommandHandler());
		new BackCommandHandler(this);
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
	
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Item droppedItem = event.getItemDrop();
        
        if (droppedItem != null && Arrays.asList(Material.GLASS_BOTTLE, Material.BOWL).contains(droppedItem.getItemStack().getType())) {
            droppedItem.remove();
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
    	
        if (
        	Arrays.asList(
        		GameMode.SURVIVAL,
        		GameMode.ADVENTURE
        	).contains(event.getPlayer().getGameMode())) {
            event.setCancelled(true); // Cancel the block break event if the player is in survival mode
        }
    }
    
    public Map<UUID, Location> lastDeathPos = new HashMap<>();
    private Map<UUID, Collection<PotionEffect>> playerEffects = new HashMap<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        
        lastDeathPos.put(player.getUniqueId(), player.getLocation());
        playerEffects.put(player.getUniqueId(), player.getActivePotionEffects());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (playerEffects.containsKey(player.getUniqueId())) {

        	(new BukkitRunnable() {
				
				@Override
				public void run() {
		            for (PotionEffect effect : playerEffects.get(player.getUniqueId())) {
		                player.addPotionEffect(effect, true);
		            }

		            playerEffects.remove(player.getUniqueId());
				}
				
			}).runTask(this);
			
        }
    }
    
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(event.toWeatherState());
    }
}
