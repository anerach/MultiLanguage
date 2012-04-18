package me.plugin.multilanguage.commands;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSet extends MultiLanguageCommand {
	public CommandSet(MultiLanguage plugin) {
		super(plugin);
	}
	
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length != 2) {
			player.sendMessage(ChatColor.WHITE + "/ml set <language>");
			return true;
		}
		
		Language newLang = Language.getLanguage(args[1]);
		
		if(newLang == null) {
			player.sendMessage("The requested language doesn't exist.");
			return true;
		}
		
		plugin.playerLanguages.put(player.getName(), newLang);
		player.sendMessage("You've changed your language to " + newLang.getName());
		return true;
	}

}
