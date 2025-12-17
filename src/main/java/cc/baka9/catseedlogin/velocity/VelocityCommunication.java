package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.common.CommonCommunication;
import cc.baka9.catseedlogin.util.CommunicationAuth;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Velocity 与 Bukkit 的通讯交流
 * 保持与Bungee版本完全一致的功能和行为
 */
public class VelocityCommunication implements CommonCommunication {

    /**
     * 发送连接请求，检查玩家登录状态
     * @param playerName 玩家名称
     * @return 1表示已登录，0表示未登录
     */
    @Override
    public int sendConnectRequest(String playerName) {
        try (Socket socket = getSocket(); 
             BufferedWriter bufferedWriter = getSocketBufferedWriter(socket)) {
            
            // 请求类型
            bufferedWriter.write("Connect");
            bufferedWriter.newLine();
            
            // 玩家名
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            
            bufferedWriter.flush();
            return socket.getInputStream().read();
            
        } catch (IOException e) {
            PluginMain.getInstance().getLogger().error("Failed to send connect request for player: " + playerName, e);
        }
        return 0;
    }

    /**
     * 发送保持登录状态请求
     * @param playerName 玩家名称
     */
    @Override
    public void sendKeepLoggedInRequest(String playerName) {
        try (Socket socket = getSocket(); 
             BufferedWriter bufferedWriter = getSocketBufferedWriter(socket)) {
            
            // 请求类型
            bufferedWriter.write("KeepLoggedIn");
            bufferedWriter.newLine();
            
            // 玩家名
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            
            // 时间戳
            String time = String.valueOf(System.currentTimeMillis());
            bufferedWriter.write(time);
            bufferedWriter.newLine();
            
            // 根据玩家名、时间戳和authKey加密的结果
            String sign = CommunicationAuth.encryption(playerName, time, PluginMain.getInstance().getConfig().getAuthKey());
            bufferedWriter.write(sign);
            bufferedWriter.newLine();

            bufferedWriter.flush();
            
        } catch (IOException e) {
            PluginMain.getInstance().getLogger().error("Failed to send keep logged in request for player: " + playerName, e);
        }
    }

    /**
     * 获取Socket连接
     */
    private static Socket getSocket() throws IOException {
        try {
            return new Socket(PluginMain.getInstance().getConfig().getHost(), PluginMain.getInstance().getConfig().getPort());
        } catch (IOException e) {
            PluginMain instance = PluginMain.getInstance();
            ProxyServer proxyServer = instance.getProxyServer();
            Logger logger = instance.getLogger();
            
            logger.warn("§c请检查装载登录插件的子服是否在 velocity.toml 中开启了bungeecord功能，以及Host和Port是否与velocity端的配置相同");
            throw new IOException(e);
        }
    }

    /**
     * 获取Socket的BufferedWriter
     */
    private static BufferedWriter getSocketBufferedWriter(Socket socket) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
}