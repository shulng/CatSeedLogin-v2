package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.velocity.config.VelocityConfigManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PreLoginEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Listeners {

    private static final List<String> loggedInPlayerList = new CopyOnWriteArrayList<>();

    @Subscribe
    public void onChat(com.velocitypowered.api.event.player.PlayerChatEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getUsername();
        String message = event.getMessage();
        
        if (message.startsWith("/") && !loggedInPlayerList.contains(playerName)) {
            event.setResult(com.velocitypowered.api.event.player.PlayerChatEvent.ChatResult.denied());
            handleLogin(player, message);
        }
    }

    @Subscribe
    public void onCommandExecute(com.velocitypowered.api.event.command.CommandExecuteEvent event) {
        if (!(event.getCommandSource() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getCommandSource();
        String playerName = player.getUsername();
        String command = event.getCommand();
        
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

    @Subscribe
    public void onServerPreConnect(ServerPreConnectEvent event) {
        Player player = event.getPlayer();
        RegisteredServer target = event.getResult().getServer().orElse(null);
        
        if (target == null) {
            return;
        }
        
        String targetName = target.getServerInfo().getName();
        String playerName = player.getUsername();
        VelocityConfigManager config = PluginMain.getInstance().getConfigManager();
        String loginServerName = config.getLoginServerName();

        if (!loggedInPlayerList.contains(playerName)) {
            if (!targetName.equals(loginServerName)) {
                PluginMain.runAsync(() -> {
                    try {
                        if (PluginMain.getInstance().getCommunication().sendConnectRequest(playerName) == 1) {
                            loggedInPlayerList.add(playerName);
                        } else {
                            PluginMain.getInstance().getProxyServer()
                                .getServer(loginServerName)
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
                handleLogin(player, null);
            }
        }
    }

    @Subscribe
    public void onServerConnected(ServerConnectedEvent event) {
        Player player = event.getPlayer();
        String serverName = event.getServer().getServerInfo().getName();
        VelocityConfigManager config = PluginMain.getInstance().getConfigManager();
        String loginServerName = config.getLoginServerName();
        
        if (serverName.equals(loginServerName) && loggedInPlayerList.contains(player.getUsername())) {
            PluginMain.runAsyncDelayed(() -> {
                PluginMain.getInstance().getCommunication().sendKeepLoggedInRequest(player.getUsername());
            }, 1, TimeUnit.SECONDS);
        }
    }

    @Subscribe
    public void onPlayerDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        loggedInPlayerList.remove(player.getUsername());
    }

    @Subscribe
    public void onPreLogin(PreLoginEvent event) {
        String playerName = event.getUsername();
        
        try {
            if (loggedInPlayerList.contains(playerName) && (PluginMain.getInstance().getCommunication().sendConnectRequest(playerName) == 1)) {
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

    private void handleLogin(Player player, String message) {
        String playerName = player.getUsername();
        
        PluginMain.runAsync(() -> {
            try {
                if (PluginMain.getInstance().getCommunication().sendConnectRequest(playerName) == 1) {
                    loggedInPlayerList.add(playerName);
                    
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
    
    public static List<String> getLoggedInPlayers() {
        return loggedInPlayerList;
    }
}
