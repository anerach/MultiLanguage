package me.arno.multilanguage.listener;

import me.arno.multilanguage.Localisation;
import me.arno.multilanguage.MultiLanguage;
import me.arno.multilanguage.managers.LanguageManager;
import me.arno.multilanguage.managers.SettingsManager;

import org.bukkit.Bukkit;
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
	
	public void sendMessage(String msg, Player player) {
		Localisation localisation;
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			localisation = new Localisation(getLanguageManager().getPlayerLanguage(p));
			localisation.sendMessage(player, msg);
		}
	}
}
