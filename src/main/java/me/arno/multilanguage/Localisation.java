package me.arno.multilanguage;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Localisation {
	private final FileConfiguration languageConfig;
	private final File languageFile;
	private final Language language;
	private final String lang;
	
	public Localisation(Language language) {
		this.language = language;
		this.lang = language.name().toLowerCase();
		this.languageFile = new File(MultiLanguage.plugin.getDataFolder() + File.separator + "languages" + File.separator + lang + ".yml");
		
		if(languageFile.exists()) {
			languageConfig = YamlConfiguration.loadConfiguration(languageFile);
		} else {
			MultiLanguage.plugin.log.severe("Unable to load language file: " + lang);
			languageConfig = null;
		}
	}
	
	public void sendMessage(Player receiver, String message) {
		sendMessage(receiver, receiver, message, null);
	}
	
	public void sendMessage(Player receiver, Player player, String message) {
		sendMessage(receiver, player, message, null);
	}
	
	public void sendMessage(Player receiver, Player player, String message, HashMap<String, String> args) {
		receiver.sendMessage(getMessage(message, player, args));
	}
	
	public String getMessage(String message) {
		return getMessage(message, null, null);
	}
	
	public String getMessage(String message, Player player) {
		return getMessage(message, player, null);
	}
	
	public String getMessage(String message, HashMap<String, String> args) {
		return getMessage(message, null, args);
	}
	
	public String getMessage(String message, Player player, HashMap<String, String> args) {
		String msg = languageConfig.getString(message);
		
		msg = msg.replaceAll("&0", ChatColor.BLACK.toString());
		msg = msg.replaceAll("&1", ChatColor.DARK_BLUE.toString());
		msg = msg.replaceAll("&2", ChatColor.DARK_GREEN.toString());
		msg = msg.replaceAll("&3", ChatColor.DARK_AQUA.toString());
		msg = msg.replaceAll("&4", ChatColor.DARK_RED.toString());
		msg = msg.replaceAll("&5", ChatColor.DARK_PURPLE.toString());
		msg = msg.replaceAll("&6", ChatColor.GOLD.toString());
		msg = msg.replaceAll("&7", ChatColor.GRAY.toString());
		msg = msg.replaceAll("&8", ChatColor.DARK_GRAY.toString());
		msg = msg.replaceAll("&9", ChatColor.BLUE.toString());
		msg = msg.replaceAll("&a", ChatColor.GREEN.toString());
		msg = msg.replaceAll("&b", ChatColor.AQUA.toString());
		msg = msg.replaceAll("&c", ChatColor.RED.toString());
		msg = msg.replaceAll("&d", ChatColor.LIGHT_PURPLE.toString());
		msg = msg.replaceAll("&e", ChatColor.YELLOW.toString());
		msg = msg.replaceAll("&f", ChatColor.WHITE.toString());
		msg = msg.replaceAll("&k", ChatColor.MAGIC.toString());
		msg = msg.replaceAll("&l", ChatColor.BOLD.toString());
		msg = msg.replaceAll("&m", ChatColor.STRIKETHROUGH.toString());
		msg = msg.replaceAll("&n", ChatColor.UNDERLINE.toString());
		msg = msg.replaceAll("&o", ChatColor.ITALIC.toString());
		msg = msg.replaceAll("&r", ChatColor.RESET.toString());
		
		if(args != null) {
			for (Entry<String, String> arg : args.entrySet()) {
		    	msg = msg.replaceAll("\\{" + arg.getKey() + "}", arg.getValue());
		    }
		}
		
		if(player != null) {
			msg = msg.replaceAll("\\{player}", player.getName());
			msg = msg.replaceAll("\\{level}", Integer.toString(player.getLevel()));
			msg = msg.replaceAll("\\{exp}", Integer.toString(player.getTotalExperience()));
			msg = msg.replaceAll("\\{language}", language.getName());
			if(player.getKiller() != null)
				msg = msg.replaceAll("\\{killer}", player.getKiller().getName());
		}
		return msg;
	}
}
