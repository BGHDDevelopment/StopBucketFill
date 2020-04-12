package me.noodles.sbf.commands;

import com.google.common.collect.ImmutableList;
import me.noodles.sbf.SBF;
import me.noodles.sbf.utilities.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-12 03:09
 */
public final class SBFCommand implements TabExecutor {
    private final ImmutableList<String> keywords = ImmutableList.of("reload");
    private final SBF plugin;

    private final String PERMISSION = "sbf.reload";

    public SBFCommand(SBF plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (player.hasPermission(PERMISSION)) {
                if (args.length >= 1) {
                    if (getKeywords().contains(args[0])) {
                        final String keyword = args[0];

                        if (keyword.equalsIgnoreCase("reload")) {
                            getPlugin().reloadConfig();

                            Common.tell(player, "&aConfig has been reloaded.");

                            return true;
                        }
                    }
                }

                return false;
            }

            Common.tell(player, "&cYou don't have permission to use this command!");

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission(PERMISSION)) ? StringUtil.copyPartialMatches(args[0], getKeywords(), new ArrayList<>()) : Collections.emptyList();
    }

    private ImmutableList<String> getKeywords() {
        return keywords;
    }

    private SBF getPlugin() {
        return plugin;
    }

}
