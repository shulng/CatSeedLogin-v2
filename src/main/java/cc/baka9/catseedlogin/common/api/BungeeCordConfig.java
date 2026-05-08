package cc.baka9.catseedlogin.common.api;

public interface BungeeCordConfig {

    boolean isEnable();
    String getProxyHost();
    int getProxyPort();
    String getAuthKey();
    String getLoginServerName();

    default String getHost() {
        return getProxyHost();
    }

    default int getPort() {
        return getProxyPort();
    }
}
