package me.arno.multilanguage.commands;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.arno.multilanguage.Language;
import me.arno.multilanguage.MultiLanguage;
import me.arno.multilanguage.managers.ChannelManager;
import me.arno.multilanguage.managers.LanguageManager;
import me.arno.multilanguage.managers.SettingsManager;

public class MultiLanguageCommand {
	public final MultiLanguage plugin;
	public final Logger log;
	
	public final String permission;
	public final Boolean console;
	
	public String usage;
	
	public MultiLanguageCommand() {
		this(null, false);
	}
	
	public MultiLanguageCommand(boolean console) {
		this(null, console);
	}
	
	public MultiLanguageCommand(String permission) {
		this(permission, false);
	}
	
	public MultiLanguageCommand(String permission, boolean console) {
		this.plugin = MultiLanguage.plugin;
		this.log = plugin.log;
		this.permission = permission;
		this.console = console;
	}
	
	public LanguageManager getLanguageManager() {
		return plugin.getLanguageManager();
	}
	
	public SettingsManager getSettingsManager() {
		return plugin.getSettingsManager();
	}
	
	public ChannelManager getChannelManager() {
		return plugin.getChannelManager();
	}
	
	public Boolean hasPermission(Player player) {
		if(player == null && console)
			return true;
		else if(player == null && console == false)
			return false;
		else if(permission != null && player != null)
			return player.hasPermission(permission);
		else if(player != null)
			return player.isOp();
		
		return false;
	}
	
	public void sendChannelMessage(String msg, Player sender, Language language) {
		for(String player : getChannelManager().getChannelPlayers(language)) {
			if(sender == null)
				Bukkit.getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "] " + ChatColor.YELLOW + msg);
			else
				Bukkit.getPlayer(player).sendMessage(ChatColor.DARK_RED + "[" + language.getName() + "]" + ChatColor.GOLD + "[" + sender.getName() + "] " + ChatColor.YELLOW + msg);
		}
	}
	
	public void setCommandUsage(String usage) {
		this.usage = usage;
	}
	
	public String getCommandUsage() {
		return usage;
	}
	
	public boolean execute(Player player, Command cmd, String[] args) {
		player.sendMessage("This command doesn't exists. Say " + ChatColor.GOLD + "/ml help " + ChatColor.WHITE + "for a list of available commands.");
		return true;
	}
}
