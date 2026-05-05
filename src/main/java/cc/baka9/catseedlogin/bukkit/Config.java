package cc.baka9.catseedlogin.bukkit;

import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cc.baka9.catseedlogin.bukkit.config.BukkitConfigManager;
import cc.baka9.catseedlogin.common.i18n.MessageKey;

public class Config {
    private static final CatSeedLogin plugin = CatSeedLogin.instance;
    private static final Map<String, String> offlineLocations = new ConcurrentHashMap<>();

    public static class MySQL {
        public static boolean Enable;
        public static String Host;
        public static String Port;
        public static String Database;
        public static String User;
        public static String Password;

        public static void load(){
            BukkitConfigManager cm = plugin.getConfigManager();
            Enable = cm.isMySQL();
            Host = cm.getHost();
            Port = String.valueOf(cm.getPort());
            Database = cm.getDatabase();
            User = cm.getUser();
            Password = cm.getPassword();
        }
    }

    public static class BungeeCord {
        public static boolean Enable;
        public static String Host;
        public static String Port;
        public static String AuthKey;

        public static void load(){
            BukkitConfigManager cm = plugin.getConfigManager();
            Enable = cm.isEnable();
            Host = cm.getHost();
            Port = String.valueOf(cm.getPort());
            AuthKey = cm.getAuthKey();
        }
    }

    public static class Settings {
        public static int IpRegisterCountLimit;
        public static int IpCountLimit;
        public static Location SpawnLocation;
        public static boolean LimitChineseID;
        public static boolean BedrockLoginBypass;
        public static boolean LoginwiththesameIP;
        public static boolean Emptybackpack;
        public static int IPTimeout;
        public static int MaxLengthID;
        public static int MinLengthID;
        public static boolean BeforeLoginNoDamage;
        public static long ReenterInterval;
        public static boolean AfterLoginBack;
        public static boolean CanTpSpawnLocation;
        public static List<Pattern> CommandWhiteList = new ArrayList<>();
        public static int AutoKick;
        public static String NamePattern;
        public static boolean DeathStateQuitRecordLocation;
        public static boolean FloodgatePrefixProtect;

        public static void load(){
            BukkitConfigManager cm = plugin.getConfigManager();
            IpRegisterCountLimit = cm.getIpRegisterCountLimit();
            IpCountLimit = cm.getIpCountLimit();
            LimitChineseID = cm.isLimitChineseID();
            MinLengthID = cm.getMinLengthID();
            BedrockLoginBypass = cm.isBedrockLoginBypass();
            LoginwiththesameIP = cm.isLoginWithSameIP();
            Emptybackpack = cm.isEmptyBackpack();
            MaxLengthID = cm.getMaxLengthID();
            BeforeLoginNoDamage = cm.isBeforeLoginNoDamage();
            ReenterInterval = cm.getReenterInterval();
            AfterLoginBack = cm.isAfterLoginBack();
            CanTpSpawnLocation = cm.isCanTpSpawnLocation();
            NamePattern = cm.getNamePattern();
            CommandWhiteList = cm.getCommandWhiteList();
            AutoKick = cm.getAutoKick();
            IPTimeout = cm.getIPTimeout();
            SpawnLocation = cm.getBukkitSpawnLocation();
            DeathStateQuitRecordLocation = cm.isDeathStateQuitRecordLocation();
            FloodgatePrefixProtect = cm.isFloodgatePrefixProtect();
        }

        public static void save(){
            BukkitConfigManager cm = plugin.getConfigManager();
            cm.set("settings.ip-register-count-limit", IpRegisterCountLimit);
            cm.set("settings.ip-count-limit", IpCountLimit);
            cm.set("settings.limit-chinese-id", LimitChineseID);
            cm.set("bedrock.login-bypass", BedrockLoginBypass);
            cm.set("same-ip-login.enabled", LoginwiththesameIP);
            cm.set("empty-backpack", Emptybackpack);
            cm.set("same-ip-login.timeout", IPTimeout);
            cm.set("settings.min-length-id", MinLengthID);
            cm.set("settings.max-length-id", MaxLengthID);
            cm.set("settings.before-login-no-damage", BeforeLoginNoDamage);
            cm.set("settings.reenter-interval", ReenterInterval);
            cm.set("settings.after-login-back", AfterLoginBack);
            cm.set("settings.can-tp-spawn-location", CanTpSpawnLocation);
            cm.set("settings.auto-kick", AutoKick);
            cm.set("settings.death-state-quit-record-location", DeathStateQuitRecordLocation);
            cm.set("bedrock.floodgate-prefix-protect", FloodgatePrefixProtect);
            cm.set("settings.name-pattern", NamePattern);
            
            if (SpawnLocation != null) {
                cm.setSpawnLocation(SpawnLocation);
            }
        }
    }

