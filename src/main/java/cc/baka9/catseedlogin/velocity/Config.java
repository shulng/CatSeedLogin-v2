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

    public static boolean Enable;
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
                if (in != null) {
                    Files.copy(in, configFile);
                } else {
                    // 创建默认配置
                    createDefaultConfig(configFile);
                }
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
            
            Enable = (Boolean) config.getOrDefault("Enable", false);
            Host = (String) config.getOrDefault("Host", "127.0.0.1");
            Port = (Integer) config.getOrDefault("Port", 10086);
            LoginServerName = (String) config.getOrDefault("LoginServerName", "login");
            AuthKey = (String) config.getOrDefault("AuthKey", "default");
            
            logger.info("Host: " + Host);
            logger.info("Port: " + Port);
            logger.info("LoginServerName: " + LoginServerName);
            
        } catch (IOException e) {
            logger.error("Failed to load config", e);
        }
    }
    
    private static void createDefaultConfig(Path configFile) throws IOException {
        String defaultConfig = 
            "# CatSeedLogin-Velocity Configuration\n" +
            "# 是否启用Velocity适配\n" +
            "Enable: true\n" +
            "\n" +
            "# 登录服监听的主机地址\n" +
            "Host: 127.0.0.1\n" +
            "\n" +
            "# 登录服监听的端口\n" +
            "Port: 10086\n" +
            "\n" +
            "# 登录服务器名称\n" +
            "LoginServerName: login\n" +
            "\n" +
            "# 通信密钥，必须与Bukkit端配置一致\n" +
            "AuthKey: default\n";
            
        Files.write(configFile, defaultConfig.getBytes());
    }
}