package me.arno.multilanguage.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandHelp extends MultiLanguageCommand {
	
	public CommandHelp() {
		setCommandUsage("/ml help");
	}
	
	@Override
	public boolean execute(Player player, Command cmd, String[] args) {
		if(args.length > 0)
			return false;
		
		player.sendMessage(ChatColor.DARK_RED + "MultiLanguage Commands" + ChatColor.GRAY + "--------------------------");
		player.sendMessage(ChatColor.GOLD + addSpaces("/ml help", 110) + ChatColor.BLUE + "Shows information about the commands");
		player.sendMessage(ChatColor.GOLD + addSpaces("/ml set <language>", 110) + ChatColor.BLUE + "Changes your language");
		player.sendMessage(ChatColor.GOLD + addSpaces("/ml list", 110) + ChatColor.BLUE + "Shows a list of all the available languages");
		player.sendMessage(ChatColor.GOLD + addSpaces("/ml channel", 110) + ChatColor.BLUE + "Joins your language's channel");
		return true;
	}
	
	public String addSpaces(String message, int totalLength) {
		int spaces = Math.round((totalLength - wordLength(message)) / charLength(' '));
		
		for(int i=0;i<spaces;i++)
			message += " ";
		return message;
	}
	
	public int wordLength(String str) {
		int length = 0;
		for(char c : str.toCharArray()) {
			length += charLength(c);
		}
		return length;
	}
	
	public int charLength(char c) {
        if (new String("i.:,;|!").indexOf(c) != -1)
        	return 2;
        else if (new String("l ").indexOf(c) != -1)
        	return 3;
        else if (new String("tI[]").indexOf(c) != -1)
        	return 4;
        else if (new String("fk{}<>\"*()").indexOf(c) != -1)
        	return 5;
        else if (new String("abcdeghjmnopqrsuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ1234567890\\/#?$%-=_+&^").indexOf(c) != -1)
        	return 7;
        else if (new String("@~").indexOf(c) != -1)
        	return 7;
        else if (c == ' ')
        	return 3;
        else
        	return -1;
    }
}
