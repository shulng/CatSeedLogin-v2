package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.velocity.config.VelocityConfigManager;
import cc.baka9.catseedlogin.velocity.config.VelocityPlatformAdapter;
import cc.baka9.catseedlogin.common.api.PlatformAdapter;
import cc.baka9.catseedlogin.common.i18n.I18n;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

@Plugin(
    id = "catseedlogin-velocity",
    name = "CatSeedLogin-Velocity",
    version = "2.0.0",
    description = "Velocity adapter for CatSeedLogin",
    authors = {"CatSeed"}
)
public class PluginMain {
    
    private static PluginMain instance;
    private final ProxyServer proxyServer;
    private final Logger logger;
    private VelocityConfigManager configManager;
    private VelocityPlatformAdapter platformAdapter;
    private VelocityCommunication communication;
    
    @Inject
    public PluginMain(ProxyServer proxyServer, Logger logger) {
        this.proxyServer = proxyServer;
        this.logger = logger;
        instance = this;
    }
    
    public static PluginMain getInstance() {
        return instance;
    }
    
    public ProxyServer getProxyServer() {
        return proxyServer;
    }
    
    public Logger getLogger() {
        return logger;
    }
    
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        configManager = new VelocityConfigManager(this);
        platformAdapter = new VelocityPlatformAdapter(this, configManager.getI18n());
        communication = new VelocityCommunication();
        configManager.reload();
        
        proxyServer.getEventManager().register(this, new Listeners());
        
        proxyServer.getCommandManager().register(
            proxyServer.getCommandManager().metaBuilder("CatSeedLoginVelocity")
                .aliases("cslv")
                .build(),
            new Commands()
        );
        
        logger.info("CatSeedLogin-Velocity has been enabled!");
    }
    
    public static ScheduledTask runAsync(Runnable runnable) {
        return instance.proxyServer.getScheduler()
            .buildTask(instance, runnable)
            .schedule();
    }
    
    public static ScheduledTask runAsyncDelayed(Runnable runnable, long delay, TimeUnit unit) {
        return instance.proxyServer.getScheduler()
            .buildTask(instance, runnable)
            .delay(delay, unit)
            .schedule();
    }

    public VelocityConfigManager getConfigManager() {
        return configManager;
    }

    public VelocityPlatformAdapter getPlatformAdapter() {
        return platformAdapter;
    }

    public I18n getI18n() {
        return configManager.getI18n();
    }

    public VelocityCommunication getCommunication() {
        return communication;
    }
}
