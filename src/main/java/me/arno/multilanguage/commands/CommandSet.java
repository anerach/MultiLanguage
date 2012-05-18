package me.arno.multilanguage.commands;

import me.arno.multilanguage.Language;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSet extends MultiLanguageCommand {
	public CommandSet() {
		setCommandUsage("/ml set <language>");
	}
	
	@Override
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length > 1 || args.length < 1)
			return false;
		
		Language language = Language.getLanguage(args[0]);
		if(getLanguageManager().setPlayerLanguage(player, language)) {
			player.sendMessage(ChatColor.BLUE + "You've changed your language to " + ChatColor.GOLD + language.getName());
		} else {
			player.sendMessage(ChatColor.BLUE + "You've entered an incorrect language");
		}
		return true;
	}
}
