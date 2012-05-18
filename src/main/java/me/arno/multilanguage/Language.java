package me.arno.multilanguage;

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
	PORTUGUESE("Portuguesa", "pt"),
	SLOVAK("Slovak", "sk");
	//RUSSIAN("Pусский", "ru"),
	//CHINESE("Chinese", "cn"),
	
	String extension;
	String name;
	
	Language(String name, String extension) {
		this.extension = extension;
		this.name = name;
	}
	
	@Override
	public String toString() {
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
		if(Language.valueOf(str) != null)
			return Language.valueOf(str);
		
		for(Language lang : Language.values()) {
			if(str.equalsIgnoreCase(lang.name()) || str.equalsIgnoreCase(lang.toString()) || str.equalsIgnoreCase(lang.getExtension())) {
				return lang;
			}
		}
		return null;
	}
}
