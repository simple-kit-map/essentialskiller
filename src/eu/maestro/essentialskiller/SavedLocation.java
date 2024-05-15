package eu.maestro.essentialskiller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;

import eu.maestro.essentialskiller.handler.manager.LocationType;

public class SavedLocation {
	
	public final static Set<SavedLocation> locations = new HashSet<SavedLocation>();
	
	private UUID creator;
	public UUID getCreator() {return creator;};

	private String name;
	public String getName() {return name;};
	
	private Location location;
	public Location getLocation() {return location;};
	
	private LocationType type;
	public LocationType getType() {return type;};
	
	public SavedLocation(
			final UUID creator,
			final String name,
			final Location location, 
			final LocationType type
			) {
		this.creator = creator;
		this.name = name;
		this.location = location;
		this.type = type;
		locations.add(this); 
	}
}
