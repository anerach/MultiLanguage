package me.arno.multilanguage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import me.arno.multilanguage.listener.MultiLanguageListener;

public class NoticeListener extends MultiLanguageListener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if ((player.isOp() || player.hasPermission("multilanguage.notices")) && (getDoubleLatestVersion() > getDoubleCurrentVersion())) {
			player.sendMessage(ChatColor.BLUE + "BlockLog " + ChatColor.GOLD + "v" + getLatestVersion() + ChatColor.BLUE + " is released!");
			player.sendMessage(ChatColor.BLUE + "You're using MultiLanguage " + ChatColor.GOLD + "v" + getCurrentVersion());
		}
	}
	

	
	public double getDoubleLatestVersion() {
		return plugin.doubleNewVersion;
	}
	
	public String getLatestVersion() {
		return plugin.newVersion;
	}
	
	public double getDoubleCurrentVersion() {
		return plugin.doubleCurrentVersion;
	}
	
	public String getCurrentVersion() {
		return plugin.currentVersion;
	}
}
