package me.arno.multilanguage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LocalisationAPI {
	private final MultiLanguage multiLanguage;
	private final File localisationDirectory;
	
	private Language defaultLanguage;
	private HashMap<String, File> languageFiles = new HashMap<String, File>();
	
	public LocalisationAPI(Plugin plugin) {
		this(plugin.getName());
	}
	
	public LocalisationAPI(String plugin) {
		this.multiLanguage = MultiLanguage.plugin;
		this.localisationDirectory = new File(MultiLanguage.plugin.getDataFolder() + File.separator + "plugins" + File.separator + plugin);
		if(!localisationDirectory.exists())
			localisationDirectory.mkdirs();
	}
	
	/**
	 * Adds a new translation for your plugin
	 * 
	 * @param language The language
	 * @param resource The yaml file as an {@link InputStream}
	 * @throws IOException
	 */
	public void addLanguage(Language language, InputStream resource) throws IOException {
		addLanguage(language, resource, false);
	}
	
	/**
	 * Adds a new translation for your plugin
	 * 
	 * @param language The language
	 * @param resource The yaml file as an {@link InputStream}
	 * @param defaultLanguage True to set this as the default language for your plugin
	 * @throws IOException
	 */
	public void addLanguage(Language language, InputStream resource, boolean defaultLanguage) throws IOException {
		File langFile = new File(localisationDirectory + File.separator + language.name().toLowerCase() + ".yml");
		
		FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(resource);
		
		langConfig.setDefaults(defaultConfig);
		langConfig.options().copyDefaults(true);
		
		langConfig.save(langFile);
		
		languageFiles.put(language.name().toLowerCase(), langFile);
		
		if(defaultLanguage)
			this.defaultLanguage = language;
	}
	
	/**
	 * Send a message to everyone online in their selected language
	 * 
	 * @param message Message that everyone should receive
	 */
	public void sendGlobalMessage(String message) {
		sendGlobalMessage(message, null);
	}
	
	/**
	 * Send a message to everyone online in their selected language
	 * 
	 * @param message Message that everyone should receive
	 * @param args Extra arguments that can be added to the message
	 */
	public void sendGlobalMessage(String message, HashMap<String, String> args) {
		for(Player receiver : Bukkit.getOnlinePlayers()) {
			receiver.sendMessage(getMessage(message, receiver, args));
		}
	}
	
	/**
	 * Send a message to a player in their selected language
	 * 
	 * @param sender Player that receives the message
	 * @param message Message that the player receives
	 */
	public void sendMessage(CommandSender receiver, String message) {
		sendMessage(receiver, message, null);
	}
	
	/**
	 * Send a message to a player in their selected language
	 * 
	 * @param sender Player that receives the message
	 * @param message Message that the player receives
	 * @param args Extra arguments that can be added to the message
	 */
	public void sendMessage(CommandSender receiver, String message, HashMap<String, String> args) {
		receiver.sendMessage(getMessage(message, receiver, args));
	}
	
	private String getMessage(String message, CommandSender receiver, HashMap<String, String> args) {
		Language language = multiLanguage.getLanguageManager().getPlayerLanguage(receiver);
		File langFile = languageFiles.get(defaultLanguage);
		
		if(languageFiles.containsKey(language.getName().toLowerCase()))
			langFile = languageFiles.get(language.getName().toLowerCase());
		
		String msg = YamlConfiguration.loadConfiguration(langFile).getString(message);

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
		
		if(receiver != null) {
			msg = msg.replaceAll("\\{player}", receiver.getName());
			msg = msg.replaceAll("\\{language}", language.getName());
			
			if(receiver instanceof Player) {
				Player player = (Player) receiver;
				msg = msg.replaceAll("\\{health}", Integer.toString(player.getHealth()));
				msg = msg.replaceAll("\\{hunger}", Integer.toString(player.getFoodLevel()));
				msg = msg.replaceAll("\\{level}", Integer.toString(player.getLevel()));
				msg = msg.replaceAll("\\{exp}", Integer.toString(player.getTotalExperience()));
				if(player.getKiller() != null)
					msg = msg.replaceAll("\\{killer}", player.getKiller().getName());
			}
		}
		return msg;
	}
}
