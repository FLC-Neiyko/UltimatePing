package fr.neiyko.ultimateping.managers;

import fr.neiyko.ultimateping.Main;
import fr.neiyko.ultimateping.commands.Ping;
import fr.neiyko.ultimateping.commands.PingReload;

public class MCommands {

    private Main main = Main.getInstance();

    public void initCommands() {
        main.getCommand("ping").setExecutor(new Ping());
        main.getCommand("pingreload").setExecutor(new PingReload());
    }

}
