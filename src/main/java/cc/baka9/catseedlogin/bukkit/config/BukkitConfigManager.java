package cc.baka9.catseedlogin.bukkit.config;

import cc.baka9.catseedlogin.bukkit.CatSeedLogin;
import cc.baka9.catseedlogin.common.config.BaseConfigManager;
import cc.baka9.catseedlogin.common.config.ConfigConstants;
import cc.baka9.catseedlogin.common.config.YamlConfiguration;
import cc.baka9.catseedlogin.common.api.CoreConfig;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.InputStream;

public class BukkitConfigManager extends BaseConfigManager {

    private final CatSeedLogin plugin;

    public BukkitConfigManager(CatSeedLogin plugin) {
        super();
        this.plugin = plugin;
        initConfig(plugin.getDataFolder(), "config.yml");
    }

    @Override
    public InputStream getResource(String name) {
        return plugin.getResource(name);
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

    public void setSpawnLocation(Location location) {
        String locStr = String.format("%s:%.2f:%.2f:%.2f:%.2f:%.2f",
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch());
        mainConfig.set(ConfigConstants.Path.SPAWN_LOCATION, locStr);
        saveConfig("config.yml");
    }

    public Location getBukkitSpawnLocation() {
        CoreConfig.SpawnLocation spawn = getSpawnLocation();
        World world = Bukkit.getWorld(spawn.getWorld());
        if (world == null) {
            world = Bukkit.getWorlds().get(0);
        }
        return new Location(world, spawn.getX(), spawn.getY(), spawn.getZ(), spawn.getYaw(), spawn.getPitch());
    }

    public File getDataFolder() {
        return dataFolder;
    }
}
