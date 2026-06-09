package cc.baka9.catseedlogin.bukkit.database;

import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class SQL {
    protected JavaPlugin plugin;

    public SQL(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() throws SQLException {
        try {
            flush(new BufferStatement("CREATE TABLE IF NOT EXISTS accounts (name CHAR(255), password CHAR(255), email CHAR(255), ips CHAR(255), lastAction TIMESTAMP, location CHAR(255) DEFAULT NULL)"));
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to create accounts table: " + e.getMessage());
            throw e;
        }

        try {
            flush(new BufferStatement("ALTER TABLE accounts ADD email CHAR(255)"));
        } catch (SQLException e) {
            if (!e.getMessage().toLowerCase().contains("duplicate column name")) throw e;
        }

        try {
            flush(new BufferStatement("ALTER TABLE accounts ADD ips CHAR(255)"));
        } catch (SQLException e) {
            if (!e.getMessage().toLowerCase().contains("duplicate column name")) throw e;
        }

        try {
            flush(new BufferStatement("ALTER TABLE accounts ADD location CHAR(255)"));
        } catch (SQLException e) {
            if (!e.getMessage().toLowerCase().contains("duplicate column name")) throw e;
        }
    }

    public void add(LoginPlayer lp) {
        try {
            flush(new BufferStatement("INSERT INTO accounts (name, password, lastAction, email, ips, location) VALUES (?, ?, ?, ?, ?, ?)",
                lp.getName(), lp.getPassword(), new Date(), lp.getEmail(), lp.getIps(), lp.getLocation()));
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to add player: " + lp.getName() + " - " + e.getMessage());
        }
    }

    public void del(String name) {
        try {
            flush(new BufferStatement("DELETE FROM accounts WHERE name = ?", name));
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to delete player: " + name + " - " + e.getMessage());
        }
    }

    public void edit(LoginPlayer lp) {
        try {
            flush(new BufferStatement("UPDATE accounts SET password = ?, lastAction = ?, email = ?, ips = ?, location = ? WHERE name = ?",
                lp.getPassword(), new Date(), lp.getEmail(), lp.getIps(), lp.getLocation(), lp.getName()));
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to edit player: " + lp.getName() + " - " + e.getMessage());
        }
    }

    public void updateLocation(String name, String location) {
        try {
            flush(new BufferStatement("UPDATE accounts SET location = ? WHERE name = ?", location, name));
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to update location for player: " + name + " - " + e.getMessage());
        }
    }

    public String getLocation(String name) {
        try {
            return queryForString("SELECT location FROM accounts WHERE name = ?", name);
        } catch (Exception e) {
            plugin.getLogger().severe("Failed to get location for player: " + name + " - " + e.getMessage());
            return null;
        }
    }

    public LoginPlayer get(String name) {
        String sql = "SELECT * FROM accounts WHERE name = ?";
        try (PreparedStatement ps = new BufferStatement(sql, name).prepareStatement(getConnection());
             ResultSet resultSet = ps.executeQuery()) {
            return mapLoginPlayerOrNull(resultSet);
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to get player: " + name);
            return null;
        }
    }

    private String queryForString(String sql, Object... params) {
        try (PreparedStatement ps = new BufferStatement(sql, params).prepareStatement(getConnection());
             ResultSet resultSet = ps.executeQuery()) {
            return resultSet.next() ? resultSet.getString(1) : null;
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to query: " + sql + " - " + e.getMessage());
            return null;
        }
    }

    private LoginPlayer mapLoginPlayerOrNull(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return mapLoginPlayer(resultSet);
        }
        return null;
    }

    public List<LoginPlayer> getAll() {
        try (PreparedStatement ps = new BufferStatement("SELECT * FROM accounts").prepareStatement(getConnection());
             ResultSet resultSet = ps.executeQuery()) {
            List<LoginPlayer> lps = new ArrayList<>();
            while (resultSet.next()) {
                lps.add(mapLoginPlayer(resultSet));
            }
            return lps;
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to get all players: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<LoginPlayer> getLikeByIp(String ip) {
        String likePattern = "%" + ip + "%";
        try (PreparedStatement ps = new BufferStatement("SELECT * FROM accounts WHERE ips LIKE ?", likePattern).prepareStatement(getConnection());
             ResultSet resultSet = ps.executeQuery()) {
            List<LoginPlayer> lps = new ArrayList<>();
            while (resultSet.next()) {
                lps.add(mapLoginPlayer(resultSet));
            }
            return lps;
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to query players by IP: " + ip + " - " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private LoginPlayer mapLoginPlayer(ResultSet resultSet) throws SQLException {
        LoginPlayer lp = new LoginPlayer(resultSet.getString("name"), resultSet.getString("password"));
        lp.setLastAction(resultSet.getTimestamp("lastAction").getTime());
        lp.setEmail(resultSet.getString("email"));
        lp.setIps(resultSet.getString("ips"));
        lp.setLocation(resultSet.getString("location"));
        return lp;
    }

    public abstract Connection getConnection() throws SQLException;

    public abstract void closeConnection();

    public void flush(BufferStatement bufferStatement) throws SQLException {
        try (PreparedStatement ps = bufferStatement.prepareStatement(getConnection())) {
            ps.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to execute flush: " + e.getMessage());
            throw e;
        }
    }
}
