package me.arno.multilanguage.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChannelListener extends MultiLanguageListener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(getChannelManager().isPlayerInChannel(player)) {
			sendChannelMessage(event.getMessage(), player, getChannelManager().getPlayerChannel(player));
			event.setCancelled(true);
		}
	}
}
