package fr.neiyko.ultimateping.managers;

import fr.neiyko.ultimateping.Main;
import fr.neiyko.ultimateping.commands.Ping;

public class MCommands {

    private Main main = Main.getInstance();

    public void initCommands() {
        main.getCommand("ping").setExecutor(new Ping());
    }

}
