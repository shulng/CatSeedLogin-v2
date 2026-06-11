package cc.baka9.catseedlogin.bukkit;

import cc.baka9.catseedlogin.common.config.ConfigConstants;
import cc.baka9.catseedlogin.common.config.ConfigHelper;
import cc.baka9.catseedlogin.common.util.ValidationUtil;
import cc.baka9.catseedlogin.bukkit.config.BukkitConfigManager;
import cc.baka9.catseedlogin.common.i18n.MessageKey;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {
    private static CatSeedLogin plugin;

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
            Host = cm.getDatabaseHost();
            Port = String.valueOf(cm.getDatabasePort());
            Database = cm.getDatabaseName();
            User = cm.getDatabaseUser();
            Password = cm.getDatabasePassword();
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
            Host = cm.getProxyHost();
            Port = String.valueOf(cm.getProxyPort());
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
        public static boolean EmptyBackpack;
        public static int IPTimeout;
        public static int MaxLengthID;
        public static int MinLengthID;
        public static boolean BeforeLoginNoDamage;
        public static long ReenterInterval;
        public static boolean AfterLoginBack;
        public static boolean CanTpSpawnLocation;
        public static List<Pattern> CommandWhiteList = new java.util.ArrayList<>();
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
            EmptyBackpack = cm.isEmptyBackpack();
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
            cm.set(ConfigConstants.Path.SETTINGS_IP_REGISTER_LIMIT, IpRegisterCountLimit);
            cm.set(ConfigConstants.Path.SETTINGS_IP_COUNT_LIMIT, IpCountLimit);
            cm.set(ConfigConstants.Path.SETTINGS_LIMIT_CHINESE_ID, LimitChineseID);
            cm.set(ConfigConstants.Path.BEDROCK_LOGIN_BYPASS, BedrockLoginBypass);
            cm.set(ConfigConstants.Path.SAME_IP_ENABLED, LoginwiththesameIP);
            cm.set(ConfigConstants.Path.EMPTY_BACKPACK, EmptyBackpack);
            cm.set(ConfigConstants.Path.SAME_IP_TIMEOUT, IPTimeout);
            cm.set(ConfigConstants.Path.SETTINGS_MIN_LENGTH_ID, MinLengthID);
            cm.set(ConfigConstants.Path.SETTINGS_MAX_LENGTH_ID, MaxLengthID);
            cm.set(ConfigConstants.Path.SETTINGS_BEFORE_LOGIN_NO_DAMAGE, BeforeLoginNoDamage);
            cm.set(ConfigConstants.Path.SETTINGS_REENTER_INTERVAL, ReenterInterval);
            cm.set(ConfigConstants.Path.SETTINGS_AFTER_LOGIN_BACK, AfterLoginBack);
            cm.set(ConfigConstants.Path.SETTINGS_CAN_TP_SPAWN_LOCATION, CanTpSpawnLocation);
            cm.set(ConfigConstants.Path.SETTINGS_AUTO_KICK, AutoKick);
            cm.set(ConfigConstants.Path.SETTINGS_DEATH_STATE_QUIT_RECORD, DeathStateQuitRecordLocation);
            cm.set(ConfigConstants.Path.BEDROCK_FLOODGATE_PREFIX, FloodgatePrefixProtect);
            cm.set(ConfigConstants.Path.SETTINGS_NAME_PATTERN, NamePattern);
            
            if (CommandWhiteList != null && !CommandWhiteList.isEmpty()) {
                cm.getMainConfig().set(ConfigConstants.Path.SETTINGS_COMMAND_WHITELIST, 
                    CommandWhiteList.stream().map(Pattern::toString).collect(Collectors.toList()));
            }
            
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
            Enable = cm.isEmailEnable();
            EmailAccount = cm.getEmailAccount();
            EmailPassword = cm.getEmailPassword();
            EmailSmtpHost = cm.getEmailSmtpHost();
            EmailSmtpPort = cm.getEmailSmtpPort();
            SSLAuthVerify = cm.isSSLAuthVerify();
            FromPersonal = cm.getFromPersonal();
        }
    }

    public static void load(){
        plugin = CatSeedLogin.instance;
        BukkitConfigManager cm = plugin.getConfigManager();
        cm.createDefaultConfig("config.yml");
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
        try {
            String locStr = CatSeedLogin.sql.getLocation(player.getName());
            if (locStr != null && !locStr.isEmpty()) {
                return Optional.of(str2Location(locStr));
            }
        } catch (Exception e) {
            plugin.getLogger().warning("获取玩家离线位置失败: " + player.getName());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void setOfflineLocation(Player player) {
        String locStr = loc2String(player.getLocation());
        plugin.runTaskAsync(() -> {
            try {
                CatSeedLogin.sql.updateLocation(player.getName(), locStr);
            } catch (Exception e) {
                plugin.getLogger().warning("保存玩家离线位置失败: " + player.getName());
                e.printStackTrace();
            }
        });
    }

    private static Location str2Location(String str){
        Location loc;
        try {
            String[] locStrs = str.split(":");
            World world = Bukkit.getWorld(locStrs[0]);
            if (world == null) {
                world = getDefaultWorld();
            }
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

        try (java.io.InputStream is = new java.io.BufferedInputStream(java.nio.file.Files.newInputStream(serverPropertiesFile.toPath()))) {
            java.util.Properties properties = new java.util.Properties();
            properties.load(is);
            String worldName = properties.getProperty("level-name");
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                return world;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Bukkit.getWorlds().get(0);
    }
}
