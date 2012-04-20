package me.plugin.multilanguage;

import java.io.UnsupportedEncodingException;

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
	RUSSIAN("Pусский", "ru"),
	PORTUGUESE("Portuguesa", "pt");
	//CHINESE("Chinese", "cn");
	
	String extension;
	String name;
	
	Language(String name, String extension) {
		this.extension = extension;
		this.name = name;
	}
	
	public String getName() {
		try {
			return new String(name.getBytes("ISO-8859-1"), "UTF-8"); //ISO-8859-1
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
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
