package cc.baka9.catseedlogin.bungee;

import cc.baka9.catseedlogin.common.CommonConfig;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.config.ConfigurationProvider;

public class BungeeConfig implements CommonConfig {

    private String host;
    private int port;
    private String loginServerName;
    private String authKey;

    @Override
    public void load() {
        File dataFolder = PluginMain.instance.getDataFolder();
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
        }

        String fileName = "bungeecord.yml";
        File configFile = new File(dataFolder, fileName);
        if (!configFile.exists()) {
            try (InputStream in = PluginMain.instance.getResourceAsStream("bungee-resources/bungeecord.yml")) {
                Files.copy(in, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ConfigurationProvider configurationProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            Configuration config = configurationProvider.load(configFile);
            this.host = config.getString("Host");
            this.port = config.getInt("Port");
            this.loginServerName = config.getString("LoginServerName");
            this.authKey = config.getString("AuthKey");
            PluginMain.instance.getLogger().info("Host:" + host);
            PluginMain.instance.getLogger().info("Port:" + port);
            PluginMain.instance.getLogger().info("LoginServerName:" + loginServerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getLoginServerName() {
        return loginServerName;
    }

    @Override
    public String getAuthKey() {
        return authKey;
    }
}
