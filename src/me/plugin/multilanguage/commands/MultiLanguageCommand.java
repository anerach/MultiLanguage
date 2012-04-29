package me.plugin.multilanguage.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

public class MultiLanguageCommand {
	public final MultiLanguage plugin;
	
	public MultiLanguageCommand(MultiLanguage plugin) {
		this.plugin = plugin;
	}
	
	public void sendChannelMessage(String msg, Player sender, Language language) {
		ArrayList<String> players = plugin.channels.get(language);
		for(String player : players) {
			if(sender == null)
				plugin.getServer().getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "] " + ChatColor.YELLOW + msg);
			else
				plugin.getServer().getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "]" + ChatColor.GOLD + "[" + sender.getName() + "] " + ChatColor.YELLOW + msg);
		}
	}
}
