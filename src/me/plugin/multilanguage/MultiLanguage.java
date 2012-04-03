package me.plugin.multilanguage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import me.plugin.multilanguage.commands.CommandList;
import me.plugin.multilanguage.commands.CommandSet;
import me.plugin.multilanguage.listeners.LanguageListener;
import me.plugin.multilanguage.listeners.LoginListener;

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
	public Logger log;
	public HashMap<String, Language> playerLanguages = new HashMap<String, Language>();
	
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
		return playerLanguages.get(player.getName()).getFullName();
	}
	
	public void loadConfig() {
		getConfig().addDefault("languages.enabled", true);
		getConfig().addDefault("languages.default", "english");
	    getConfig().options().copyDefaults(true);
	    saveConfig();	    
	}
	
	public void prepareLanguages() {
		File languageFiles = new File(getDataFolder() + "/languages");
		if(!languageFiles.exists())
			languageFiles.mkdirs();
		
		File englishFile = new File(languageFiles, "english.yml");
		File dutchFile = new File(languageFiles, "dutch.yml");
		
		FileConfiguration englishConfig = YamlConfiguration.loadConfiguration(englishFile);
		FileConfiguration dutchConfig = YamlConfiguration.loadConfiguration(dutchFile);
		
		YamlConfiguration englishYmlConfig = YamlConfiguration.loadConfiguration(getResource("resources/english.yml"));
		YamlConfiguration dutchYmlConfig = YamlConfiguration.loadConfiguration(getResource("resources/dutch.yml"));
		
		englishConfig.setDefaults(englishYmlConfig);
		dutchConfig.setDefaults(dutchYmlConfig);
		
		englishConfig.options().copyDefaults(true);
		dutchConfig.options().copyDefaults(true);
		
		try {
			englishConfig.save(englishFile);
			dutchConfig.save(dutchFile);
	    } catch (IOException e) {
	        e.printStackTrace();
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
		
		log.info("Loading config");
		loadConfig();
		
		log.info("Loading languages");
		prepareLanguages();
		
		log.info("Loading player languages");
		loadLanguages();
		
		/* BukkitDev project hasn't been created yet
		log.info("Checking for updates");
		newVersion = loadLatestVersion(currentVersion);
		
		doubleCurrentVersion = Double.valueOf(currentVersion.replaceFirst("\\.", ""));
		doubleNewVersion = Double.valueOf(newVersion.replaceFirst("\\.", ""));
		
		if(doubleNewVersion > doubleCurrentVersion) {
			log.warning("LanguageCraft v" + newVersion + " is released! You're using LanguageCraft v" + currentVersion);
			log.warning("Update LanguageCraft at http://dev.bukkit.org/server-mods/languagecraft/");
		}
		
		*/
		
		log.info("Starting LanguageCraft");
		getCommand("setlang").setExecutor(new CommandSet(this));
		getCommand("langlist").setExecutor(new CommandList(this));

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new LoginListener(this), this);
		
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
		
		if(!cmd.getName().equalsIgnoreCase("language"))
			return false;
		
		if (player == null) {
			sender.sendMessage("This command can only be run by a player");
			return true;
		}
		
		player.sendMessage(ChatColor.GOLD + "This server is using LanguageCraft v" + getDescription().getVersion() + " by Anerach and TheMammoth");
		return true;
    }
}
