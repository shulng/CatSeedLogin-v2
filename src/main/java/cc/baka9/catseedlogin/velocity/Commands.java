package cc.baka9.catseedlogin.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Velocity 命令类
 * 保持与Bungee版本一致的管理功能
 */
public class Commands implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        
        if (!source.hasPermission("catseedlogin.admin")) {
            source.sendMessage(Component.text("你没有权限执行此命令!", NamedTextColor.RED));
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
            return CompletableFuture.completedFuture(List.of("reload", "status", "list"));
        }
        
        return CompletableFuture.completedFuture(List.of());
    }
    
    private void sendHelp(CommandSource source) {
        source.sendMessage(Component.text("=== CatSeedLogin-Velocity 命令帮助 ===", NamedTextColor.GOLD));
        source.sendMessage(Component.text("/cslv reload - 重载配置文件", NamedTextColor.YELLOW));
        source.sendMessage(Component.text("/cslv status - 查看插件状态", NamedTextColor.YELLOW));
        source.sendMessage(Component.text("/cslv list - 查看已登录玩家列表", NamedTextColor.YELLOW));
    }
    
    private void handleReload(CommandSource source) {
        try {
            Config.load();
            source.sendMessage(Component.text("配置文件已重载!", NamedTextColor.GREEN));
            
            if (!Config.Enable) {
                source.sendMessage(Component.text("警告: 插件当前已禁用!", NamedTextColor.RED));
            }
            
        } catch (Exception e) {
            source.sendMessage(Component.text("重载配置文件时出错: " + e.getMessage(), NamedTextColor.RED));
            PluginMain.getInstance().getLogger().error("Failed to reload config", e);
        }
    }
    
    private void handleStatus(CommandSource source) {
        source.sendMessage(Component.text("=== CatSeedLogin-Velocity 状态 ===", NamedTextColor.GOLD));
        source.sendMessage(Component.text("启用状态: " + (Config.Enable ? "启用" : "禁用"), 
            Config.Enable ? NamedTextColor.GREEN : NamedTextColor.RED));
        source.sendMessage(Component.text("监听地址: " + Config.Host + ":" + Config.Port, NamedTextColor.YELLOW));
        source.sendMessage(Component.text("登录服务器: " + Config.LoginServerName, NamedTextColor.YELLOW));
        
        // 检查登录服务器是否在线
        boolean loginServerOnline = PluginMain.getInstance().getProxyServer()
            .getServer(Config.LoginServerName)
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