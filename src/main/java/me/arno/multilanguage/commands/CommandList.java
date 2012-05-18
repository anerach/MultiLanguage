package me.arno.multilanguage.commands;

import me.arno.multilanguage.Language;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandList extends MultiLanguageCommand {
	public CommandList() {
		setCommandUsage("/ml list");
	}
	
	@Override
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length > 0)
			return false;
		
		player.sendMessage(ChatColor.DARK_RED + "- Available Languages -");
		for(Language lang : Language.values())
			player.sendMessage(ChatColor.GOLD + lang.getName() + ChatColor.DARK_RED + " (" + lang.getExtension().toUpperCase() + ")");
		
		return true;
	}
}
