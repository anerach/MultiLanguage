package me.arno.multilanguage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import me.arno.multilanguage.commands.*;
import me.arno.multilanguage.listener.*;
import me.arno.multilanguage.managers.*;
import me.arno.multilanguage.schedules.Updates;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiLanguage extends JavaPlugin {
	public static MultiLanguage plugin;
	public Logger log;

	private SettingsManager settingsManager;
	private LanguageManager languageManager;
	private ChannelManager channelManager;
	
	public String newVersion;
	public String currentVersion;
	public double doubleNewVersion;
	public double doubleCurrentVersion;
	
	public LanguageManager getLanguageManager() {
		return languageManager;
	}
	
	public SettingsManager getSettingsManager() {
		return settingsManager;
	}
	
	public ChannelManager getChannelManager() {
		return channelManager;
	}
	
	private void loadConfiguration() {
		getConfig().addDefault("default-language", Language.ENGLISH.name());
		getConfig().addDefault("check-for-updates", true);
		getConfig().addDefault("channels-enabled", true);
		getConfig().addDefault("translations.login", true);
		getConfig().addDefault("translations.logout", true);
		getConfig().addDefault("translations.deaths.pvp", true);
		getConfig().addDefault("translations.deaths.natural", true);
		getConfig().addDefault("translations.deaths.monsters", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void loadMetrics() {
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    log.warning("Failed to start the metrics");
		}
	}
	
	public void loadPlugin() {
		plugin = this;
		log = getLogger();
		
		settingsManager = new SettingsManager();
		languageManager = new LanguageManager();
		channelManager = new ChannelManager();
		
		log.info("Loading configuration");
		loadConfiguration();
		
		log.info("Loading the language files");
		languageManager.prepareLanguages();
		
		log.info("Loading player language settings");
		languageManager.loadLanguages();
		
		log.info("Starting the plugin");
		loadMetrics();
		
		getServer().getPluginManager().registerEvents(new ChannelListener(), this);
		getServer().getPluginManager().registerEvents(new TranslationListener(), this);
		
		if(getSettingsManager().isUpdateCheckingEnabled()) {
	    	getServer().getScheduler().scheduleSyncRepeatingTask(this, new Updates(), 1L, 1 * 60 * 60 * 20L); // Check every hour for a new version
	    	getServer().getPluginManager().registerEvents(new NoticeListener(), this);
	    }
	}

	@Override
	public void onEnable() {
		loadPlugin();
		PluginDescriptionFile PluginDesc = getDescription();
		log.info("v" + PluginDesc.getVersion() + " is enabled!");
	}
	
	@Override
	public void onDisable() {
		languageManager.saveLanguages();
		PluginDescriptionFile PluginDesc = getDescription();
		log.info("v" + PluginDesc.getVersion() + " is disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	Player player = null;
    	
		if (sender instanceof Player)
			player = (Player) sender;
		
		if(!cmd.getName().equalsIgnoreCase("multilanguage"))
			return false;
		
		if (player == null) {
			sender.sendMessage("This command can only be run by a player");
			return true;
		}
		
		if(args.length < 1) {
			player.sendMessage(ChatColor.GRAY + "----- " + ChatColor.DARK_RED + "MultiLanguage" + ChatColor.GRAY + " -----");
			player.sendMessage(ChatColor.GOLD + "This server is using MultiLanguage v" + getDescription().getVersion() + " by Anerach");
			player.sendMessage(ChatColor.GOLD + "Say " + ChatColor.BLUE + "/ml help" + ChatColor.GOLD + " for help");
			return true;
		}
		
		ArrayList<String> argList = new ArrayList<String>();
		
		if(args.length > 1) {
			for(int i=1;i<args.length;i++) {
				argList.add(args[i]);
			}
		}
		
		String[] newArgs = argList.toArray(new String[]{});
		
		MultiLanguageCommand command = new MultiLanguageCommand();
		
		if(args[0].equalsIgnoreCase("set")) {
			command = new CommandSet();
		} else if(args[0].equalsIgnoreCase("list")) {
			command = new CommandList();
		} else if(args[0].equalsIgnoreCase("help")) {
			command = new CommandHelp();
		} else if(args[0].equalsIgnoreCase("ch") || args[0].equalsIgnoreCase("channel")) {
			command = new CommandChannel();
		}
		
		if(!command.hasPermission(player)) {
			player.sendMessage("You don't have the permission for this command");
			return true;
		}
		
		cmd.setUsage(command.getCommandUsage());
		return command.execute(player, cmd, newArgs);
    }
}
