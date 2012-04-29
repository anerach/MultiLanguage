package me.plugin.multilanguage.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.Localisation;
import me.plugin.multilanguage.MultiLanguage;

public class CommandChannel extends MultiLanguageCommand {
	public CommandChannel(MultiLanguage plugin) {
		super(plugin);
	}
	
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length > 1) {
			player.sendMessage(ChatColor.WHITE + "/ml channel");
			return true;
		}
		
		Language lang = Language.getLanguage(plugin.getPlayerLanguage(player));
		Localisation localisation = new Localisation(plugin, lang);
		
		if(!plugin.playerChannels.containsKey(player.getName())) {
			plugin.playerChannels.put(player.getName(), lang);
			plugin.channels.get(lang).add(player.getName());
			player.sendMessage(localisation.getMessage("channel.toggle-on"));
			sendChannelMessage(localisation.getMessage("channel.join", player), null, lang);
			String players = "";
			String[] cPlayers = plugin.channels.get(lang).toArray(new String[]{});
			
			for(int i=0;i<cPlayers.length;i++)
				players += (cPlayers.length == i+1) ? cPlayers[i] : cPlayers[i] + ", ";
				
			player.sendMessage(ChatColor.DARK_RED + "[" + lang.getName() + "] " + localisation.getMessage("channel.players"));
			player.sendMessage(ChatColor.DARK_RED + "[" + lang.getName() + "] " + ChatColor.GOLD + players);
		} else {
			player.sendMessage(localisation.getMessage("channel.toggle-off"));
			sendChannelMessage(localisation.getMessage("channel.leave", player), null, lang);
			plugin.playerChannels.remove(player.getName());
			plugin.channels.get(lang).remove(player.getName());
		}
		return true;
	}
}
