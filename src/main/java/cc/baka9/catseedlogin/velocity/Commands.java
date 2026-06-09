package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.common.i18n.MessageKey;
import cc.baka9.catseedlogin.velocity.config.VelocityConfigManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Commands implements SimpleCommand {

    private final VelocityConfigManager configManager;
    private final ProxyServer proxyServer;
    private final Logger logger;

    public Commands(VelocityConfigManager configManager, ProxyServer proxyServer, Logger logger) {
        this.configManager = configManager;
        this.proxyServer = proxyServer;
        this.logger = logger;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        
        try {
            if (!source.hasPermission("catseedlogin.admin")) {
                source.sendMessage(Component.text(MessageKey.NO_PERMISSION.get()));
                return;
            }
            
            if (args.length == 0) {
                sendHelp(source);
                return;
            }
            
            switch (args[0].toLowerCase()) {
                case "reload":
                    handleReload(source);
                    break;
                case "status":
                    handleStatus(source);
                    break;
                case "list":
                    handleList(source);
                    break;
                default:
                    sendHelp(source);
                    break;
            }
        } catch (Exception e) {
            source.sendMessage(Component.text("执行命令时发生错误", NamedTextColor.RED));
            logger.error("Error executing command", e);
        }
    }
    
    @Override
    public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
        String[] args = invocation.arguments();
        
        if (args.length <= 1) {
            return CompletableFuture.completedFuture(Arrays.asList("reload", "status", "list"));
        }
        
        return CompletableFuture.completedFuture(new ArrayList<>());
    }
    
    private void sendHelp(CommandSource source) {
        source.sendMessage(Component.text("=== CatSeedLogin-Velocity 命令帮助 ===", NamedTextColor.GOLD));
        source.sendMessage(Component.text("/cslv reload - 重载配置文件", NamedTextColor.YELLOW));
        source.sendMessage(Component.text("/cslv status - 查看插件状态", NamedTextColor.YELLOW));
        source.sendMessage(Component.text("/cslv list - 查看已登录玩家列表", NamedTextColor.YELLOW));
    }
    
    private void handleReload(CommandSource source) {
        try {
            configManager.reload();
            source.sendMessage(Component.text(MessageKey.CONFIG_RELOADED.get()));
        } catch (Exception e) {
            source.sendMessage(Component.text("重载配置文件时出错: " + e.getMessage(), NamedTextColor.RED));
            logger.error("Failed to reload config", e);
        }
    }
    
    private void handleStatus(CommandSource source) {
        try {
            source.sendMessage(Component.text("=== CatSeedLogin-Velocity 状态 ===", NamedTextColor.GOLD));
            
            String host = configManager.getProxyHost();
            int port = configManager.getProxyPort();
            String loginServerName = configManager.getLoginServerName();
            
            source.sendMessage(Component.text("监听地址: " + host + ":" + port, NamedTextColor.YELLOW));
            source.sendMessage(Component.text("登录服务器: " + loginServerName, NamedTextColor.YELLOW));
            
            boolean loginServerOnline = proxyServer
                .getServer(loginServerName)
                .isPresent();
            
            source.sendMessage(Component.text("登录服务器状态: " + (loginServerOnline ? "在线" : "离线"), 
                loginServerOnline ? NamedTextColor.GREEN : NamedTextColor.RED));
        } catch (Exception e) {
            source.sendMessage(Component.text("获取状态时发生错误", NamedTextColor.RED));
            logger.error("Error getting status", e);
        }
    }
    
    private void handleList(CommandSource source) {
        try {
            Listeners listeners = PluginMain.getInstance().getListeners();
            List<String> loggedInPlayers = listeners.getLoggedInPlayers();
            
            source.sendMessage(Component.text("=== 已登录玩家列表 ===", NamedTextColor.GOLD));
            source.sendMessage(Component.text("已登录玩家数量: " + loggedInPlayers.size(), NamedTextColor.YELLOW));
            
            if (loggedInPlayers.isEmpty()) {
                source.sendMessage(Component.text("暂无已登录玩家", NamedTextColor.GRAY));
            } else {
                loggedInPlayers.forEach(playerName -> 
                    source.sendMessage(Component.text("- " + playerName, NamedTextColor.WHITE))
                );
            }
        } catch (Exception e) {
            source.sendMessage(Component.text("获取玩家列表时发生错误", NamedTextColor.RED));
            logger.error("Error getting player list", e);
        }
    }
}