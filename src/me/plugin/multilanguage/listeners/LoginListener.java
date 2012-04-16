package me.plugin.multilanguage.listeners;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.Localisation;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
	MultiLanguage plugin;
	
	public LoginListener(MultiLanguage plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(!plugin.playerLanguages.containsKey(player.getName()))
			plugin.playerLanguages.put(player.getName(), Language.getLanguage(plugin.getConfig().getString("languages.default")));
		    
		Localisation localisation = new Localisation(plugin, plugin.playerLanguages.get(player.getName()));
		player.sendMessage(localisation.getMessage("message.language", player));
	}
}
