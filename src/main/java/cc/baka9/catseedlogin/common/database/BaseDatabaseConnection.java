package cc.baka9.catseedlogin.common.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDatabaseConnection {

    protected Connection connection;

    protected boolean isConnectionValid() throws SQLException {
        if (connection == null || connection.isClosed()) {
            return false;
        }
        try (PreparedStatement ps = connection.prepareStatement("SELECT 1")) {
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    protected Connection getValidConnection() throws SQLException {
        if (isConnectionValid()) {
            return connection;
        }
        closeConnection();
        connection = createConnection();
        return connection;
    }

    protected abstract Connection createConnection() throws SQLException;

    public abstract void closeConnection();

    public Connection getConnection() throws SQLException {
        return getValidConnection();
    }
}
