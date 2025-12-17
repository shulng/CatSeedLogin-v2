package cc.baka9.catseedlogin.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Velocity 主类
 * 适配原Bungee版本的CatSeedLogin功能
 */
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
        Config.load();
        
        
        
        // 注册监听器
        proxyServer.getEventManager().register(this, new Listeners());
        
        // 注册命令
        proxyServer.getCommandManager().register(
            proxyServer.getCommandManager().metaBuilder("CatSeedLoginVelocity")
                .aliases("cslv")
                .build(),
            new Commands()
        );
        
        logger.info("CatSeedLogin-Velocity has been enabled!");
    }
    
    /**
     * 异步执行任务
     */
    public static ScheduledTask runAsync(Runnable runnable) {
        return instance.proxyServer.getScheduler()
            .buildTask(instance, runnable)
            .schedule();
    }
    
    /**
     * 延迟异步执行任务
     */
    public static ScheduledTask runAsyncDelayed(Runnable runnable, long delay, TimeUnit unit) {
        return instance.proxyServer.getScheduler()
            .buildTask(instance, runnable)
            .delay(delay, unit)
            .schedule();
    }
}