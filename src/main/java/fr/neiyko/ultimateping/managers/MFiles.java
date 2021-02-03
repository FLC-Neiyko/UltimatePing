package fr.neiyko.ultimateping.managers;

import fr.neiyko.ultimateping.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class MFiles {

    private Main main = Main.getInstance();

    public void initFile() {

        ressourceSetup("config.yml", true);
        main.fileConfigConfiguration = YamlConfiguration.loadConfiguration(main.configFile);

        ressourceSetup("messages.yml", true);
        main.fileConfigMessages = YamlConfiguration.loadConfiguration(main.messagesFile);

    }

    public void ressourceSetup(String fileName, boolean reset) {
        InputStream in = main.getResource((fileName));

        if (in == null) {
            throw new IllegalArgumentException("The '" + fileName + "' resource was not found !");
        }

        File outDir = new File(main.getDataFolder(), "");
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        String fileNameString = fileName.toLowerCase();
        if (fileNameString.equals("config.yml") || fileName.equals("messages.yml")) {
            File outFile = new File(main.getDataFolder(), fileName);
            if (!outFile.exists()) {
                try {
                    OutputStream out = new FileOutputStream(outFile);
                    byte[] buf = new byte['?'];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.close();
                    in.close();
                    return;
                } catch (Exception e) {
                    main.logConsole(Level.WARNING, "The '" + fileName + "' was not found !");
                }
            }
        }
    }

}
