package me.plugin.multilanguage;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Localisation {
	private FileConfiguration languageConfig;
	
	public Localisation(MultiLanguage plugin, Language language) {
		String lang = language.getFullName().toLowerCase();
		File languageFile = new File(plugin.getDataFolder() + "/languages", lang + ".yml");
		languageConfig = YamlConfiguration.loadConfiguration(languageFile);
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
		String msg =  languageConfig.getString(message);
		
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
			Set<Entry<String, String>> argSet = args.entrySet();
			for (Entry<String, String> arg : argSet) {
		    	msg = msg.replaceAll("\\{" + arg.getKey() + "}", arg.getValue());
		    }
		}
		
		if(player != null) {
			msg = msg.replaceAll("\\{player}", player.getName());
			msg = msg.replaceAll("\\{level}", Integer.toString(player.getLevel()));
			msg = msg.replaceAll("\\{exp}", Integer.toString(player.getTotalExperience()));
			if(player.getKiller() != null)
				msg = msg.replaceAll("\\{killer}", player.getKiller().getName());
		}
		return msg;
	}
}
