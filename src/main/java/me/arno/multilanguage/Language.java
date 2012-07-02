package me.arno.multilanguage;

public enum Language {
	ENGLISH("English", "en"),
	DUTCH("Nederlands", "nl"),
	SPANISH("Espanol", "es"),
	DANISH("Dansk", "dk"),
	POLISH("Polski", "pl"),
	ITALIAN("Italiano", "it"),
	LITHUANIAN("Lietuva", "lt"),
	NORWEGIAN("Norsk", "no"),
	GERMAN("Deutsch", "de"),
	PORTUGUESE("Portuguesa", "pt"),
	RUSSIAN("Pусский", "ru"),
	CHINESE("Chinese", "cn"),
	SLOVAK("Slovenský", "sk"),
	SWEDISH("Svenska", "swe"),
	FRENCH("Français", "fr"),
	CZECH("Czech", "cz");
	
	String extension;
	String name;
	
	Language(String name, String extension) {
		this.extension = extension;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public static Language getLanguage(String str) {
		for(Language lang : Language.values()) {
			if(str.equalsIgnoreCase(lang.name()) || str.equalsIgnoreCase(lang.getName()) || str.equalsIgnoreCase(lang.getExtension())) {
				return lang;
			}
		}
		return null;
	}
}
