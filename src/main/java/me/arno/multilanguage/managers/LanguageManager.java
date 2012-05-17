package me.arno.multilanguage.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.arno.multilanguage.Language;
import me.arno.multilanguage.MultiLanguage;

public class LanguageManager {
	private Logger log;
	private HashMap<String, Language> playerLanguages = new HashMap<String, Language>();
	private static File languageFile = new File(MultiLanguage.plugin.getDataFolder() + File.separator + "languages.txt");
	private static File languageFolder = new File(MultiLanguage.plugin.getDataFolder() + File.separator + "languages");
	
	public LanguageManager() {
		log = MultiLanguage.plugin.log;
	}
	
	public void loadLanguages() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(languageFile));
			
			String currentLine;
			String[] args;
			
			while((currentLine = in.readLine()) != null) {
				args = currentLine.split(",");
				playerLanguages.put(args[0], Language.valueOf(args[1]));
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveLanguages() {
		try {
			FileWriter fileWriter = new FileWriter(languageFile);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			for(Entry<String, Language> playerLang : playerLanguages.entrySet()) {
				writer.write(playerLang.getKey() + "," + playerLang.getValue().name() + System.getProperty("line.separator"));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prepareLanguages() {
		if(!languageFolder.exists())
			languageFolder.mkdirs();
		
		for(Language lang : Language.values()) {
			String language = lang.name().toLowerCase() + ".yml";
			try {
				File file = new File(languageFolder, language);
				if(!file.exists()) {
					FileConfiguration config = YamlConfiguration.loadConfiguration(file);
					FileConfiguration ymlConfig = YamlConfiguration.loadConfiguration(MultiLanguage.plugin.getResource("resources/" + language));
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
}
