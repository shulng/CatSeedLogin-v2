package cc.baka9.catseedlogin.common;

/**
 * 公共配置接口
 */
public interface CommonConfig {
    
    /**
     * 加载配置
     */
    void load();
    
    /**
     * 获取 Host
     */
    String getHost();
    
    /**
     * 获取 Port
     */
    int getPort();
    
    /**
     * 获取 LoginServerName
     */
    String getLoginServerName();
    
    /**
     * 获取 AuthKey
     */
    String getAuthKey();
}
