package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.common.CommonConfig;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

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
public class VelocityConfig implements CommonConfig {

    private String host;
    private int port;
    private String loginServerName;
    private String authKey;

    @Override
    public void load() {
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
            try (InputStream in = VelocityConfig.class.getClassLoader().getResourceAsStream("velocity-resources/velocity.yml")) {
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

            this.host = String.valueOf(config.get("Host"));
            this.port = Integer.parseInt(String.valueOf(config.get("Port")));
            this.loginServerName = String.valueOf(config.get("LoginServerName"));
            this.authKey = String.valueOf(config.get("AuthKey"));
            logger.info("Host: " + host);
            logger.info("Port: " + port);
            logger.info("LoginServerName: " + loginServerName);
        } catch (IOException e) {
            logger.error("Failed to load config", e);
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