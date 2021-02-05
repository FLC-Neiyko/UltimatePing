package fr.neiyko.ultimateping.commands;

import fr.neiyko.ultimateping.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class PingReload implements CommandExecutor {

    Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(main.getMessages("not-instance-of-player").replace("&", "§"));
            return false;
        }

        Player p = (Player) sender;

        if (main.hasPermission(p, "permission.reload-permission")) {
            PluginManager pm = main.getServer().getPluginManager();
            long start_timer = System.currentTimeMillis();
            try {
                pm.disablePlugin(pm.getPlugin("UltimatePing"));
                pm.enablePlugin(pm.getPlugin("UltimatePing"));
            } catch (Exception e) {
                p.sendMessage(main.getMessages("reloadError").replace("&", "§"));
                return false;
            }
            long end_timer = System.currentTimeMillis();
            p.sendMessage(main.getMessages("reloadComplete").replace("&", "§").replace("%timerMS%", end_timer - start_timer + ""));
        } else {
            p.sendMessage(main.getMessages("permission.no-reload-perm").replace("&", "§"));
        }

        return false;
    }
}
