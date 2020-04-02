package me.noodles.sbf;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.*;

public class MainSBF extends JavaPlugin implements Listener  {
	
	
    public static MainSBF plugin;
    private UpdateChecker checker;
    

	
	
	 public void onEnable() {
	        MainSBF.plugin = this;
	        final PluginDescriptionFile VarUtilType = this.getDescription();
	        this.getLogger().info("StopBucketFill V" + VarUtilType.getVersion() + " starting...");
	        this.saveDefaultConfig();
	        this.reloadConfig();
	        registerEvents((Plugin)this, new UpdateJoinEvent());
	        registerEvents((Plugin)this, new Events());
	        registerEvents(this, this);
	        this.getLogger().info("StopBucketFill V" + VarUtilType.getVersion() + " started!");
	        this.setEnabled(true);
	        this.getLogger().info("StopBucketFill V" + VarUtilType.getVersion() + " checking for updates...");
	        this.checker = new UpdateChecker(this);
	        if (this.checker.isConnected()) {
	            if (this.checker.hasUpdate()) {
	                getServer().getConsoleSender().sendMessage("------------------------");
	                getServer().getConsoleSender().sendMessage("StopBucketFill is outdated!");
	                getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
	                getServer().getConsoleSender().sendMessage("Your version: " + MainSBF.plugin.getDescription().getVersion());
	                getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/46678");
	                getServer().getConsoleSender().sendMessage("------------------------");
	            }
	            else {
	                getServer().getConsoleSender().sendMessage("------------------------");
	                getServer().getConsoleSender().sendMessage("StopBucketFill is up to date!");
	                getServer().getConsoleSender().sendMessage("------------------------");            }
	        }
	    }
	 
	    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
	        for (final Listener listener : listeners) {
	            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	        }
	    }

		public static MainSBF getPlugin() {
	        return (MainSBF)getPlugin((Class)MainSBF.class);
	    }
	    
}
	 