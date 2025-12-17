package cc.baka9.catseedlogin.bungee;

import cc.baka9.catseedlogin.common.CommonConfig;
import cc.baka9.catseedlogin.common.CommonCommunication;
import cc.baka9.catseedlogin.common.CommonPlugin;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

/**
 * Bungee Cord 主类
 */
public class PluginMain extends Plugin implements CommonPlugin {
    public static PluginMain instance;
    private BungeeConfig config;
    private BungeeCommunication communication;

    @Override
    public void onEnable() {
        instance = this;
        config = new BungeeConfig();
        communication = new BungeeCommunication();
        config.load();
        getProxy().getPluginManager().registerListener(this, new Listeners());
        getProxy().getPluginManager().registerCommand(this, new Commands("CatSeedLoginBungee", "catseedlogin.admin", "cslb"));

    }

    public static ScheduledTask runAsync(Runnable runnable) {
        return instance.getProxy().getScheduler().runAsync(instance, runnable);
    }

    @Override
    public CommonConfig getConfig() {
        return config;
    }

    @Override
    public CommonCommunication getCommunication() {
        return communication;
    }

    @Override
    public void logInfo(String message) {
        getLogger().info(message);
    }

    @Override
    public void logWarn(String message) {
        getLogger().warning(message);
    }

    @Override
    public void logError(String message, Throwable throwable) {
        getLogger().severe(message);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}
