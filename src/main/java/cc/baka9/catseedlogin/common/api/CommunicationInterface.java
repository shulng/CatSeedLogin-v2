package cc.baka9.catseedlogin.common.api;

import java.util.Optional;

public interface CommunicationInterface {

    int sendConnectRequest(String playerName);

    void sendKeepLoggedInRequest(String playerName);

    void startServer();

    void stopServer();

    boolean isRunning();

    interface Factory {
        CommunicationInterface create();
    }
}
