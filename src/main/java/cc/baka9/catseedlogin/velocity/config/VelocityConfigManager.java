package cc.baka9.catseedlogin.velocity.config;

import cc.baka9.catseedlogin.velocity.PluginMain;
import cc.baka9.catseedlogin.common.config.ConfigManager;
import cc.baka9.catseedlogin.common.config.YamlConfiguration;
import cc.baka9.catseedlogin.common.i18n.I18n;
import cc.baka9.catseedlogin.common.api.BungeeCordConfig;

import java.io.File;
import java.io.InputStream;

public class VelocityConfigManager extends ConfigManager implements BungeeCordConfig {

    private final PluginMain plugin;
    private I18n i18n;
    private YamlConfiguration mainConfig;

    public VelocityConfigManager(PluginMain plugin) {
        super(plugin.getDataDirectory().toFile());
        this.plugin = plugin;
        init();
    }

    private void init() {
        createDefaultConfig("config.yml");
        mainConfig = getConfig("config.yml");
        
        i18n = new I18n(dataFolder, this::getResource);
        String language = mainConfig.getString("language", "zh_CN");
        i18n.setLocale(language.replace("_", "-"));
    }

    @Override
    protected InputStream getResource(String name) {
        if (name.startsWith("languages/")) {
            return getClass().getClassLoader().getResourceAsStream(name);
        }
        return getClass().getClassLoader().getResourceAsStream(name);
    }

    public I18n getI18n() {
        return i18n;
    }

    public YamlConfiguration getMainConfig() {
        return mainConfig;
    }

    public void reload() {
        reloadAll();
        mainConfig = getConfig("config.yml");
        String language = mainConfig.getString("language", "zh_CN");
        i18n.setLocale(language.replace("_", "-"));
        i18n.reload();
    }

    @Override
    public boolean isEnable() {
        return mainConfig.getBoolean("proxy.enabled", false);
    }

    @Override
    public String getProxyHost() {
        return mainConfig.getString("proxy.host", "127.0.0.1");
    }

    @Override
    public int getProxyPort() {
        return mainConfig.getInt("proxy.port", 2333);
    }

    @Override
    public String getAuthKey() {
        return mainConfig.getString("proxy.auth-key", "");
    }

    @Override
    public String getLoginServerName() {
        return mainConfig.getString("proxy.login-server-name", "lobby");
    }
}
