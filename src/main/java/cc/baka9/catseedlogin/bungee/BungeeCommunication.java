package cc.baka9.catseedlogin.bungee;

import cc.baka9.catseedlogin.common.communication.BaseCommunication;

public class BungeeCommunication extends BaseCommunication {

    @Override
    protected String getHost() {
        return PluginMain.instance.getConfigManager().getProxyHost();
    }

    @Override
    protected int getPort() {
        return PluginMain.instance.getConfigManager().getProxyPort();
    }

    @Override
    protected void logError(String message, Exception e) {
        PluginMain.instance.getLogger().severe(message);
        e.printStackTrace();
    }

    @Override
    protected void logWarning(String message) {
        PluginMain.instance.getLogger().warning(message);
    }

    @Override
    protected String getAuthKey() {
        return PluginMain.instance.getConfigManager().getAuthKey();
    }
}
