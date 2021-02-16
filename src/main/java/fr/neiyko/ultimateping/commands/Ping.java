package fr.neiyko.ultimateping.commands;

import fr.neiyko.ultimateping.Main;
import fr.neiyko.ultimateping.utils.PingUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class Ping implements CommandExecutor {

    private Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(main.getMessages("not-instance-of-player").replace("&", "§"));
            return false;
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            if (!permEnabled(p, main.fileConfigConfiguration.getString("permission.ping"))) {
                p.sendMessage(main.getMessages("permission.no-perm").replace("&", "§"));
                 return true;
            }
            String ping = "" + PingUtil.getPing(p);
            p.sendMessage(main.getMessages("ping-message").replace("&", "§").replace("%ping%", ping));
        } else if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
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
                p.sendMessage(main.getMessages("reloadComplete").replace("%timerMS%", start_timer - end_timer + "").replace("&", "§"));
            } else {
                p.sendMessage(main.getMessages("permission.no-reload-perm").replace("&", "§"));
            }
        } else {
            if (!permEnabled(p, main.fileConfigConfiguration.getString("permission.ping-other"))) {
                p.sendMessage(main.getMessages("permission.no-perm").replace("&", "§"));
                return true;
            }
            String target = args[0];
            Player targetP = Bukkit.getPlayer(target);
            if (targetP == null) {
                p.sendMessage(main.getMessages("player-not-found").replace("&", "§").replace("%target%", target));
                return true;
            }
            p.sendMessage(main.getMessages("ping-other-message").replace("&", "§").replace("%target%", target).replace("%ping%", "" + PingUtil.getPing(targetP)));
        }
        return false;
    }

    private boolean permEnabled(Player p, String perm) {
        return (!main.fileConfigConfiguration.getBoolean("permission.enabled") || p.hasPermission(perm));
    }
}