    public static class Language {
        public static String LOGIN_REQUEST;
        public static String REGISTER_REQUEST;
        public static String LOGIN_NOREGISTER;
        public static String LOGIN_REPEAT;
        public static String LOGIN_SUCCESS;
        public static String LOGIN_FAIL;
        public static String LOGIN_FAIL_IF_FORGET;
        public static String REGISTER_SUCCESS;
        public static String REGISTER_BEFORE_LOGIN_ALREADY;
        public static String REGISTER_AFTER_LOGIN_ALREADY;
        public static String REGISTER_PASSWORD_CONFIRM_FAIL;
        public static String COMMON_PASSWORD_SO_SIMPLE;
        public static String RESETPASSWORD_NOREGISTER;
        public static String RESETPASSWORD_EMAIL_DISABLE;
        public static String RESETPASSWORD_EMAIL_NO_SET;
        public static String RESETPASSWORD_EMAIL_REPEAT_SEND_MESSAGE;
        public static String RESETPASSWORD_EMAIL_SENDING_MESSAGE;
        public static String RESETPASSWORD_EMAIL_SENT_MESSAGE;
        public static String RESETPASSWORD_EMAIL_WARN;
        public static String RESETPASSWORD_SUCCESS;
        public static String RESETPASSWORD_EMAILCODE_INCORRECT;
        public static String RESETPASSWORD_FAIL;
        public static String CHANGEPASSWORD_NOREGISTER;
        public static String CHANGEPASSWORD_NOLOGIN;
        public static String CHANGEPASSWORD_OLDPASSWORD_INCORRECT;
        public static String CHANGEPASSWORD_PASSWORD_CONFIRM_FAIL;
        public static String CHANGEPASSWORD_SUCCESS;
        public static String AUTO_KICK;
        public static String REGISTER_MORE;
        public static String BEDROCK_LOGIN_BYPASS;
        public static String LOGIN_WITH_THE_SAME_IP;

        public static void load(){
            LOGIN_REQUEST = MessageKey.LOGIN_REQUEST.get();
            REGISTER_REQUEST = MessageKey.REGISTER_REQUEST.get();
            LOGIN_NOREGISTER = MessageKey.LOGIN_NOREGISTER.get();
            LOGIN_REPEAT = MessageKey.LOGIN_REPEAT.get();
            LOGIN_SUCCESS = MessageKey.LOGIN_SUCCESS.get();
            LOGIN_FAIL = MessageKey.LOGIN_FAIL.get();
            LOGIN_FAIL_IF_FORGET = MessageKey.LOGIN_FAIL_IF_FORGET.get();
            REGISTER_SUCCESS = MessageKey.REGISTER_SUCCESS.get();
            REGISTER_BEFORE_LOGIN_ALREADY = MessageKey.REGISTER_BEFORE_LOGIN_ALREADY.get();
            REGISTER_AFTER_LOGIN_ALREADY = MessageKey.REGISTER_AFTER_LOGIN_ALREADY.get();
            REGISTER_PASSWORD_CONFIRM_FAIL = MessageKey.REGISTER_PASSWORD_CONFIRM_FAIL.get();
            COMMON_PASSWORD_SO_SIMPLE = MessageKey.COMMON_PASSWORD_SO_SIMPLE.get();
            RESETPASSWORD_NOREGISTER = MessageKey.RESETPASSWORD_NOREGISTER.get();
            RESETPASSWORD_EMAIL_DISABLE = MessageKey.RESETPASSWORD_EMAIL_DISABLE.get();
            RESETPASSWORD_EMAIL_NO_SET = MessageKey.RESETPASSWORD_EMAIL_NO_SET.get();
            RESETPASSWORD_EMAIL_REPEAT_SEND_MESSAGE = MessageKey.RESETPASSWORD_EMAIL_REPEAT_SEND_MESSAGE.get();
            RESETPASSWORD_EMAIL_SENDING_MESSAGE = MessageKey.RESETPASSWORD_EMAIL_SENDING_MESSAGE.get();
            RESETPASSWORD_EMAIL_SENT_MESSAGE = MessageKey.RESETPASSWORD_EMAIL_SENT_MESSAGE.get();
            RESETPASSWORD_EMAIL_WARN = MessageKey.RESETPASSWORD_EMAIL_WARN.get();
            RESETPASSWORD_SUCCESS = MessageKey.RESETPASSWORD_SUCCESS.get();
            RESETPASSWORD_EMAILCODE_INCORRECT = MessageKey.RESETPASSWORD_EMAILCODE_INCORRECT.get();
            RESETPASSWORD_FAIL = MessageKey.RESETPASSWORD_FAIL.get();
            CHANGEPASSWORD_NOREGISTER = MessageKey.CHANGEPASSWORD_NOREGISTER.get();
            CHANGEPASSWORD_NOLOGIN = MessageKey.CHANGEPASSWORD_NOLOGIN.get();
            CHANGEPASSWORD_OLDPASSWORD_INCORRECT = MessageKey.CHANGEPASSWORD_OLDPASSWORD_INCORRECT.get();
            CHANGEPASSWORD_PASSWORD_CONFIRM_FAIL = MessageKey.CHANGEPASSWORD_PASSWORD_CONFIRM_FAIL.get();
            CHANGEPASSWORD_SUCCESS = MessageKey.CHANGEPASSWORD_SUCCESS.get();
            AUTO_KICK = MessageKey.AUTO_KICK.get();
            REGISTER_MORE = MessageKey.REGISTER_MORE.get();
            BEDROCK_LOGIN_BYPASS = MessageKey.BEDROCK_LOGIN_BYPASS.get();
            LOGIN_WITH_THE_SAME_IP = MessageKey.LOGIN_WITH_THE_SAME_IP.get();
        }
    }

