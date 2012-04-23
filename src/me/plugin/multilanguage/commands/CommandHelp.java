package me.plugin.multilanguage.commands;

import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandHelp extends MultiLanguageCommand {
	public CommandHelp(MultiLanguage plugin) {
		super(plugin);
	}
	
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length > 1) {
			player.sendMessage(ChatColor.WHITE + "/ml help");
			return true;
		}
		
		player.sendMessage(ChatColor.DARK_RED + "-~= MultiLanguage Commands =~-");
		player.sendMessage(ChatColor.GOLD + "/ml help " + ChatColor.GREEN + "-" + ChatColor.BLUE + " Shows information about the commands");
		player.sendMessage(ChatColor.GOLD + "/ml set <language> " + ChatColor.GREEN + "-" + ChatColor.BLUE + " Changes your language");
		player.sendMessage(ChatColor.GOLD + "/ml list " + ChatColor.GREEN + "-" + ChatColor.BLUE + " Shows a list of all the available languages");
		player.sendMessage(ChatColor.GOLD + "/ml channel " + ChatColor.GREEN + "-" + ChatColor.BLUE + " Joins the channel of your current language");
		
		return true;
	}

}
