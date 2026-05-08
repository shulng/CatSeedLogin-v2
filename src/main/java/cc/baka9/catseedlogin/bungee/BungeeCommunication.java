package cc.baka9.catseedlogin.bungee;

import cc.baka9.catseedlogin.util.CommunicationAuth;
import net.md_5.bungee.api.ProxyServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class BungeeCommunication {

    public int sendConnectRequest(String playerName) {
        try (Socket socket = getSocket(); BufferedWriter bufferedWriter = getSocketBufferedWriter(socket)) {
            bufferedWriter.write("Connect");
            bufferedWriter.newLine();
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            return socket.getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void sendKeepLoggedInRequest(String playerName) {
        try (Socket socket = getSocket(); BufferedWriter bufferedWriter = getSocketBufferedWriter(socket)) {
            bufferedWriter.write("KeepLoggedIn");
            bufferedWriter.newLine();
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            String time = String.valueOf(System.currentTimeMillis());
            bufferedWriter.write(time);
            bufferedWriter.newLine();
            String sign = CommunicationAuth.encryption(playerName, time, PluginMain.instance.getConfigManager().getAuthKey());
            bufferedWriter.write(sign);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Socket getSocket() throws IOException {
        try {
            return new Socket(PluginMain.instance.getConfigManager().getProxyHost(), PluginMain.instance.getConfigManager().getProxyPort());
        } catch (IOException e) {
            ProxyServer.getInstance().getLogger().warning("§c请检查装载登录插件的子服是否在配置文件中开启了代理功能，以及Host和Port是否与代理端的配置相同");
            throw new IOException(e);
        }
    }

    private static BufferedWriter getSocketBufferedWriter(Socket socket) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
}
