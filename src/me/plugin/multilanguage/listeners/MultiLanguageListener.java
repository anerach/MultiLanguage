package me.plugin.multilanguage.listeners;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.Localisation;
import me.plugin.multilanguage.MultiLanguage;

public class MultiLanguageListener implements Listener {
	public final MultiLanguage plugin;
	public final Logger log;
	
	public MultiLanguageListener(MultiLanguage plugin) {
		this.plugin = plugin;
		this.log = plugin.log;
	}
	
	public void sendMessage(String msg, Player player) {
		Localisation localisation;
		
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			localisation = new Localisation(plugin, plugin.playerLanguages.get(p.getName()));
			p.sendMessage(localisation.getMessage(msg, player));
		}
	}
	
	public void sendChannelMessage(String msg, Player sender, Language language) {
		ArrayList<String> players = plugin.channels.get(language);
		for(String player : players) {
			plugin.getServer().getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "]"+ ChatColor.BLUE + "[" + ChatColor.GOLD + sender.getName() + ChatColor.BLUE + "] " + ChatColor.YELLOW + msg);
		}
	}
}
