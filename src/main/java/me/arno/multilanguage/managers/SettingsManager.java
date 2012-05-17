package me.arno.multilanguage.managers;

import org.bukkit.configuration.file.FileConfiguration;

import me.arno.multilanguage.Language;
import me.arno.multilanguage.MultiLanguage;

public class SettingsManager {
	
	public Language getDefaultLanguage() {
		return Language.getLanguage(getConfig().getString("default-language"));
	}
	
	public boolean isUpdateCheckingEnabled() {
		return getConfig().getBoolean("check-for-updates");
	}
	
	public boolean isChannelChatEnabled() {
		return getConfig().getBoolean("channels-enabled");
	}
	
	public boolean isLoginTranslationEnabled() {
		return getConfig().getBoolean("translations.login");
	}
	
	public boolean isLogoutTranslationEnabled() {
		return getConfig().getBoolean("translations.logout");
	}
	
	public boolean isPvpDeathTranslationEnabled() {
		return getConfig().getBoolean("translations.deaths.pvp");
	}
	
	public boolean isNaturalDeathTranslationEnabled() {
		return getConfig().getBoolean("translations.deaths.natural");
	}
	
	public boolean isMonsterDeathTranslationEnabled() {
		return getConfig().getBoolean("translations.deaths.monsters");
	}
	
	public FileConfiguration getConfig() {
		return MultiLanguage.plugin.getConfig();
	}
}
