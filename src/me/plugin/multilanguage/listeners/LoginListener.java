package me.plugin.multilanguage.listeners;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.ChatColor;
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
	    
	    Language playerLanguage = plugin.playerLanguages.get(player.getName());
	    
	    player.sendMessage(ChatColor.GOLD + "Welcome " + player.getName() + ". Your concurrent language is " + playerLanguage.getFullName());
	}
}
