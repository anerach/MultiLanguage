package me.plugin.multilanguage.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import me.plugin.multilanguage.Language;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MultiLanguageAPI {
	private MultiLanguage plugin;
	private File localisationDirectory;
	private File localisationFile;
	private FileConfiguration languageConfig;
	private File defaultLanguage;
	private HashMap<String, File> languages = new HashMap<String, File>();
	
	public MultiLanguageAPI(String externPlugin) {
		this.plugin = (MultiLanguage) Bukkit.getServer().getPluginManager().getPlugin("MultiLanguage");
		this.localisationDirectory = new File(plugin.getDataFolder() + "/plugins/" + externPlugin);
		this.defaultLanguage = new File(localisationDirectory, "english.yml");
		
		if(!localisationDirectory.exists())
			localisationDirectory.mkdirs();
	}
	
	public void addLanguage(Language language, InputStream resource) {
		addLanguage(language, resource, false);
	}
	
	public void addLanguage(Language language, InputStream resource, boolean defaultLang) {
		File langFile = new File(localisationDirectory, language.name().toLowerCase() + ".yml");
		FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);
		YamlConfiguration langYmlConfig = YamlConfiguration.loadConfiguration(resource);
		
		langConfig.setDefaults(langYmlConfig);
		langConfig.options().copyDefaults(true);
		
		try {
			langConfig.save(langFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		languages.put(language.name().toLowerCase(), langFile);
		
		if(defaultLang)
			defaultLanguage = langFile;
	}
	
	public void loadPlayerLanguage(Player player) {
		if(languages.containsKey(plugin.getPlayerLanguage(player))) {
			this.localisationFile = languages.get(plugin.getPlayerLanguage(player));
			this.languageConfig = YamlConfiguration.loadConfiguration(localisationFile);
		} else
			this.languageConfig = YamlConfiguration.loadConfiguration(defaultLanguage);
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
		}
		
		return msg;
	}
}
