package eu.maestro.essentialskiller.handler;
import eu.maestro.essentialskiller.EssentialsKillerPlugin;
import eu.maestro.essentialskiller.handler.manager.FilesManager;

public class ManagerHandler {
	
	private FilesManager fileManager;
	public FilesManager getFileManager() { return fileManager; }
	
	public ManagerHandler(final EssentialsKillerPlugin plugin) {
		this.fileManager = new FilesManager(plugin);
	}

}
