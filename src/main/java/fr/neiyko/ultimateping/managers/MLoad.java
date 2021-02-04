package fr.neiyko.ultimateping.managers;

import fr.neiyko.ultimateping.Main;

import java.util.logging.Level;

public class MLoad {

    private Main main = Main.getInstance();

    public void pluginLoad() {

        long start_timer = System.currentTimeMillis();

        main.logConsole(Level.INFO, "=== Beginning of loading ===");
        main.logConsole(Level.INFO, "Loading the plugin...");
        main.logConsole(Level.INFO, "---");

        main.getCommandsManager().initCommands();
        main.getFileManager().initFile();

        long end_timer = System.currentTimeMillis();
        main.logConsole(Level.INFO, "Loading completed in " + (start_timer-end_timer) + " ms");
        pluginEnable();
    }

    public void pluginEnable() {

        main.logConsole(Level.INFO, "----");
        main.logConsole(Level.INFO, "Plugin UltimatePing");
        main.logConsole(Level.INFO, "Status: Enabled");
        main.logConsole(Level.INFO, "----");

    }

    public void pluginDisable() {

        main.logConsole(Level.INFO, "----");
        main.logConsole(Level.INFO, "Plugin UltimatePing");
        main.logConsole(Level.INFO, "Status: Disabled");
        main.logConsole(Level.INFO, "----");
        
    }

}
