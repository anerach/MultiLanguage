package me.plugin.multilanguage.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;

import me.plugin.multilanguage.MultiLanguage;

public class ChannelListener extends MultiLanguageListener {
	public ChannelListener(MultiLanguage plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		if(plugin.playerChannels.containsKey(player.getName())) {
			sendChannelMessage(event.getMessage(), plugin.playerChannels.get(player.getName()));
			event.setCancelled(true);
		}
	}
}
