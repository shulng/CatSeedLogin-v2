package cc.baka9.catseedlogin.velocity;

import cc.baka9.catseedlogin.common.communication.BaseCommunication;

public class VelocityCommunication extends BaseCommunication {

    @Override
    protected String getProxyHost() {
        return PluginMain.getInstance().getConfigManager().getProxyHost();
    }

    @Override
    protected int getProxyPort() {
        return PluginMain.getInstance().getConfigManager().getProxyPort();
    }

    @Override
    protected void logError(String message, Exception e) {
        PluginMain.getInstance().getLogger().error(message, e);
    }

    @Override
    protected void logWarning(String message) {
        PluginMain.getInstance().getLogger().warn(message);
    }

    @Override
    protected String getAuthKey() {
        return PluginMain.getInstance().getConfigManager().getAuthKey();
    }
}
