package me.plugin.multilanguage;

public enum Language {
	ENGLISH("English", "eng"),
	DUTCH("Nederlands", "nl"),
	SPANISH("Espanol", "es"),
	DANISH("Dansk", "dk"),
	POLISH("Polski", "pl"),
	ITALIAN("Italiano", "it");
	
	String shortName;
	String name;
	Language(String name, String shortName) {
		this.shortName = shortName;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public static Language getLanguage(String str) {
		if(str.equalsIgnoreCase("english") || str.equalsIgnoreCase("eng"))
			return Language.ENGLISH;
		else if(str.equalsIgnoreCase("dutch") || str.equalsIgnoreCase("nederlands") || str.equalsIgnoreCase("nl"))
			return Language.DUTCH;
		else if(str.equalsIgnoreCase("spanish") || str.equalsIgnoreCase("espanol") || str.equalsIgnoreCase("es"))
			return Language.SPANISH;
		else if(str.equalsIgnoreCase("danish") || str.equalsIgnoreCase("dansk") || str.equalsIgnoreCase("es"))
			return Language.DANISH;
		else if(str.equalsIgnoreCase("polish") || str.equalsIgnoreCase("polski") || str.equalsIgnoreCase("pl"))
			return Language.POLISH;
		else if(str.equalsIgnoreCase("italian") || str.equalsIgnoreCase("italiano") || str.equalsIgnoreCase("it"))
			return Language.ITALIAN;
		
		return null;
	}
}
