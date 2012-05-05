package me.plugin.multilanguage.listeners;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.Localisation;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener extends MultiLanguageListener {
	public PlayerListener(MultiLanguage plugin) {
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(!plugin.playerLanguages.containsKey(player.getName()))
			plugin.playerLanguages.put(player.getName(), Language.getLanguage(plugin.getConfig().getString("languages.default")));
		
		Language lang = Language.getLanguage(plugin.getPlayerLanguage(player));
		
		Localisation localisation = new Localisation(lang);
		player.sendMessage(localisation.getMessage("message.language", player));
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Language lang = Language.getLanguage(plugin.getPlayerLanguage(player));
		
		plugin.playerChannels.remove(player.getName());
		plugin.channels.get(lang).remove(player.getName());
	}
}
