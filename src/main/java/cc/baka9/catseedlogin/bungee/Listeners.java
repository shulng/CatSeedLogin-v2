package cc.baka9.catseedlogin.bungee;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cc.baka9.catseedlogin.bungee.config.BungeeConfigManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Listeners implements Listener {

    private final ProxyServer proxyServer = ProxyServer.getInstance();
    private final List<String> loggedInPlayerList = new CopyOnWriteArrayList<>();
    private final BungeeConfigManager configManager;
    private final BungeeCommunication communication;

    public Listeners(BungeeConfigManager configManager, BungeeCommunication communication) {
        this.configManager = configManager;
        this.communication = communication;
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.isProxyCommand() && event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            String playerName = player.getName();

            if (!loggedInPlayerList.contains(playerName)) {
                event.setCancelled(true);
                handleLogin(player, event.getMessage());
            }
        }
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent event) {
        ServerInfo target = event.getTarget();
        String loginServerName = configManager.getLoginServerName();
        if (!event.isCancelled() && !target.getName().equals(loginServerName)) {
            ProxiedPlayer player = event.getPlayer();
            String playerName = player.getName();

            if (!loggedInPlayerList.contains(playerName)) {
                PluginMain.runAsync(() -> {
                    try {
                        if (communication.sendConnectRequest(playerName) == 1) {
                            loggedInPlayerList.add(playerName);
                        } else {
                            event.setTarget(proxyServer.getServerInfo(loginServerName));
                        }
                    } catch (Exception e) {
                        proxyServer.getLogger().severe("Error checking login status for player: " + playerName);
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @EventHandler
    public void onServerConnected(ServerConnectedEvent event) {
        String loginServerName = configManager.getLoginServerName();
        if (event.getServer().getInfo().getName().equals(loginServerName)) {
            ProxiedPlayer player = event.getPlayer();
            if (loggedInPlayerList.contains(player.getName())) {
                PluginMain.runAsync(() -> communication.sendKeepLoggedInRequest(player.getName()));
            }
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        loggedInPlayerList.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        String playerName = event.getConnection().getName();
        try {
            if (loggedInPlayerList.contains(playerName) && (communication.sendConnectRequest(playerName) == 1)) {
                event.setCancelReason(new TextComponent("您已经登录，请勿重复登录。"));
                event.setCancelled(true);
            }
        } catch (Exception e) {
            event.setCancelReason(new TextComponent("发生错误，请稍后再试。"));
            event.setCancelled(true);
            throw e;
        }
    }

    private void handleLogin(ProxiedPlayer player, String message) {
        String playerName = player.getName();
        PluginMain.runAsync(() -> {
            if (communication.sendConnectRequest(playerName) == 1) {
                loggedInPlayerList.add(playerName);
                if (message != null && !message.isEmpty()) {
                    proxyServer.getPluginManager().dispatchCommand(player, message.substring(1));
                }
            }
        });
    }
}