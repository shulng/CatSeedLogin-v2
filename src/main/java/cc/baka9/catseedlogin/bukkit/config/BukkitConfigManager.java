package cc.baka9.catseedlogin.bukkit.config;

import cc.baka9.catseedlogin.bukkit.CatSeedLogin;
import cc.baka9.catseedlogin.common.config.ConfigManager;
import cc.baka9.catseedlogin.common.config.YamlConfiguration;
import cc.baka9.catseedlogin.common.i18n.I18n;
import cc.baka9.catseedlogin.common.api.CoreConfig;
import cc.baka9.catseedlogin.common.api.DatabaseConfig;
import cc.baka9.catseedlogin.common.api.BungeeCordConfig;
import cc.baka9.catseedlogin.common.api.EmailConfig;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BukkitConfigManager extends ConfigManager implements CoreConfig, DatabaseConfig, BungeeCordConfig, EmailConfig {

    private final CatSeedLogin plugin;
    private I18n i18n;
    private YamlConfiguration mainConfig;

    public BukkitConfigManager(CatSeedLogin plugin) {
        super(plugin.getDataFolder());
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
        return plugin.getResource(name);
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
    public int getIpRegisterCountLimit() {
        return mainConfig.getInt("settings.ip-register-count-limit", 2);
    }

    @Override
    public int getIpCountLimit() {
        return mainConfig.getInt("settings.ip-count-limit", 2);
    }

    @Override
    public boolean isLimitChineseID() {
        return mainConfig.getBoolean("settings.limit-chinese-id", true);
    }

    @Override
    public boolean isBedrockLoginBypass() {
        return mainConfig.getBoolean("bedrock.login-bypass", true);
    }

    @Override
    public boolean isLoginWithSameIP() {
        return mainConfig.getBoolean("same-ip-login.enabled", false);
    }

    @Override
    public boolean isEmptyBackpack() {
        return mainConfig.getBoolean("empty-backpack", true);
    }

    @Override
    public int getIPTimeout() {
        return mainConfig.getInt("same-ip-login.timeout", 5);
    }

    @Override
    public int getMaxLengthID() {
        return mainConfig.getInt("settings.max-length-id", 15);
    }

    @Override
    public int getMinLengthID() {
        return mainConfig.getInt("settings.min-length-id", 2);
    }

    @Override
    public boolean isBeforeLoginNoDamage() {
        return mainConfig.getBoolean("settings.before-login-no-damage", true);
    }

    @Override
    public long getReenterInterval() {
        return mainConfig.getLong("settings.reenter-interval", 60);
    }

    @Override
    public boolean isAfterLoginBack() {
        return mainConfig.getBoolean("settings.after-login-back", true);
    }

    @Override
    public boolean isCanTpSpawnLocation() {
        return mainConfig.getBoolean("settings.can-tp-spawn-location", true);
    }

    @Override
    public int getAutoKick() {
        return mainConfig.getInt("settings.auto-kick", 120);
    }

    @Override
    public String getNamePattern() {
        return mainConfig.getString("settings.name-pattern", "^\\w+$");
    }

    @Override
    public boolean isDeathStateQuitRecordLocation() {
        return mainConfig.getBoolean("settings.death-state-quit-record-location", true);
    }

    @Override
    public boolean isFloodgatePrefixProtect() {
        return mainConfig.getBoolean("bedrock.floodgate-prefix-protect", true);
    }

    @Override
    public List<Pattern> getCommandWhiteList() {
        List<String> patterns = mainConfig.getStringList("settings.command-white-list");
        return patterns.stream()
                .map(Pattern::compile)
                .collect(Collectors.toList());
    }

    @Override
    public SpawnLocation getSpawnLocation() {
        String locStr = mainConfig.getString("spawn.location", "world:0:64:0:0:0");
        return parseSpawnLocation(locStr);
    }

    public void setSpawnLocation(Location location) {
        String locStr = String.format("%s:%.2f:%.2f:%.2f:%.2f:%.2f",
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch());
        mainConfig.set("spawn.location", locStr);
        saveConfig("config.yml");
    }

    private SpawnLocation parseSpawnLocation(String str) {
        String[] parts = str.split(":");
        return new SpawnLocation() {
            @Override
            public String getWorld() {
                return parts.length > 0 ? parts[0] : "world";
            }

            @Override
            public double getX() {
                return parts.length > 1 ? Double.parseDouble(parts[1]) : 0;
            }

            @Override
            public double getY() {
                return parts.length > 2 ? Double.parseDouble(parts[2]) : 64;
            }

            @Override
            public double getZ() {
                return parts.length > 3 ? Double.parseDouble(parts[3]) : 0;
            }

            @Override
            public float getYaw() {
                return parts.length > 4 ? Float.parseFloat(parts[4]) : 0;
            }

            @Override
            public float getPitch() {
                return parts.length > 5 ? Float.parseFloat(parts[5]) : 0;
            }
        };
    }

    public Location getBukkitSpawnLocation() {
        SpawnLocation spawn = getSpawnLocation();
        World world = Bukkit.getWorld(spawn.getWorld());
        if (world == null) {
            world = Bukkit.getWorlds().get(0);
        }
        return new Location(world, spawn.getX(), spawn.getY(), spawn.getZ(), spawn.getYaw(), spawn.getPitch());
    }

    @Override
    public boolean isMySQL() {
        return mainConfig.getBoolean("database.mysql", false);
    }

    @Override
    public String getHost() {
        return mainConfig.getString("database.host", "127.0.0.1");
    }

    @Override
    public int getPort() {
        return mainConfig.getInt("database.port", 3306);
    }

    @Override
    public String getDatabase() {
        return mainConfig.getString("database.database", "catseedlogin");
    }

    @Override
    public String getUser() {
        return mainConfig.getString("database.user", "root");
    }

    @Override
    public String getPassword() {
        return mainConfig.getString("database.password", "password");
    }

    @Override
    public boolean isEnable() {
        return mainConfig.getBoolean("proxy.enabled", false);
    }

    @Override
    public String getAuthKey() {
        return mainConfig.getString("proxy.auth-key", "");
    }

    @Override
    public String getLoginServerName() {
        return mainConfig.getString("proxy.login-server-name", "lobby");
    }

    @Override
    public boolean isSSLAuthVerify() {
        return mainConfig.getBoolean("email.ssl-auth", true);
    }

    @Override
    public String getEmailAccount() {
        return mainConfig.getString("email.account", "");
    }

    @Override
    public String getEmailPassword() {
        return mainConfig.getString("email.password", "");
    }

    @Override
    public String getEmailSmtpHost() {
        return mainConfig.getString("email.smtp-host", "smtp.example.com");
    }

    @Override
    public String getEmailSmtpPort() {
        return mainConfig.getString("email.smtp-port", "465");
    }

    @Override
    public String getFromPersonal() {
        return mainConfig.getString("email.from-name", "Server");
    }

    public void set(String path, Object value) {
        mainConfig.set(path, value);
        saveConfig("config.yml");
    }
}
