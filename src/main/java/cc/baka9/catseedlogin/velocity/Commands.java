package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.common.i18n.MessageKey;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Commands implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        
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
            PluginMain.getInstance().getConfigManager().reload();
            source.sendMessage(Component.text(MessageKey.CONFIG_RELOADED.get()));
        } catch (Exception e) {
            source.sendMessage(Component.text("重载配置文件时出错: " + e.getMessage(), NamedTextColor.RED));
            PluginMain.getInstance().getLogger().error("Failed to reload config", e);
        }
    }
    
    private void handleStatus(CommandSource source) {
        source.sendMessage(Component.text("=== CatSeedLogin-Velocity 状态 ===", NamedTextColor.GOLD));
        
        String host = PluginMain.getInstance().getConfigManager().getHost();
        int port = PluginMain.getInstance().getConfigManager().getPort();
        String loginServerName = PluginMain.getInstance().getConfigManager().getLoginServerName();
        
        source.sendMessage(Component.text("监听地址: " + host + ":" + port, NamedTextColor.YELLOW));
        source.sendMessage(Component.text("登录服务器: " + loginServerName, NamedTextColor.YELLOW));
        
        boolean loginServerOnline = PluginMain.getInstance().getProxyServer()
            .getServer(loginServerName)
            .isPresent();
        
        source.sendMessage(Component.text("登录服务器状态: " + (loginServerOnline ? "在线" : "离线"), 
            loginServerOnline ? NamedTextColor.GREEN : NamedTextColor.RED));
    }
    
    private void handleList(CommandSource source) {
        List<String> loggedInPlayers = Listeners.getLoggedInPlayers();
        
        source.sendMessage(Component.text("=== 已登录玩家列表 ===", NamedTextColor.GOLD));
        source.sendMessage(Component.text("已登录玩家数量: " + loggedInPlayers.size(), NamedTextColor.YELLOW));
        
        if (loggedInPlayers.isEmpty()) {
            source.sendMessage(Component.text("暂无已登录玩家", NamedTextColor.GRAY));
        } else {
            loggedInPlayers.forEach(playerName -> 
                source.sendMessage(Component.text("- " + playerName, NamedTextColor.WHITE))
            );
        }
    }
}
