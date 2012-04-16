package me.plugin.multilanguage;

public enum Language {
	ENGLISH("English", "eng"),
	DUTCH("Nederlands", "nl"),
	SPANISH("Espanol", "es"),
	DANISH("Dansk", "dk"),
	POLISH("Polski", "pl"),
	ITALIAN("Italiano", "it"),
	LITHUANIAN("Lietuva", "lt"),
	NORWEGIAN("Norsk", "no"),
	GERMAN("Deutsch", "de"),
	PORTUGUESE("Portuguesa", "pt");
	//CHINESE("Chinese", "cn");
	
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
