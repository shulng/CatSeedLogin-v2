package cc.baka9.catseedlogin.bukkit;

import cc.baka9.catseedlogin.common.communication.BaseCommunication;
import cc.baka9.catseedlogin.bukkit.database.Cache;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends BaseCommunication {
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
        try {
            serverSocket = new ServerSocket(CatSeedLogin.instance.getConfigManager().getProxyPort(), 50);
            while (!serverSocket.isClosed()) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                    handleRequest(socket);
                } catch (IOException e) {
                    break;
                }
            }
        } catch (IOException e) {
            CatSeedLogin.instance.getLogger().warning("无法启动Socket服务器: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream outputStream = socket.getOutputStream()) {
            String requestType = bufferedReader.readLine();
            String playerName = bufferedReader.readLine();
            switch (requestType) {
                case "Connect":
                    handleConnectRequest(outputStream, playerName);
                    break;
                case "KeepLoggedIn":
                    String time = bufferedReader.readLine();
                    String sign = bufferedReader.readLine();
                    handleKeepLoggedInRequest(playerName, time, sign);
                    break;
                default:
                    break;
            }
        }
    }

    private static void handleKeepLoggedInRequest(String playerName, String time, String sign) {
        if (sign.equals(cc.baka9.catseedlogin.util.CommunicationAuth.encryption(playerName, time, CatSeedLogin.instance.getConfigManager().getAuthKey()))) {
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

    private static void handleConnectRequest(OutputStream outputStream, String playerName) {
        CatScheduler.runTask(() -> {
            boolean result = LoginPlayerHelper.isLogin(playerName);
            try {
                outputStream.write(result ? 1 : 0);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected String getProxyHost() {
        return CatSeedLogin.instance.getConfigManager().getProxyHost();
    }

    @Override
    protected int getProxyPort() {
        return CatSeedLogin.instance.getConfigManager().getProxyPort();
    }

    @Override
    protected String getAuthKey() {
        return CatSeedLogin.instance.getConfigManager().getAuthKey();
    }

    @Override
    protected void logError(String message, Exception e) {
        CatSeedLogin.instance.getLogger().severe(message);
        e.printStackTrace();
    }

    @Override
    protected void logWarning(String message) {
        CatSeedLogin.instance.getLogger().warning(message);
    }
}
