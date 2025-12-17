package cc.baka9.catseedlogin.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.config.ProxyConfig;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Velocity 配置类
 * 适配原Bungee版本的配置结构
 */
public class Config {

    public static String Host;
    public static int Port;
    public static String LoginServerName;
    public static String AuthKey;

    public static void load() {
        PluginMain plugin = PluginMain.getInstance();
        ProxyServer proxyServer = plugin.getProxyServer();
        Logger logger = plugin.getLogger();
        
        Path dataFolder = Paths.get("plugins", "CatSeedLogin-Velocity");
        if (!Files.exists(dataFolder)) {
            try {
                Files.createDirectories(dataFolder);
            } catch (IOException e) {
                logger.error("Failed to create plugin directory", e);
                return;
            }
        }

        String fileName = "velocity.yml";
        Path configFile = dataFolder.resolve(fileName);
        
        if (!Files.exists(configFile)) {
            try (InputStream in = Config.class.getClassLoader().getResourceAsStream("velocity-resources/velocity.yml")) {
                Files.copy(in, configFile);
            } catch (IOException e) {
                logger.error("Failed to create default config", e);
                return;
            }
        }

        try {
            Yaml yaml = new Yaml();
            Map<String, Object> config;
            
            try (InputStream input = Files.newInputStream(configFile)) {
                config = yaml.load(input);
            }

            Host = String.valueOf(config.get("Host"));
            Port = Integer.parseInt(String.valueOf(config.get("Port")));
            LoginServerName = String.valueOf(config.get("LoginServerName"));
            AuthKey = String.valueOf(config.get("AuthKey"));
            logger.info("Host: " + Host);
            logger.info("Port: " + Port);
            logger.info("LoginServerName: " + LoginServerName);
        } catch (IOException e) {
            logger.error("Failed to load config", e);
        }
    }
}