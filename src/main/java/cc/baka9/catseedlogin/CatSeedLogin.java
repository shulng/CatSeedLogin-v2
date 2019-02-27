package cc.baka9.catseedlogin;

import cc.baka9.catseedlogin.command.CommandChangePassword;
import cc.baka9.catseedlogin.command.CommandLogin;
import cc.baka9.catseedlogin.command.CommandRegister;
import cc.baka9.catseedlogin.database.Cache;
import cc.baka9.catseedlogin.database.SQL;
import cc.baka9.catseedlogin.database.SQLite;
import cc.baka9.catseedlogin.object.LoginPlayerHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CatSeedLogin extends JavaPlugin {

    private static CatSeedLogin instance;
    public static SQL sql;

    @Override
    public void onEnable(){
        instance = this;
        sql = new SQLite();
        try {
            if (!sql.hasTable("accounts")) {
                sql.createBD();
            }
            Cache.refreshAll();
        } catch (Exception e) {
            getLogger().warning("§c加载数据库时出错");
            e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginCommand("login").setExecutor(new CommandLogin());
        getServer().getPluginCommand("register").setExecutor(new CommandRegister());
        getServer().getPluginCommand("changepassword").setExecutor(new CommandChangePassword());
        getServer().getScheduler().runTaskTimer(this, () -> {
            if (!Cache.isLoaded) return;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!LoginPlayerHelper.isLogin(player.getName())) {

                    if (!LoginPlayerHelper.isRegister(player.getName())) {
                        player.sendMessage("§a你还没有注册,请输入§e/reg 密码 重复密码 §a来注册");
                        continue;
                    }
                    player.sendMessage("§a请输入§e/l 密码 §a来登录游戏");
                    player.sendMessage("§b如果你从未注册,请换个游戏名!");

                }
            }
        }, 0, 20 * 5);


    }

    @Override
    public void onDisable(){
        try {
            sql.getConnection().close();
        } catch (Exception e) {
            getLogger().warning("获取数据库连接时出错");
            e.printStackTrace();
        }
        super.onDisable();
    }

    public static CatSeedLogin getInstance(){
        return instance;
    }
}
