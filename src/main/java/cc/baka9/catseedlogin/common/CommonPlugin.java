package cc.baka9.catseedlogin.common;

/**
 * 公共插件接口
 */
public interface CommonPlugin {
    
    /**
     * 获取公共配置
     */
    CommonConfig getConfig();
    
    /**
     * 获取公共通信
     */
    CommonCommunication getCommunication();
    
    /**
     * 记录日志
     * @param message 日志信息
     */
    void logInfo(String message);
    
    /**
     * 记录警告日志
     * @param message 警告信息
     */
    void logWarn(String message);
    
    /**
     * 记录错误日志
     * @param message 错误信息
     * @param throwable 异常
     */
    void logError(String message, Throwable throwable);
}
