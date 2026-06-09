package cc.baka9.catseedlogin.bukkit.command;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cc.baka9.catseedlogin.bukkit.CatScheduler;
import cc.baka9.catseedlogin.bukkit.CatSeedLogin;
import cc.baka9.catseedlogin.bukkit.Config;
import cc.baka9.catseedlogin.bukkit.database.Cache;
import cc.baka9.catseedlogin.bukkit.event.CatSeedPlayerRegisterEvent;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;
import cc.baka9.catseedlogin.util.Util;

public class CommandRegister implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if (args.length != 2) return false;
        Player player = (Player) sender;
        String name = sender.getName();

        if (!canRegister(player, name)) {
            return true;
        }
        if (!args[0].equals(args[1])) {
            sender.sendMessage(Config.Language.REGISTER_PASSWORD_CONFIRM_FAIL);
            return true;
        }
        if (Util.passwordIsDifficulty(args[0])) {
            sender.sendMessage(Config.Language.COMMON_PASSWORD_SO_SIMPLE);
            return true;
        }
        if (!Cache.isLoaded) return true;

        sender.sendMessage("§e注册中..");
        registerPlayerAsync(player, name, args[0]);
        return true;
    }

    private boolean canRegister(Player player, String name) {
        if (Config.Settings.BedrockLoginBypass && LoginPlayerHelper.isFloodgatePlayer(player)) return false;
        if (LoginPlayerHelper.isLogin(name)) {
            player.sendMessage(Config.Language.REGISTER_AFTER_LOGIN_ALREADY);
            return false;
        }
        if (LoginPlayerHelper.isRegister(name)) {
            player.sendMessage(Config.Language.REGISTER_BEFORE_LOGIN_ALREADY);
            return false;
        }
        return true;
    }

    private void registerPlayerAsync(Player player, String name, String password) {
        CatSeedLogin.instance.runTaskAsync(() -> {
            try {
                processRegistration(player, name, password);
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§c服务器内部错误!");
            }
        });
    }

    private void processRegistration(Player player, String name, String password) throws Exception {
        String currentIp = player.getAddress().getAddress().getHostAddress();
        List<LoginPlayer> loginPlayersByIp = CatSeedLogin.sql.getLikeByIp(currentIp);

        if (!player.getAddress().getAddress().isLoopbackAddress()
                && loginPlayersByIp.size() >= Config.Settings.IpRegisterCountLimit) {
            player.sendMessage(Config.Language.REGISTER_MORE
                    .replace("{count}", String.valueOf(loginPlayersByIp.size()))
                    .replace("{accounts}", String.join(", ",
                            loginPlayersByIp.stream().map(LoginPlayer::getName).toArray(String[]::new))));
            return;
        }

        LoginPlayer lp = new LoginPlayer(name, password);
        lp.crypt();
        CatSeedLogin.sql.add(lp);
        Cache.refresh(lp.getName());
        LoginPlayerHelper.add(lp);
        CatScheduler.runTask(() -> {
            CatSeedPlayerRegisterEvent event = new CatSeedPlayerRegisterEvent(Bukkit.getPlayer(name));
            Bukkit.getServer().getPluginManager().callEvent(event);
        });
        player.sendMessage(Config.Language.REGISTER_SUCCESS);
        CatScheduler.updateInventory(player);
        LoginPlayerHelper.recordCurrentIP(player, lp);
    }
}