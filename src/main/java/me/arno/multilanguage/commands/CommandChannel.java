package me.arno.multilanguage.commands;

import java.util.List;

import me.arno.multilanguage.Language;
import me.arno.multilanguage.Localisation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandChannel extends MultiLanguageCommand {
	public CommandChannel() {
		setCommandUsage("/ml channel");
	}
	
	@Override
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length > 0)
			return false;
		Language language = getLanguageManager().getPlayerLanguage(player);
		Localisation localisation = new Localisation(language);
		
		if(!getChannelManager().isPlayerInChannel(player)) {
			getChannelManager().addChannelPlayer(player, language);
		
			Localisation.sendMessage(player, "channel.toggle-on");
			sendChannelMessage(localisation.getMessage("channel.join", player), null, language);
			
			List<String> players = getChannelManager().getChannelPlayers(language);
			String playerList = "";
			
			for(int i=0;i<players.size();i++) {
				playerList += players.get(i);
				if(players.size() != i+1)
					playerList += ", ";
			}
			
			player.sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "] " + localisation.getMessage("channel.players"));
			player.sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "] " + ChatColor.GOLD + playerList);
		} else {
			Localisation.sendMessage(player, "channel.toggle-off");
			sendChannelMessage(localisation.getMessage("channel.leave", player), null, language);
			getChannelManager().removePlayerFromChannel(player, language);
		}
		return true;
	}
}
