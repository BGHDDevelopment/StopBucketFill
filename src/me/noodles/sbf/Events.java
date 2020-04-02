package me.noodles.sbf;

import org.bukkit.plugin.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Events implements Listener
{

	String Message;
	
	 public Events() {
	        this.Message = ChatColor.translateAlternateColorCodes('&', MainSBF.getPlugin().getConfig().getString("Messages.Message"));
	
	 } 
	
    public Events(final MainSBF plugin) {
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void bucket(final PlayerBucketFillEvent e) {
        final Player player = e.getPlayer();
        if (!player.hasPermission("sbf.allow")) {
    		player.sendMessage(String.valueOf(this.Message));
            e.setCancelled(true);
        }
    }
}
