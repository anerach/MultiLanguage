package me.plugin.multilanguage.commands;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandList extends MultiLanguageCommand {
	public CommandList(MultiLanguage plugin) {
		super(plugin);
	}
	
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length != 1) {
			player.sendMessage(ChatColor.WHITE + "/ml list");
			return true;
		}
		
		player.sendMessage(ChatColor.DARK_RED + "- Available Languages -");
		for(Language lang : Language.values())
			player.sendMessage(ChatColor.GOLD + lang.name().toLowerCase() + ChatColor.DARK_RED + " (" + lang.getExtension().toUpperCase() + ")");
		
		return true;
	}

}
