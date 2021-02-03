package fr.neiyko.ultimateping.commands;

import fr.neiyko.ultimateping.Main;
import fr.neiyko.ultimateping.utils.PingUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

    private Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(main.getMessages("not-instance-of-player").replace("&", "§"));
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            if (!permEnabled(p, main.fileConfigConfiguration.getString("permission.ping"))) {
                p.sendMessage(main.getMessages("permission.no-perm").replace("&", "§"));
                 return true;
            }
            String ping = "" + PingUtil.getPing(p);
            p.sendMessage(main.getMessages("ping-message").replace("&", "§").replace("%ping%", ping));
        } else {
            if (!permEnabled(p, main.fileConfigConfiguration.getString("permission.ping-other"))) {
                p.sendMessage(main.getMessages("permission.no-perm").replace("&", "§"));
                return true;
            }
            String target = (args.length > 0) ? args[0] : null;
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
