package cc.baka9.catseedlogin.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.connection.PreLoginEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Velocity 监听事件类
 * 保持与Bungee版本完全一致的功能和行为
 */
public class Listeners {

    private static final List<String> loggedInPlayerList = new CopyOnWriteArrayList<>();

    /**
     * 登录之前不能输入velocity指令
     */
    @Subscribe
    public void onChat(com.velocitypowered.api.event.player.PlayerChatEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getUsername();
        String message = event.getMessage();
        
        // 检查是否为命令且玩家未登录
        if (message.startsWith("/") && !loggedInPlayerList.contains(playerName)) {
            event.setResult(com.velocitypowered.api.event.player.PlayerChatEvent.ChatResult.denied());
            handleLogin(player, message);
        }
    }

    /**
     * 处理命令执行事件
     */
    @Subscribe
    public void onCommandExecute(com.velocitypowered.api.event.command.CommandExecuteEvent event) {
        if (!(event.getCommandSource() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getCommandSource();
        String playerName = player.getUsername();
        String command = event.getCommand();
        
        // 检查是否为命令且玩家未登录（排除登录相关命令）
        if (!loggedInPlayerList.contains(playerName) && 
            !command.toLowerCase().startsWith("login") && 
            !command.toLowerCase().startsWith("register") &&
            !command.toLowerCase().startsWith("l") &&
            !command.toLowerCase().startsWith("reg") &&
            !command.toLowerCase().startsWith("cslv")) {
            
            event.setResult(com.velocitypowered.api.event.command.CommandExecuteEvent.CommandResult.denied());
            handleLogin(player, "/" + command);
        }
    }

    /**
     * 玩家切换子服时，检查velocity端该玩家的登录状态
     * 如果没有登录，在登录服获取登录状态后更新velocity端该玩家的登录状态
     * 如果登录服依然未登录，强制切换目标服务器为登录服
     */
    @Subscribe
    public void onServerPreConnect(ServerPreConnectEvent event) {
        Player player = event.getPlayer();
        RegisteredServer target = event.getResult().getServer().orElse(null);
        
        if (target == null) {
            return;
        }
        
        String targetName = target.getServerInfo().getName();
        String playerName = player.getUsername();

        if (!loggedInPlayerList.contains(playerName)) {
            // 如果目标服务器已经是登录服，不需要强制切换
            if (!targetName.equals(Config.LoginServerName)) {
                // 异步检查登录状态
                PluginMain.runAsync(() -> {
                    try {
                        // 直接检查玩家在登录服的状态
                        if (Communication.sendConnectRequest(playerName) == 1) {
                            // 如果已经登录，添加到已登录列表
                            loggedInPlayerList.add(playerName);
                        } else {
                            // 如果未登录，强制切换到登录服
                            PluginMain.getInstance().getProxyServer()
                                .getServer(Config.LoginServerName)
                                .ifPresent(loginServer -> {
                                    event.setResult(ServerPreConnectEvent.ServerResult.allowed(loginServer));
                                });
                        }
                    } catch (Exception e) {
                        PluginMain.getInstance().getLogger()
                            .error("Error checking login status for player: " + playerName, e);
                    }
                });
            } else {
                // 目标服务器是登录服，直接检查登录状态
                handleLogin(player, null);
            }
        }
    }

    /**
     * 玩家切换到登录服务之后，如果velocity端已登录模式
     * 使用登录状态更新子服务器登录状态，避免玩家需要重新登录
     */
    @Subscribe
    public void onServerConnected(ServerConnectedEvent event) {
        Player player = event.getPlayer();
        String serverName = event.getServer().getServerInfo().getName();
        
        if (serverName.equals(Config.LoginServerName) && loggedInPlayerList.contains(player.getUsername())) {
            PluginMain.runAsyncDelayed(() -> {
                Communication.sendKeepLoggedInRequest(player.getUsername());
            }, 1, TimeUnit.SECONDS);
        }
    }

    /**
     * 玩家离线时，从velocity端删除玩家的登录状态
     */
    @Subscribe
    public void onPlayerDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        loggedInPlayerList.remove(player.getUsername());
    }

    /**
     * 玩家在登录前，检查velocity端和子服务器的登录状态
     * 如果任一已登录，阻止连接
     */
    @Subscribe
    public void onPreLogin(PreLoginEvent event) {
        
        
        String playerName = event.getUsername();
        
        try {
            if (loggedInPlayerList.contains(playerName) && (Communication.sendConnectRequest(playerName) == 1)) {
                event.setResult(PreLoginEvent.PreLoginComponentResult.denied(
                    Component.text("您已经登录，请勿重复登录。")
                ));
            }
        } catch (Exception e) {
            event.setResult(PreLoginEvent.PreLoginComponentResult.denied(
                Component.text("发生错误，请稍后再试。")
            ));
        }
    }

    /**
     * 处理玩家登录逻辑
     */
    private void handleLogin(Player player, String message) {
        String playerName = player.getUsername();
        
        PluginMain.runAsync(() -> {
            try {
                if (Communication.sendConnectRequest(playerName) == 1) {
                    loggedInPlayerList.add(playerName);
                    
                    // 如果提供了命令，执行它
                    if (message != null && !message.isEmpty() && message.startsWith("/")) {
                        PluginMain.getInstance().getProxyServer()
                            .getCommandManager()
                            .executeAsync(player, message.substring(1));
                    }
                }
            } catch (Exception e) {
                PluginMain.getInstance().getLogger()
                    .error("Error handling login for player: " + playerName, e);
            }
        });
    }
    
    /**
     * 获取已登录玩家列表（用于调试和管理）
     */
    public static List<String> getLoggedInPlayers() {
        return loggedInPlayerList;
    }
}