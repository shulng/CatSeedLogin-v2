package cc.baka9.catseedlogin.bukkit.command;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.baka9.catseedlogin.bukkit.CatScheduler;
import cc.baka9.catseedlogin.bukkit.Communication;
import cc.baka9.catseedlogin.bukkit.Config;
import cc.baka9.catseedlogin.bukkit.PluginContext;
import cc.baka9.catseedlogin.bukkit.Cache;
import cc.baka9.catseedlogin.bukkit.database.MySQL;
import cc.baka9.catseedlogin.bukkit.database.SQLite;
import cc.baka9.catseedlogin.common.model.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;
import cc.baka9.catseedlogin.common.util.ValidationUtil;

public class CommandCatSeedLogin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        return reload(sender, args)
                || setPwd(sender, args)
                || delPlayer(sender, args)
                || setIpCountLimit(sender, args)
                || limitChineseID(sender, args)
                || bedrockLoginBypass(sender, args)
                || LoginwiththesameIP(sender, args)
                || setIdLength(sender, args)
                || beforeLoginNoDamage(sender, args)
                || setReenterInterval(sender, args)
                || afterLoginBack(sender, args)
                || setSpawnLocation(sender, args)
                || commandWhiteListInfo(sender, args)
                || commandWhiteListAdd(sender, args)
                || commandWhiteListDel(sender, args)
                || canTpSpawnLocation(sender, args)
                || autoKick(sender, args)
                || setIpRegCountLimit(sender, args)
                || deathStateQuitRecordLocation(sender, args);
    }

    // ---- Helper: Boolean Toggle ----

    private static class BoolSetting {
        final BooleanSupplier getter;
        final Consumer<Boolean> setter;
        final String label;

        BoolSetting(BooleanSupplier getter, Consumer<Boolean> setter, String label) {
            this.getter = getter;
            this.setter = setter;
            this.label = label;
        }
    }

    private boolean toggle(CommandSender sender, String[] args, String key, BoolSetting setting) {
        if (args.length == 0 || !args[0].equalsIgnoreCase(key)) return false;
        try {
            setting.setter.accept(!setting.getter.getAsBoolean());
            Config.Settings.save();
            sender.sendMessage("§e" + setting.label + " " + (setting.getter.getAsBoolean() ? "§a开启" : "§8关闭"));
        } catch (Exception e) {
            sender.sendMessage("§c设置失败: " + e.getMessage());
        }
        return true;
    }

    // ---- Toggle Settings ----

    private boolean deathStateQuitRecordLocation(CommandSender sender, String[] args) {
        return toggle(sender, args, "deathStateQuitRecordLocation",
                new BoolSetting(() -> Config.Settings.DeathStateQuitRecordLocation,
                        v -> Config.Settings.DeathStateQuitRecordLocation = v,
                        "死亡状态退出游戏记录退出位置"));
    }

    private boolean canTpSpawnLocation(CommandSender sender, String[] args) {
        return toggle(sender, args, "canTpSpawnLocation",
                new BoolSetting(() -> Config.Settings.CanTpSpawnLocation,
                        v -> Config.Settings.CanTpSpawnLocation = v,
                        "登录之前强制在登陆地点"));
    }

    private boolean afterLoginBack(CommandSender sender, String[] args) {
        return toggle(sender, args, "afterLoginBack",
                new BoolSetting(() -> Config.Settings.AfterLoginBack,
                        v -> Config.Settings.AfterLoginBack = v,
                        "登陆之后返回下线地点"));
    }

    private boolean beforeLoginNoDamage(CommandSender sender, String[] args) {
        return toggle(sender, args, "beforeLoginNoDamage",
                new BoolSetting(() -> Config.Settings.BeforeLoginNoDamage,
                        v -> Config.Settings.BeforeLoginNoDamage = v,
                        "登陆之前不受到伤害"));
    }

    private boolean limitChineseID(CommandSender sender, String[] args) {
        return toggle(sender, args, "limitChineseID",
                new BoolSetting(() -> Config.Settings.LimitChineseID,
                        v -> Config.Settings.LimitChineseID = v,
                        "限制中文游戏名"));
    }

    private boolean bedrockLoginBypass(CommandSender sender, String[] args) {
        return toggle(sender, args, "bedrockLoginBypass",
                new BoolSetting(() -> Config.Settings.BedrockLoginBypass,
                        v -> Config.Settings.BedrockLoginBypass = v,
                        "基岩版玩家登录跳过"));
    }

    private boolean LoginwiththesameIP(CommandSender sender, String[] args) {
        return toggle(sender, args, "LoginwiththesameIP",
                new BoolSetting(() -> Config.Settings.LoginwiththesameIP,
                        v -> Config.Settings.LoginwiththesameIP = v,
                        "同IP玩家登录跳过"));
    }

    // ---- Number Settings ----

    private boolean autoKick(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("setAutoKick")) return false;
        try {
            Config.Settings.AutoKick = Integer.parseInt(args[1]);
            Config.Settings.save();
            sender.sendMessage(Config.Settings.AutoKick > 0
                    ? "§e已设置未登录自动踢出累计时间为 §a" + Config.Settings.AutoKick + "秒"
                    : "§e已关闭未登录自动踢出");
        } catch (NumberFormatException e) {
            sender.sendMessage("§e秒数必须是一个数字");
        }
        return true;
    }

    private boolean setReenterInterval(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("setReenterInterval")) return false;
        try {
            Config.Settings.ReenterInterval = Long.parseLong(args[1]);
            Config.Settings.save();
            sender.sendMessage("§e离开服务器重新进入的间隔限制 " + Config.Settings.ReenterInterval + "tick");
        } catch (NumberFormatException e) {
            sender.sendMessage("§c请输入一个数字");
        }
        return true;
    }

    private boolean setIdLength(CommandSender sender, String[] args) {
        if (args.length < 3 || !args[0].equalsIgnoreCase("setIdLength")) return false;
        try {
            Config.Settings.MinLengthID = Integer.parseInt(args[1]);
            Config.Settings.MaxLengthID = Integer.parseInt(args[2]);
            Config.Settings.save();
            sender.sendMessage("§e游戏名最小和最大长度为 " + Config.Settings.MinLengthID + " ~ " + Config.Settings.MaxLengthID);
        } catch (NumberFormatException e) {
            sender.sendMessage("§c请输入数字");
        }
        return true;
    }

    private boolean setIpCountLimit(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("setIpCountLimit")) return false;
        try {
            Config.Settings.IpCountLimit = Integer.parseInt(args[1]);
            Config.Settings.save();
            sender.sendMessage("§e相同ip登录限制数量为 " + Config.Settings.IpCountLimit);
        } catch (NumberFormatException e) {
            sender.sendMessage("§c请输入数字");
        }
        return true;
    }

    private boolean setIpRegCountLimit(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("setIpRegCountLimit")) return false;
        try {
            Config.Settings.IpRegisterCountLimit = Integer.parseInt(args[1]);
            Config.Settings.save();
            sender.sendMessage("§e相同ip注册限制数量为 " + Config.Settings.IpRegisterCountLimit);
        } catch (NumberFormatException e) {
            sender.sendMessage("§c请输入数字");
        }
        return true;
    }

    // ---- Command Whitelist ----

    private boolean commandWhiteListInfo(CommandSender sender, String[] args) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("commandWhiteListInfo")) return false;
        sender.sendMessage("§e登录前可执行指令: ");
        Config.Settings.CommandWhiteList.forEach(cmdRegex -> sender.sendMessage(cmdRegex.toString()));
        return true;
    }

    private boolean commandWhiteListAdd(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("commandWhiteListAdd")) return false;
        String regex = joinArgs(args, 1);
        Pattern pattern = Pattern.compile(regex);
        if (containsRegex(regex)) {
            sender.sendMessage("§c已经存在 " + regex);
        } else {
            Config.Settings.CommandWhiteList.add(pattern);
            Config.Settings.save();
            sender.sendMessage("§e已添加登录前可执行指令 " + regex);
        }
        return true;
    }

    private boolean commandWhiteListDel(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("commandWhiteListDel")) return false;
        String regex = joinArgs(args, 1);
        if (containsRegex(regex)) {
            removeRegex(regex);
            Config.Settings.save();
            sender.sendMessage("§e已删除登录前可执行指令 " + regex);
        } else {
            sender.sendMessage("§c不存在 " + regex);
        }
        return true;
    }

    private static String joinArgs(String[] args, int from) {
        String[] cmd = new String[args.length - from];
        System.arraycopy(args, from, cmd, 0, cmd.length);
        return String.join(" ", cmd);
    }

    private static boolean containsRegex(String regex) {
        return Config.Settings.CommandWhiteList.stream()
                .map(Pattern::toString).collect(Collectors.toList()).contains(regex);
    }

    private static void removeRegex(String regex) {
        Config.Settings.CommandWhiteList.removeIf(p -> p.toString().equals(regex));
    }

    // ---- Spawn Location ----

    private boolean setSpawnLocation(CommandSender sender, String[] args) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("setSpawnLocation")) return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c不能在控制台使用这个指令");
            return true;
        }
        Config.Settings.SpawnLocation = ((Player) sender).getLocation();
        Config.Settings.save();
        sender.sendMessage("§e已设置玩家登陆坐标为你站着的位置");
        return true;
    }

    // ---- Reload ----

    private boolean reload(CommandSender sender, String[] args) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) return false;
        Config.reload();
        PluginContext.setSql(Config.MySQL.Enable ? new MySQL(PluginContext.getPlugin()) : new SQLite(PluginContext.getPlugin()));
        try {
            PluginContext.getSql().init();
            Cache.refreshAll();
        } catch (Exception e) {
            PluginContext.getLogger().warning("§c加载数据库时出错");
            e.printStackTrace();
        }
        try {
            Communication.socketServerStopAsync();
        } catch (Exception e) {
            PluginContext.getLogger().warning("§c停止通信服务时出错");
            e.printStackTrace();
        }
        if (Config.BungeeCord.Enable) {
            try {
                Communication.socketServerStartAsync();
            } catch (Exception e) {
                PluginContext.getLogger().warning("§c启动通信服务时出错");
                e.printStackTrace();
            }
        }
        sender.sendMessage("配置已重载!");
        return true;
    }

    // ---- Delete Player ----

    private boolean delPlayer(CommandSender sender, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("delplayer")) return false;
        String name = args[1];
        LoginPlayer lp = Cache.getIgnoreCase(name);
        if (lp == null) {
            sender.sendMessage(String.format("§c账户 §a%s §c不存在", name));
            return true;
        }
        delPlayerAsync(sender, lp);
        return true;
    }

    private void delPlayerAsync(CommandSender sender, LoginPlayer lp) {
        CatScheduler.runTaskAsync(() -> {
            try {
                PluginContext.getSql().del(lp.getName());
                Cache.refresh(lp.getName());
                LoginPlayerHelper.remove(lp);
                sender.sendMessage("§e已删除账户 §a" + lp.getName());
                kickPlayerIfOnline(lp.getName());
            } catch (Exception e) {
                sender.sendMessage("§c数据库异常!");
                e.printStackTrace();
            }
        });
    }

    private static void kickPlayerIfOnline(String name) {
        CatScheduler.runTask(() -> {
            Player p = Bukkit.getPlayerExact(name);
            if (p != null && p.isOnline()) {
                p.kickPlayer("§c你的账户已被删除!");
            }
        });
    }

    // ---- Set Password ----

    private boolean setPwd(CommandSender sender, String[] args) {
        if (args.length < 3 || !args[0].equalsIgnoreCase("setpwd")) return false;
        String name = args[1], pwd = args[2];
        if (ValidationUtil.isPasswordTooSimple(pwd)) {
            sender.sendMessage("§c密码必须是6~16位之间的数字和字母组成");
            return true;
        }
        sender.sendMessage("§e设置中..");
        CatScheduler.runTaskAsync(() -> setPwdLookup(sender, name, pwd));
        return true;
    }

    private void setPwdLookup(CommandSender sender, String name, String pwd) {
        LoginPlayer lp = Cache.getIgnoreCase(name);
        if (lp == null) {
            setPwdRegisterNew(sender, name, pwd);
        } else {
            setPwdUpdateExisting(sender, lp, pwd);
        }
    }

    private void setPwdRegisterNew(CommandSender sender, String name, String pwd) {
        try {
            LoginPlayer lp = new LoginPlayer(name, pwd);
            lp.crypt();
            PluginContext.getSql().add(lp);
            Cache.refresh(lp.getName());
            sender.sendMessage("§a指定账户不存在,现已注册..");
        } catch (Exception e) {
            sender.sendMessage("§c数据库异常!");
            e.printStackTrace();
        }
    }

    private void setPwdUpdateExisting(CommandSender sender, LoginPlayer lp, String pwd) {
        try {
            lp.setPassword(pwd);
            lp.crypt();
            PluginContext.getSql().edit(lp);
            Cache.refresh(lp.getName());
            LoginPlayerHelper.remove(lp);
            sender.sendMessage(String.join(" ", "§a玩家", lp.getName(), "密码已设置"));
            notifyPlayerPasswordChanged(lp);
        } catch (Exception e) {
            sender.sendMessage("§c数据库异常!");
            e.printStackTrace();
        }
    }

    private void notifyPlayerPasswordChanged(LoginPlayer lp) {
        CatScheduler.runTask(() -> {
            Player p = Bukkit.getPlayer(lp.getName());
            if (p == null || !p.isOnline()) return;
            p.sendMessage("§c密码已被管理员重新设置,请重新登录");
            if (!Config.Settings.CanTpSpawnLocation) return;
            p.teleport(Config.Settings.SpawnLocation);
            if (PluginContext.isLoadProtocolLib()) {
                LoginPlayerHelper.sendBlankInventoryPacket(p);
            }
        });
    }
}