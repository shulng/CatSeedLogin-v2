package cc.baka9.catseedlogin.velocity.config;

import cc.baka9.catseedlogin.velocity.PluginMain;
import cc.baka9.catseedlogin.common.config.BaseConfigManager;
import cc.baka9.catseedlogin.common.config.ConfigConstants;
import cc.baka9.catseedlogin.common.config.YamlConfiguration;
import cc.baka9.catseedlogin.common.i18n.I18n;

import java.io.File;
import java.io.InputStream;

public class VelocityConfigManager extends BaseConfigManager {

    private final PluginMain plugin;

    public VelocityConfigManager(PluginMain plugin) {
        super();
        this.plugin = plugin;
        initConfig(plugin.getDataDirectory().toFile(), "config.yml");
    }

    @Override
    protected InputStream getResource(String name) {
        if (name.startsWith("languages/")) {
            return getClass().getClassLoader().getResourceAsStream(name);
        }
        return getClass().getClassLoader().getResourceAsStream(name);
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
            plugin.getLogger().warn("无法加载默认配置文件: " + e.getMessage());
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
                plugin.getLogger().warn("无法创建默认配置文件: " + e.getMessage());
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
                plugin.getLogger().warn("保存配置文件失败: " + e.getMessage());
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

    public File getDataFolder() {
        return dataFolder;
    }
}
