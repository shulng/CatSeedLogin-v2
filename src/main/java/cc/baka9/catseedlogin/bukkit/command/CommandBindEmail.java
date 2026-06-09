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
import cc.baka9.catseedlogin.bukkit.database.Cache;
import cc.baka9.catseedlogin.bukkit.object.EmailCode;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;
import cc.baka9.catseedlogin.util.Mail;
import cc.baka9.catseedlogin.util.Util;

public class CommandBindEmail implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0 || !(sender instanceof Player)) return false;

        String name = sender.getName();

        if (!canBindEmail(sender, name)) return true;

        String subCommand = args[0].toLowerCase();
        if ("set".equals(subCommand)) {
            handleSet(sender, name, args);
        } else if ("verify".equals(subCommand)) {
            handleVerify(sender, name, args);
        }

        return true;
    }

    private boolean canBindEmail(CommandSender sender, String name) {
        Player player = (Player) sender;

        if (Config.Settings.BedrockLoginBypass && LoginPlayerHelper.isFloodgatePlayer(player)) return false;

        LoginPlayer lp = Cache.getIgnoreCase(name);
        if (lp == null) {
            sender.sendMessage("§c你还未注册!");
            return false;
        }
        if (!LoginPlayerHelper.isLogin(name)) {
            sender.sendMessage("§c你还未登陆!");
            return false;
        }
        if (!Config.EmailVerify.Enable) {
            sender.sendMessage("§c服务器没有开启邮箱功能");
            return false;
        }
        return true;
    }

    private void handleSet(CommandSender sender, String name, String[] args) {
        if (args.length <= 1) return;

        LoginPlayer lp = Cache.getIgnoreCase(name);
        if (lp.getEmail() != null && Util.checkMail(lp.getEmail())) {
            sender.sendMessage("§c你已经绑定过邮箱了!");
            return;
        }

        String mail = args[1];
        if (!Util.checkMail(mail)) {
            sender.sendMessage("§c邮箱格式不正确!");
            return;
        }

        Optional<EmailCode> existingCode = EmailCode.getByName(name, EmailCode.Type.Bind);
        if (existingCode.isPresent() && existingCode.get().getEmail().equals(mail)) {
            sender.sendMessage("§c已经向 " + mail + " 邮箱中发送验证码，请不要重复此操作");
            return;
        }

        EmailCode bindEmail = EmailCode.create(name, mail, 1000 * 60 * 5, EmailCode.Type.Bind);
        sender.sendMessage("§6向邮箱发送验证码中...");
        sendEmailCode(sender, name, mail, bindEmail);
    }

    private void handleVerify(CommandSender sender, String name, String[] args) {
        if (args.length <= 1) return;

        LoginPlayer lp = Cache.getIgnoreCase(name);
        if (lp.getEmail() != null && Util.checkMail(lp.getEmail())) {
            sender.sendMessage("§c你已经绑定过邮箱了!");
            return;
        }

        Optional<EmailCode> emailOptional = EmailCode.getByName(name, EmailCode.Type.Bind);
        if (!emailOptional.isPresent()) {
            sender.sendMessage("§c你没有待绑定的邮箱，或者验证码已过期");
            return;
        }

        EmailCode bindEmail = emailOptional.get();
        if (!bindEmail.getCode().equals(args[1])) {
            sender.sendMessage("§c验证码错误!");
            return;
        }

        sender.sendMessage("§e绑定邮箱中..");
        bindEmail(sender, lp, bindEmail);
    }

    private void sendEmailCode(CommandSender sender, String name, String mail, EmailCode bindEmail) {
        CatScheduler.runTaskAsync(() -> {
            try {
                Mail.sendMail(mail, "邮箱绑定", "你的验证码是 <strong>" + bindEmail.getCode() + "</strong>" +
                        "<br/>在服务器中使用帐号 " + name + " 输入指令<strong>/bindemail verify " + bindEmail.getCode() + "</strong> 来绑定邮箱" +
                        "<br/>绑定邮箱之后可用于忘记密码时重置自己的密码" +
                        "<br/>此验证码有效期为 " + (bindEmail.getDurability() / (1000 * 60)) + "分钟");
                CatScheduler.runTask(() -> {
                    sender.sendMessage("§6已经向邮箱 " + mail + " 发送了一串绑定验证码，请检查你的邮箱的收件箱");
                    sender.sendMessage("§c如果未收到，请检查邮箱的垃圾箱!");
                });
            } catch (Exception e) {
                CatScheduler.runTask(() -> sender.sendMessage("§c发送邮件失败,服务器内部错误!"));
                e.printStackTrace();
            }
        });
    }

    private void bindEmail(CommandSender sender, LoginPlayer lp, EmailCode bindEmail) {
        CatScheduler.runTaskAsync(() -> {
            try {
                lp.setEmail(bindEmail.getEmail());
                PluginContext.getSql().edit(lp);
                Cache.refresh(lp.getName());
                CatScheduler.runTask(() -> notifyBindSuccess(sender, bindEmail));
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage("§c服务器内部错误!");
            }
        });
    }

    private void notifyBindSuccess(CommandSender sender, EmailCode bindEmail) {
        Player syncPlayer = Bukkit.getPlayer(((Player) sender).getUniqueId());
        if (syncPlayer == null || !syncPlayer.isOnline()) return;

        syncPlayer.sendMessage("§a邮箱已绑定 " + bindEmail.getEmail() + " 忘记密码时可以用邮箱重置自己的密码");
        EmailCode.removeByName(syncPlayer.getName(), EmailCode.Type.Bind);
    }
}
