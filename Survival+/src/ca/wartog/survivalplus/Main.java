package ca.wartog.survivalplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ca.wartog.survivalplus.listeners.Events;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		//init events
		Bukkit.getPluginManager().registerEvents(new Events(), this);
	}
	
	public static Main getInstance() {
		return instance;
	}

}