    public static class EmailVerify {
        public static boolean Enable;
        public static String EmailAccount;
        public static String EmailPassword;
        public static String EmailSmtpHost;
        public static String EmailSmtpPort;
        public static boolean SSLAuthVerify;
        public static String FromPersonal;

        public static void load(){
            BukkitConfigManager cm = plugin.getConfigManager();
            Enable = cm.isEnable();
            EmailAccount = cm.getEmailAccount();
            EmailPassword = cm.getEmailPassword();
            EmailSmtpHost = cm.getEmailSmtpHost();
            EmailSmtpPort = cm.getEmailSmtpPort();
            SSLAuthVerify = cm.isSSLAuthVerify();
            FromPersonal = cm.getFromPersonal();
        }
    }

    public static FileConfiguration getConfig(String yamlFileName){
        File file = new File(plugin.getDataFolder(), yamlFileName);
        if (!file.exists()) {
            plugin.saveResource(yamlFileName, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getResourceConfig(String yamlFileName){
        return YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(yamlFileName), StandardCharsets.UTF_8));
    }

    public static void load(){
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        if (config.contains("offlineLocations")) {
            config.getConfigurationSection("offlineLocations").getKeys(false).forEach(key ->
                    offlineLocations.put(key, config.getString("offlineLocations." + key))
            );
        }
        MySQL.load();
        Settings.load();
        EmailVerify.load();
        Language.load();
        BungeeCord.load();
    }

    public static void save(){
        Settings.save();
    }

    public static void reload(){
        plugin.getConfigManager().reload();
        load();
    }

    public static Optional<Location> getOfflineLocation(Player player) {
        return Optional.ofNullable(plugin.getConfig().getString("offlineLocations." + player.getName())).map(Config::str2Location);
    }

    public static void setOfflineLocation(Player player) {
        plugin.getConfig().set("offlineLocations." + player.getName(), loc2String(player.getLocation()));
        plugin.saveConfig();
    }

    private static Location str2Location(String str){
        Location loc;
        try {
            String[] locStrs = str.split(":");
            World world = Bukkit.getWorld(locStrs[0]);
            double x = Double.parseDouble(locStrs[1]);
            double y = Double.parseDouble(locStrs[2]);
            double z = Double.parseDouble(locStrs[3]);
            float yaw = Float.parseFloat(locStrs[4]);
            float pitch = Float.parseFloat(locStrs[5]);
            loc = new Location(world, x, y, z, yaw, pitch);
        } catch (Exception ignored) {
            loc = getDefaultWorld().getSpawnLocation();
        }
        return loc;
    }

    private static String loc2String(Location loc) {
        try {
            return String.format("%s:%.2f:%.2f:%.2f:%.2f:%.2f",
                                 loc.getWorld().getName(),
                                 loc.getX(),
                                 loc.getY(),
                                 loc.getZ(),
                                 loc.getYaw(),
                                 loc.getPitch());
        } catch (Exception e) {
            e.printStackTrace();
            Location defaultLoc = getDefaultWorld().getSpawnLocation();
            return String.format("%s:%.2f:%.2f:%.2f:%.2f:%.2f",
                                 defaultLoc.getWorld().getName(),
                                 defaultLoc.getX(),
                                 defaultLoc.getY(),
                                 defaultLoc.getZ(),
                                 defaultLoc.getYaw(),
                                 defaultLoc.getPitch());
        }
    }

    private static World getDefaultWorld() {
        File serverPropertiesFile = new File("server.properties");
        if (!serverPropertiesFile.exists()) {
            return Bukkit.getWorlds().get(0);
        }

        try (InputStream is = new BufferedInputStream(Files.newInputStream(serverPropertiesFile.toPath()))) {
            Properties properties = new Properties();
            properties.load(is);
            String worldName = properties.getProperty("level-name");
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                return world;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Bukkit.getWorlds().get(0);
    }
}
