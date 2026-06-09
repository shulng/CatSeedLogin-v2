package cc.baka9.catseedlogin.bukkit.command;

import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cc.baka9.catseedlogin.bukkit.CatScheduler;
import cc.baka9.catseedlogin.bukkit.Config;
import cc.baka9.catseedlogin.bukkit.PluginContext;
import cc.baka9.catseedlogin.bukkit.Cache;
import cc.baka9.catseedlogin.bukkit.object.EmailCode;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;
import cc.baka9.catseedlogin.util.EmailSender;
import cc.baka9.catseedlogin.util.Util;

public class CommandResetPassword implements CommandExecutor {
    private static final long EMAIL_CODE_DURATION = 1000 * 60 * 5;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0 || !(sender instanceof Player)) return false;

        Player player = (Player) sender;
        String name = player.getName();

        if (Config.Settings.BedrockLoginBypass && LoginPlayerHelper.isFloodgatePlayer(player)) return true;

        LoginPlayer lp = Cache.getIgnoreCase(name);
        if (lp == null) {
            sender.sendMessage(Config.Language.RESETPASSWORD_NOREGISTER);
            return true;
        }
        if (!Config.EmailVerify.Enable) {
            sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_DISABLE);
            return true;
        }

        if (args[0].equalsIgnoreCase("forget")) {
            return handleForget(sender, name, lp);
        }

        if (args[0].equalsIgnoreCase("re") && args.length > 2) {
            return handleReset(player, lp, args[1], args[2]);
        }

        return true;
    }

    private boolean handleForget(CommandSender sender, String name, LoginPlayer lp) {
        if (lp.getEmail() == null) {
            sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_NO_SET);
            return true;
        }

        try {
            Optional<EmailCode> optional = EmailCode.getByName(name, EmailCode.Type.ResetPassword);
            if (optional.isPresent()) {
                sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_REPEAT_SEND_MESSAGE
                        .replace("{email}", optional.get().getEmail()));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmailCode emailCode = EmailCode.create(name, lp.getEmail(), EMAIL_CODE_DURATION, EmailCode.Type.ResetPassword);
        sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_SENDING_MESSAGE
                .replace("{email}", lp.getEmail()));

        sendResetEmailAsync(sender, name, emailCode);
        return true;
    }

    private void sendResetEmailAsync(CommandSender sender, String name, EmailCode emailCode) {
        CatScheduler.runTaskAsync(() -> {
            try {
                String content = buildResetEmailContent(name, emailCode);
                EmailSender.sendEmail(emailCode.getEmail(), "重置密码", content);
                notifyEmailSent(sender, emailCode.getEmail());
            } catch (Exception e) {
                notifyEmailFailed(sender);
                e.printStackTrace();
            }
        });
    }

    private String buildResetEmailContent(String name, EmailCode emailCode) {
        long minutes = emailCode.getDurability() / (1000 * 60);
        return "你的验证码是 <strong>" + emailCode.getCode() + "</strong>" +
                "<br/>在服务器中使用帐号 " + name + " 输入指令<strong>/resetpassword re " +
                emailCode.getCode() + " 新密码</strong> 来重置新密码" +
                "<br/>此验证码有效期为 " + minutes + "分钟";
    }

    private void notifyEmailSent(CommandSender sender, String email) {
        Bukkit.getScheduler().runTask(PluginContext.getPlugin(), () ->
                sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_SENT_MESSAGE.replace("{email}", email)));
    }

    private void notifyEmailFailed(CommandSender sender) {
        Bukkit.getScheduler().runTask(PluginContext.getPlugin(), () ->
                sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_WARN));
    }

    private boolean handleReset(Player player, LoginPlayer lp, String code, String pwd) {
        CommandSender sender = player;
        if (lp.getEmail() == null) {
            sender.sendMessage(Config.Language.RESETPASSWORD_EMAIL_NO_SET);
            return true;
        }

        try {
            Optional<EmailCode> optional = EmailCode.getByName(lp.getName(), EmailCode.Type.ResetPassword);
            if (!optional.isPresent()) {
                sender.sendMessage(Config.Language.RESETPASSWORD_FAIL);
                return true;
            }
            if (!optional.get().getCode().equals(code)) {
                sender.sendMessage(Config.Language.RESETPASSWORD_EMAILCODE_INCORRECT);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(Config.Language.RESETPASSWORD_FAIL);
            return true;
        }

        if (Util.passwordIsDifficulty(pwd)) {
            sender.sendMessage(Config.Language.COMMON_PASSWORD_SO_SIMPLE);
            return true;
        }

        sender.sendMessage("§e密码重置中..");
        processPasswordResetAsync(player, lp, pwd);
        return true;
    }

    private void processPasswordResetAsync(Player player, LoginPlayer lp, String pwd) {
        CommandSender sender = player;
        String name = lp.getName();
        CatScheduler.runTaskAsync(() -> {
            executePasswordReset(name, lp, pwd, sender);
        });
    }

    private void executePasswordReset(String name, LoginPlayer lp, String pwd, CommandSender sender) {
        try {
            lp.setPassword(pwd);
            lp.crypt();
            PluginContext.getSql().edit(lp);
            Cache.refresh(name);
            LoginPlayerHelper.remove(lp);
            EmailCode.removeByName(name, EmailCode.Type.ResetPassword);
            Player player = Bukkit.getPlayer(name);
            notifyResetSuccess(name, player);
        } catch (Exception e) {
            sender.sendMessage("§c数据库异常!");
            e.printStackTrace();
        }
    }

    private void notifyResetSuccess(String name, Player player) {
        Player p = Bukkit.getPlayer(name);
        if (p == null || !p.isOnline()) return;

        if (Config.Settings.CanTpSpawnLocation) {
            CatScheduler.teleport(p, Config.Settings.SpawnLocation);
        }
        p.sendMessage(Config.Language.RESETPASSWORD_SUCCESS);

        if (PluginContext.isLoadProtocolLib()) {
            LoginPlayerHelper.sendBlankInventoryPacket(player);
        }
    }
}