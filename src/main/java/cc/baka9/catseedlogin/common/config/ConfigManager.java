package cc.baka9.catseedlogin.common.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ConfigManager {

    protected final File dataFolder;
    protected final Map<String, YamlConfiguration> configs = new ConcurrentHashMap<>();
    protected final Map<String, Object> defaultValues = new HashMap<>();

    public ConfigManager(File dataFolder) {
        this.dataFolder = dataFolder;
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public YamlConfiguration getConfig(String name) {
        return configs.computeIfAbsent(name, this::loadConfig);
    }

    protected YamlConfiguration loadConfig(String name) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return config;
    }

    protected abstract InputStream getResource(String name);

    @SuppressWarnings("unchecked")
    private void mergeDefaults(YamlConfiguration config, YamlConfiguration defaults) {
        for (Map.Entry<String, Object> entry : defaults.getDataMap().entrySet()) {
            if (!config.contains(entry.getKey())) {
                config.set(entry.getKey(), entry.getValue());
            }
        }
    }

    public void reloadConfig(String name) {
        if (name == null) return;
        configs.remove(name);
        getConfig(name);
    }

    public void reloadAll() {
        for (String name : new HashMap<>(configs).keySet()) {
            if (name != null) {
                reloadConfig(name);
            }
        }
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public void createDefaultConfig(String name) {
        if (name == null) return;
        String fileName = name.endsWith(".yml") ? name : name + ".yml";
        File file = new File(dataFolder, fileName);
        if (!file.exists()) {
            try (InputStream in = getResource(fileName)) {
                if (in != null) {
                    java.nio.file.Files.copy(in, file.toPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveConfig(String name) {
        if (name == null) return;
        YamlConfiguration config = configs.get(name);
        if (config != null) {
            try {
                config.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
