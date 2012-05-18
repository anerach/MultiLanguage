package me.arno.multilanguage.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;

public class ChannelListener extends MultiLanguageListener {
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(getChannelManager().isPlayerInChannel(player)) {
			sendChannelMessage(event.getMessage(), player, getChannelManager().getPlayerChannel(player));
			event.setCancelled(true);
		}
	}
}
