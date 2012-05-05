package me.plugin.multilanguage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import me.plugin.multilanguage.commands.CommandChannel;
import me.plugin.multilanguage.commands.CommandHelp;
import me.plugin.multilanguage.commands.CommandList;
import me.plugin.multilanguage.commands.CommandSet;
import me.plugin.multilanguage.listeners.ChannelListener;
import me.plugin.multilanguage.listeners.LanguageListener;
import me.plugin.multilanguage.listeners.PlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MultiLanguage extends JavaPlugin {
	public static MultiLanguage plugin;
	public Logger log;
	public HashMap<String, Language> playerLanguages = new HashMap<String, Language>();
	public HashMap<Language, ArrayList<String>> channels = new HashMap<Language, ArrayList<String>>();
	public HashMap<String, Language> playerChannels = new HashMap<String, Language>();
	private File languageFile;
	
	public String newVersion;
	public String currentVersion;
	public double doubleNewVersion;
	public double doubleCurrentVersion;
	
	public String loadLatestVersion(String currentVersion) {
        String pluginUrlString = "http://dev.bukkit.org/server-mods/multilanguage/files.rss";
        try {
            URL url = new URL(pluginUrlString);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openConnection().getInputStream());
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("item");
            Node firstNode = nodes.item(0);
            if (firstNode.getNodeType() == 1) {
                Element firstElement = (Element) firstNode;
                NodeList firstElementTagName = firstElement.getElementsByTagName("title");
                Element firstNameElement = (Element) firstElementTagName.item(0);
                NodeList firstNodes = firstNameElement.getChildNodes();
                return firstNodes.item(0).getNodeValue().replace("MultiLanguage", "").trim();
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return currentVersion;
    }
	
	public String getPlayerLanguage(Player player) {
		return playerLanguages.get(player.getName()).name().toLowerCase();
	}
	
	public void loadConfig() {
		getConfig().addDefault("languages.enabled", true);
		getConfig().addDefault("languages.default", "english");
		getConfig().addDefault("messages.login", true);
		getConfig().addDefault("messages.logout", true);
		getConfig().addDefault("messages.deaths.pvp", true);
		getConfig().addDefault("messages.deaths.natural", true);
		getConfig().addDefault("messages.deaths.monsters", true);
	    getConfig().options().copyDefaults(true);
	    saveConfig();	    
	}
	
	public void updateLanguages() {
		Integer currentGlobalVersion = 2;
		Config versions = new Config("VERSIONS");
		versions.getConfig().addDefault("languages.global", currentGlobalVersion);
		versions.getConfig().addDefault("languages.danish", 1);
		versions.getConfig().addDefault("languages.dutch", 1);
		versions.getConfig().addDefault("languages.english", 1);
		versions.getConfig().addDefault("languages.german", 1);
		versions.getConfig().addDefault("languages.italian", 1);
		versions.getConfig().addDefault("languages.lithuanian", 1);
		versions.getConfig().addDefault("languages.norwegian", 1);
		versions.getConfig().addDefault("languages.polish", 1);
		versions.getConfig().addDefault("languages.portuguese", 1);
		versions.getConfig().addDefault("languages.spanish", 1);
		versions.getConfig().options().copyDefaults(true);
		
		if(versions.getConfig().getInt("languages.global") < currentGlobalVersion) {
			for(Language lang : Language.values()) {
				File file = new File(Config.configDir + "/languages/" + lang.name().toLowerCase() + ".yml");
				if(file.exists())
					file.delete();
			}
			versions.getConfig().set("languages.global", currentGlobalVersion);
		}
		versions.saveConfig();
	}
	
	public void prepareLanguages() {
		File languageFiles = new File(getDataFolder() + "/languages");
		if(!languageFiles.exists())
			languageFiles.mkdirs();
		
		for(Language lang : Language.values()) {
			channels.put(lang, new ArrayList<String>());
			String language = lang.name().toLowerCase() + ".yml";
			try {
				File file = new File(languageFiles, language);
				if(!file.exists()) {
					FileConfiguration config = YamlConfiguration.loadConfiguration(file);
					FileConfiguration ymlConfig = YamlConfiguration.loadConfiguration(getResource("resources/" + language));
					config.setDefaults(ymlConfig);
					config.options().copyDefaults(true);
					config.save(file);
				}
			} catch (IOException e) {
		        e.printStackTrace();
		    	log.severe("Unable to load language: " + language);
			} catch (Exception e) {
		        e.printStackTrace();
		    	log.severe("Unable to load language: " + language);
		    }
		}
	}
	
	public void saveLanguages() {
		try {
			ObjectOutputStream ObjSaveStream = new ObjectOutputStream(new FileOutputStream(languageFile));
			ObjSaveStream.writeObject(playerLanguages);
			ObjSaveStream.flush();
			ObjSaveStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadLanguages() {
		languageFile = new File(getDataFolder(), "languages.dat");
		try {
			if(!getDataFolder().exists())
				getDataFolder().mkdirs();
			
			if(languageFile.exists()) {
	    		ObjectInputStream ObjLoadStream = new ObjectInputStream(new FileInputStream(languageFile));
	    		Object Result = ObjLoadStream.readObject();

	    		playerLanguages = (HashMap<String, Language>) Result;
			}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public void loadPlugin() {
		log = getLogger();
		plugin = this;
		
		log.info("Loading config");
		loadConfig();
		
		log.info("Updating languages");
		updateLanguages();
		
		log.info("Loading languages");
		prepareLanguages();
		
		log.info("Loading player languages");
		loadLanguages();
		
		log.info("Checking for updates");
		currentVersion = getDescription().getVersion();
		newVersion = loadLatestVersion(currentVersion);
		
		doubleCurrentVersion = Double.valueOf(currentVersion.replaceFirst("\\.", ""));
		doubleNewVersion = Double.valueOf(newVersion.replaceFirst("\\.", ""));
		
		if(doubleNewVersion > doubleCurrentVersion) {
			log.warning("MultiLanguage v" + newVersion + " is released! You're using MultiLanguage v" + currentVersion);
			log.warning("Update LanguageCraft at http://dev.bukkit.org/server-mods/multilanguage/");
		}
		
		log.info("Starting MultiLanguage");

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new ChannelListener(this), this);
		
		if(getConfig().getBoolean("languages.enabled"))
			pm.registerEvents(new LanguageListener(this), this);
	}
	
	@Override
	public void onEnable() {
		loadPlugin();
		PluginDescriptionFile PluginDesc = getDescription();
		log.info("v" + PluginDesc.getVersion() + " is enabled!");
	}
	
	@Override
	public void onDisable() {
		log.info("Saving player languages");
		saveLanguages();
		
		PluginDescriptionFile PluginDesc = getDescription();
		log.info("v" + PluginDesc.getVersion() + " is disabled!");
	}
	
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
			player.sendMessage(ChatColor.DARK_RED + "-~= MultiLanguage =~-");
			player.sendMessage(ChatColor.GOLD + "This server is using MultiLanguage v" + getDescription().getVersion() + " by Anerach");
			player.sendMessage(ChatColor.GOLD + "Say " + ChatColor.BLUE + "/ml help" + ChatColor.GOLD + " for help");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("set")) {
			CommandSet cmdSet = new CommandSet(this);
			return cmdSet.execute(player, cmd, args);
		} else if(args[0].equalsIgnoreCase("list")) {
			CommandList cmdList = new CommandList(this);
			return cmdList.execute(player, cmd, args);
		} else if(args[0].equalsIgnoreCase("help")) {
			CommandHelp cmdHelp = new CommandHelp(this);
			return cmdHelp.execute(player, cmd, args);
		} else if(args[0].equalsIgnoreCase("ch") || args[0].equalsIgnoreCase("channel")) {
			CommandChannel cmdChannel = new CommandChannel(this);
			return cmdChannel.execute(player, cmd, args);
		}
		
		return false;
    }
}
