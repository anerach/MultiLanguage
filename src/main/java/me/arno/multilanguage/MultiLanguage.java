package me.arno.multilanguage;

import java.io.IOException;
import java.util.logging.Logger;

import me.arno.multilanguage.managers.LanguageManager;
import me.arno.multilanguage.managers.SettingsManager;
import me.arno.multilanguage.schedules.Updates;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiLanguage extends JavaPlugin {
	public static MultiLanguage plugin;
	public Logger log;
	
	private LanguageManager languageManager;
	private SettingsManager settingsManager;
	
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
		
		languageManager = new LanguageManager();
		
		log.info("Loading configuration");
		loadConfiguration();
		
		log.info("Loading the language files");
		languageManager.prepareLanguages();
		
		log.info("Loading player language settings");
		languageManager.loadLanguages();
		
		log.info("Starting the plugin");
		loadMetrics();
		//getServer().getPluginManager().registerEvents(new LoginListener(), this);
		//getServer().getPluginManager().registerEvents(new LogoutListener(), this);
		//getServer().getPluginManager().registerEvents(new KickListener(), this);
		//getServer().getPluginManager().registerEvents(new DeathListener(), this);
		
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
}
