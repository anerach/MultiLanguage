package me.plugin.multilanguage.commands;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSet implements CommandExecutor {
	MultiLanguage plugin;
	
	public CommandSet(MultiLanguage plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		
		if (sender instanceof Player)
			player = (Player) sender;
		
		if(!cmd.getName().equalsIgnoreCase("setlang"))
			return false;
		
		if (player == null) {
			sender.sendMessage("This command can only be run by a player");
			return true;
		}
		
		if(args.length != 1)
			return false;
		
		Language newLang = Language.getLanguage(args[0]);
		
		if(newLang == null) {
			player.sendMessage("The requested language doesn't exist.");
			return true;
		}
		
		plugin.playerLanguages.put(player.getName(), newLang);
		player.sendMessage("You've changed your language to " + newLang.name().toLowerCase());
		return true;
	}

}
