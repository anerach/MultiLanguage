package me.arno.multilanguage.managers;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import me.arno.multilanguage.Language;

public class ChannelManager {
	private HashMap<Language, List<String>> channels = new HashMap<Language, List<String>>();
	private HashMap<String, Language> playerChannels = new HashMap<String, Language>();
	
	public List<String> getChannelPlayers(Language language) {
		return channels.get(language);
	}
	
	public void addChannelPlayer(Player player, Language language) {
		addChannelPlayer(player.getName(), language);
	}
	
	public void addChannelPlayer(String player, Language language) {
		List<String> players = getChannelPlayers(language);
		players.add(player);
		
		channels.put(language, players);
		playerChannels.put(player, language);
	}
	
	public void removePlayerFromChannel(Player player, Language language) {
		removePlayerFromChannel(player.getName(), language);
	}
	
	public void removePlayerFromChannel(String player, Language language) {
		List<String> players = getChannelPlayers(language);
		players.remove(player);
		
		channels.put(language, players);
		playerChannels.remove(player);
	}
	
	public Language getPlayerChannel(Player player) {
		return getPlayerChannel(player.getName());
	}
	
	public Language getPlayerChannel(String player) {
		return playerChannels.get(player);
	}
	
	public boolean isPlayerInChannel(Player player) {
		return isPlayerInChannel(player.getName());
	}
	
	public boolean isPlayerInChannel(String player) {
		return playerChannels.containsKey(player);
	}
	
	public boolean isPlayerInChannel(Player player, Language language) {
		return isPlayerInChannel(player.getName(), language);
	}
	
	public boolean isPlayerInChannel(String player, Language language) {
		return channels.get(language).contains(player);
	}
}
