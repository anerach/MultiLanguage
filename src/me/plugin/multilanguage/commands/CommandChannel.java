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
			player.sendMessage(ChatColor.BLUE + localisation.getMessage("channel.join"));
		} else {
			plugin.playerChannels.remove(player.getName());
			plugin.channels.get(lang).remove(player.getName());
			player.sendMessage(ChatColor.BLUE + localisation.getMessage("channel.leave"));
		}
		return true;
	}
}
