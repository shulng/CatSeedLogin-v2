package cc.baka9.catseedlogin.common.api;

public interface BungeeCordConfig {

    boolean isEnable();
    String getHost();
    int getPort();
    String getAuthKey();
    String getLoginServerName();
}
