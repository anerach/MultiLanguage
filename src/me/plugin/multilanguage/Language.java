package me.plugin.multilanguage;

public enum Language {
	ENGLISH("English", "eng"),
	DUTCH("Dutch", "nl");
	
	String fullName;
	String shortName;
	Language(String fullName, String shortName) {
		this.fullName = fullName;
		this.shortName = shortName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public static Language getLanguage(String str) {
		if(str.equalsIgnoreCase("english") || str.equalsIgnoreCase("eng"))
			return Language.ENGLISH;
		else if(str.equalsIgnoreCase("dutch") || str.equalsIgnoreCase("nl"))
			return Language.DUTCH;
		
		return null;
	}
}
