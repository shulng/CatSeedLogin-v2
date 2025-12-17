package cc.baka9.catseedlogin.common;

/**
 * 公共通信接口
 */
public interface CommonCommunication {
    
    /**
     * 发送连接请求，检查玩家登录状态
     * @param playerName 玩家名称
     * @return 1表示已登录，0表示未登录
     */
    int sendConnectRequest(String playerName);
    
    /**
     * 发送保持登录状态请求
     * @param playerName 玩家名称
     */
    void sendKeepLoggedInRequest(String playerName);
}
