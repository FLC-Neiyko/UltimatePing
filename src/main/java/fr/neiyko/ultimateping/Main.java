package fr.neiyko.ultimateping;

import fr.neiyko.ultimateping.managers.MCommands;
import fr.neiyko.ultimateping.managers.MFiles;
import fr.neiyko.ultimateping.managers.MLoad;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private static Main instance;
    private MLoad managerload;
    private MCommands commandsManager;
    private MFiles fileManager;


    public static Main getInstance() {
        return instance;
    }

    public File configFile = new File(getDataFolder().getPath() + "/config.yml");
    public FileConfiguration fileConfigConfiguration;

    public File messagesFile = new File(getDataFolder().getPath() + "/messages.yml");
    public FileConfiguration fileConfigMessages;

    @Override
    public void onEnable() {
        instance = this;
        managerload = new MLoad();
        fileManager = new MFiles();
        commandsManager = new MCommands();
        managerload.pluginLoad();

    }

    @Override
    public void onDisable() {
        managerload.pluginDisable();
    }

    public MLoad getManagerload() {
        return managerload;
    }

    public void logConsole(Level level, String error) {
        getLogger().log(level, error);
    }

    public MCommands getCommandsManager() {
        return commandsManager;
    }

    public MFiles getFileManager() {
        return fileManager;
    }

    public String getPermission(String perm) {
        return fileConfigConfiguration.getString(perm);
    }

    public String getMessages(String msg) {
        return fileConfigMessages.getString(msg);
    }

    public boolean hasPermission(Player p, String action) {
        if (p.hasPermission(getPermission(action))){
            return true;
        }
        return false;
    }
}