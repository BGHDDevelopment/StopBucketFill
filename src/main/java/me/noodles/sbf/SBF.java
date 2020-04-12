package me.noodles.sbf;

import me.noodles.sbf.listeners.UpdateJoinEvent;
import me.noodles.sbf.utilities.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class SBF extends JavaPlugin implements Listener {


    public static SBF plugin;
    private UpdateChecker checker;




    public void onEnable() {
        SBF.plugin = this;
        final PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("StopBucketFill V" + VarUtilType.getVersion() + " starting...");
        this.saveDefaultConfig();
        this.reloadConfig();
        registerEvents((Plugin)this, new UpdateJoinEvent(this));
        registerEvents((Plugin)this, new Events());
        registerEvents(this, this);
        this.getLogger().info("StopBucketFill V" + VarUtilType.getVersion() + " started!");
        this.setEnabled(true);
        this.getLogger().info("StopBucketFill V" + VarUtilType.getVersion() + " checking for updates...");

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 22841).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s is out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/22841");
                }
            });
        }
    }

    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static SBF getPlugin() {
        return (SBF)getPlugin((Class)SBF.class);
    }

}
