package cc.baka9.catseedlogin.bukkit;

import cc.baka9.catseedlogin.bukkit.config.BukkitConfigManager;
import cc.baka9.catseedlogin.bukkit.database.Cache;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;
import cc.baka9.catseedlogin.util.CommunicationAuth;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {
    private static ServerSocket serverSocket;

    public static void socketServerStopAsync() {
        CatSeedLogin.instance.runTaskAsync(Communication::socketServerStop);
    }

    public static void socketServerStop() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void socketServerStartAsync() {
        CatSeedLogin.instance.runTaskAsync(Communication::socketServerStart);
    }

    public static void socketServerStart() {
        BukkitConfigManager config = CatSeedLogin.instance.getConfigManager();
        try {
            InetAddress inetAddress = InetAddress.getByName(config.getProxyHost());
            serverSocket = new ServerSocket(config.getProxyPort(), 50, inetAddress);
            while (!serverSocket.isClosed()) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                    handleRequest(socket);
                } catch (IOException e) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            CatSeedLogin.instance.getLogger().warning("无法解析域名或IP地址");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String requestType = bufferedReader.readLine();
        String playerName = bufferedReader.readLine();
        switch (requestType) {
            case "Connect":
                handleConnectRequest(socket, playerName);
                break;
            case "KeepLoggedIn":
                String time = bufferedReader.readLine();
                String sign = bufferedReader.readLine();
                handleKeepLoggedInRequest(playerName, time, sign);
                socket.close();
                break;
            default:
                break;
        }
    }

    private static void handleKeepLoggedInRequest(String playerName, String time, String sign) {
        BukkitConfigManager config = CatSeedLogin.instance.getConfigManager();
        if (sign.equals(CommunicationAuth.encryption(playerName, time, config.getAuthKey()))) {
            CatScheduler.runTask(() -> {
                LoginPlayer lp = Cache.getIgnoreCase(playerName);
                if (lp != null) {
                    LoginPlayerHelper.add(lp);
                    Player player = Bukkit.getPlayerExact(playerName);
                    if (player != null) {
                        player.updateInventory();
                    }
                }
            });
        }
    }

    private static void handleConnectRequest(Socket socket, String playerName) {
        CatScheduler.runTask(() -> {
            boolean result = LoginPlayerHelper.isLogin(playerName);
            
            CatSeedLogin.instance.runTaskAsync(() -> {
                try {
                    socket.getOutputStream().write(result ? 1 : 0);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
