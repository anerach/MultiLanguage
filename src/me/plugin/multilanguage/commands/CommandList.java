package me.plugin.multilanguage.commands;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandList implements CommandExecutor {
	MultiLanguage plugin;
	
	public CommandList(MultiLanguage plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		
		if (sender instanceof Player)
			player = (Player) sender;
		
		if(!cmd.getName().equalsIgnoreCase("langlist"))
			return false;
		
		if (player == null) {
			sender.sendMessage("This command can only be run by a player");
			return true;
		}
		
		if(args.length != 0)
			return false;
		
		player.sendMessage(ChatColor.DARK_RED + "- Available Languages -");
		for(Language lang : Language.values())
			player.sendMessage(ChatColor.GOLD + lang.name().toLowerCase() + " (" + lang.getExtension().toUpperCase() + ")");
		
		return true;
	}

}
