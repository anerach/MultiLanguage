package me.arno.multilanguage.listener;

import me.arno.multilanguage.Language;
import me.arno.multilanguage.Localisation;
import me.arno.multilanguage.MultiLanguage;
import me.arno.multilanguage.managers.ChannelManager;
import me.arno.multilanguage.managers.LanguageManager;
import me.arno.multilanguage.managers.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MultiLanguageListener implements Listener {
	public MultiLanguage plugin;
	
	public MultiLanguageListener() {
		plugin = MultiLanguage.plugin;
	}
	
	public SettingsManager getSettingsManager() {
		return MultiLanguage.plugin.getSettingsManager();
	}
	
	public LanguageManager getLanguageManager() {
		return MultiLanguage.plugin.getLanguageManager();
	}
	
	public ChannelManager getChannelManager() {
		return MultiLanguage.plugin.getChannelManager();
	}
	
	public void sendMessage(String msg, Player player) {
		Localisation localisation;
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			localisation = new Localisation(getLanguageManager().getPlayerLanguage(p));
			localisation.sendMessage(player, msg);
		}
	}
	
	public void sendChannelMessage(String msg, Player sender, Language language) {
		for(String player : getChannelManager().getChannelPlayers(language)) {
			if(sender == null)
				Bukkit.getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "] " + ChatColor.YELLOW + msg);
			else
				Bukkit.getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "]" + ChatColor.GOLD + "[" + sender.getName() + "] " + ChatColor.YELLOW + msg);
		}
	}
}
