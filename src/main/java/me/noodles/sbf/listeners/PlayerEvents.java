package me.noodles.sbf.listeners;

import me.noodles.sbf.SBF;
import me.noodles.sbf.utilities.Common;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

public final class PlayerEvents implements Listener {
    private final SBF plugin;
	
    public PlayerEvents(final SBF plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerBucket(final PlayerBucketFillEvent event) {
        final String message = getPlugin().getConfig().getString("Messages.Message");
        final Player player = event.getPlayer();

        if (!player.hasPermission("sbf.allow")) {
            if (message != null && !message.isEmpty()) {
                Common.tell(player, message);
            }

            event.setCancelled(true);
        }
    }

    private SBF getPlugin() {
        return plugin;
    }

}
