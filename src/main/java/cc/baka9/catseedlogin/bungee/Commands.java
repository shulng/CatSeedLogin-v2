package cc.baka9.catseedlogin.bungee;

import cc.baka9.catseedlogin.common.i18n.MessageKey;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class Commands extends net.md_5.bungee.api.plugin.Command {

    public Commands(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            PluginMain.instance.getConfigManager().reload();
            commandSender.sendMessage(new TextComponent(MessageKey.CONFIG_RELOADED.get()));
        }
    }
}
