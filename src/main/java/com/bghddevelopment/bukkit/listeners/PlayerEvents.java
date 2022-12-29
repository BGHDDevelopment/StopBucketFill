package com.bghddevelopment.bukkit.listeners;

import com.bghddevelopment.bukkit.SBF;
import com.bghddevelopment.bukkit.utilities.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerBucket(final PlayerBucketFillEvent event) {
        final String message = SBF.getInstance().getConfig().getString("Message");
        final Player player = event.getPlayer();

        if (!player.hasPermission("sbf.allow")) {
            if (message != null && !message.isEmpty()) {
                Color.tell(player, message);
            }

            event.setCancelled(true);
        }
    }
}
