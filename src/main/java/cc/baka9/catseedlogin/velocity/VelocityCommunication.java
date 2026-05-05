package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.common.CommonCommunication;
import cc.baka9.catseedlogin.util.CommunicationAuth;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class VelocityCommunication implements CommonCommunication {

    @Override
    public int sendConnectRequest(String playerName) {
        try (Socket socket = getSocket(); 
             BufferedWriter bufferedWriter = getSocketBufferedWriter(socket)) {
            bufferedWriter.write("Connect");
            bufferedWriter.newLine();
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            return socket.getInputStream().read();
        } catch (IOException e) {
            PluginMain.getInstance().getLogger().error("Failed to send connect request for player: " + playerName, e);
        }
        return 0;
    }

    @Override
    public void sendKeepLoggedInRequest(String playerName) {
        try (Socket socket = getSocket(); 
             BufferedWriter bufferedWriter = getSocketBufferedWriter(socket)) {
            bufferedWriter.write("KeepLoggedIn");
            bufferedWriter.newLine();
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            String time = String.valueOf(System.currentTimeMillis());
            bufferedWriter.write(time);
            bufferedWriter.newLine();
            String sign = CommunicationAuth.encryption(playerName, time, PluginMain.getInstance().getConfigManager().getAuthKey());
            bufferedWriter.write(sign);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            PluginMain.getInstance().getLogger().error("Failed to send keep logged in request for player: " + playerName, e);
        }
    }

    private static Socket getSocket() throws IOException {
        try {
            return new Socket(PluginMain.getInstance().getConfigManager().getHost(), PluginMain.getInstance().getConfigManager().getPort());
        } catch (IOException e) {
            PluginMain instance = PluginMain.getInstance();
            ProxyServer proxyServer = instance.getProxyServer();
            Logger logger = instance.getLogger();
            logger.warn("§c请检查装载登录插件的子服是否在配置文件中开启了代理功能，以及Host和Port是否与Velocity端的配置相同");
            throw new IOException(e);
        }
    }

    private static BufferedWriter getSocketBufferedWriter(Socket socket) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
}
