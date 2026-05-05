package cc.baka9.catseedlogin.common.api;

public interface DatabaseConfig {

    boolean isMySQL();

    String getHost();
    int getPort();
    String getDatabase();
    String getUser();
    String getPassword();
}
