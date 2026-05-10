package cc.baka9.catseedlogin.bungee.config;

import cc.baka9.catseedlogin.bungee.PluginMain;
import cc.baka9.catseedlogin.common.config.BaseConfigManager;
import cc.baka9.catseedlogin.common.config.ConfigConstants;
import cc.baka9.catseedlogin.common.config.ConfigHelper;
import cc.baka9.catseedlogin.common.config.YamlConfiguration;
import cc.baka9.catseedlogin.common.api.CoreConfig;

import java.io.File;
import java.io.InputStream;

public class BungeeConfigManager extends BaseConfigManager {

    private final PluginMain plugin;

    public BungeeConfigManager(PluginMain plugin) {
        super();
        this.plugin = plugin;
        initConfig(plugin.getDataFolder(), "config.yml");
    }

    @Override
    public InputStream getResource(String name) {
        if (name.startsWith("languages/")) {
            return plugin.getResourceAsStream(name);
        }
        return plugin.getResourceAsStream(name);
    }

    @Override
    public YamlConfiguration getConfig(String name) {
        String fileName = name.endsWith(".yml") ? name : name + ".yml";
        File file = new File(dataFolder, fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        
        String resourcePath = fileName;
        try (InputStream defaultStream = getResource(resourcePath)) {
            if (defaultStream != null) {
                YamlConfiguration defaultConfig = new YamlConfiguration(null);
                defaultConfig.loadFromResource(defaultStream);
                mergeDefaults(config, defaultConfig);
            }
        } catch (Exception e) {
            plugin.getLogger().warning("无法加载默认配置文件: " + e.getMessage());
        }
        
        return config;
    }

    @Override
    public void createDefaultConfig(String name) {
        String fileName = name.endsWith(".yml") ? name : name + ".yml";
        File file = new File(dataFolder, fileName);
        if (!file.exists()) {
            try (InputStream in = getResource(fileName)) {
                if (in != null) {
                    java.nio.file.Files.copy(in, file.toPath());
                }
            } catch (Exception e) {
                plugin.getLogger().warning("无法创建默认配置文件: " + e.getMessage());
            }
        }
    }

    @Override
    public void saveConfig(String name) {
        YamlConfiguration config = getConfig(name);
        if (config != null) {
            try {
                config.save();
            } catch (Exception e) {
                plugin.getLogger().warning("保存配置文件失败: " + e.getMessage());
            }
        }
    }

    private void mergeDefaults(YamlConfiguration config, YamlConfiguration defaults) {
        for (java.util.Map.Entry<String, Object> entry : defaults.getDataMap().entrySet()) {
            if (!config.contains(entry.getKey())) {
                config.set(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public CoreConfig.SpawnLocation getSpawnLocation() {
        String locStr = mainConfig.getString(ConfigConstants.Path.SPAWN_LOCATION, ConfigConstants.DEFAULT_SPAWN_LOCATION);
        return parseSpawnLocation(locStr);
    }

    private CoreConfig.SpawnLocation parseSpawnLocation(String str) {
        ConfigHelper.LocationData data = ConfigHelper.parseLocationString(str, 
            new ConfigHelper.LocationData("world", 0, 64, 0, 0, 0));
        return new CoreConfig.SpawnLocation() {
            @Override
            public String getWorld() {
                return data.world;
            }

            @Override
            public double getX() {
                return data.x;
            }

            @Override
            public double getY() {
                return data.y;
            }

            @Override
            public double getZ() {
                return data.z;
            }

            @Override
            public float getYaw() {
                return data.yaw;
            }

            @Override
            public float getPitch() {
                return data.pitch;
            }
        };
    }

    public File getDataFolder() {
        return dataFolder;
    }
}
