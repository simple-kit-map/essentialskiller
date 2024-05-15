package eu.maestro.essentialskiller.handler.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import eu.maestro.essentialskiller.EssentialsKillerPlugin;
import eu.maestro.essentialskiller.SavedLocation;
public class FilesManager {
	
	private List<SavedLocation> warps;
	public List<SavedLocation> getWarps() { return warps; }
	
	private Map<String, List<SavedLocation>> homes;
	public Map<String, List<SavedLocation>> getHomes() { return homes; }

	private final @NotNull YamlConfiguration configFile;
	
	public FilesManager(EssentialsKillerPlugin plugin) {
		this.warps = new ArrayList<>();
		
		final String locationsPath = plugin.getDataFolder() + "/locations.yml";

        final File file = new File(locationsPath);
        if (!file.exists()) {
        	try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        configFile = YamlConfiguration.loadConfiguration(file);
        
        String sectionName = "warps";
        
        for (String key : configFile.getConfigurationSection(sectionName).getKeys(false)) {
        	
        	for (String name: configFile.getConfigurationSection(sectionName + "." + key).getKeys(false)){
                double x = configFile.getDouble(sectionName + "." + key +  "." + name + ".x");
                double y = configFile.getDouble(sectionName + "." + key +  "." + name + ".y");
                double z = configFile.getDouble(sectionName + "." + key +  "." + name + ".z");
                float pitch = (float) configFile.getDouble(sectionName + "." + key +  "." + name + ".pitch");
                float yaw = (float) configFile.getDouble(sectionName + "." + key + "." +  name + ".yaw");

                new SavedLocation(
                	UUID.randomUUID(),
                	name,
                	new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch),
                	LocationType.Warp
                );
        	}


        }
        
	}
}