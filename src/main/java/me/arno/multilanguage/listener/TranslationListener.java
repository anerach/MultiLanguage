package me.arno.multilanguage.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.arno.multilanguage.Language;
import me.arno.multilanguage.Localisation;

public class TranslationListener extends MultiLanguageListener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Language language = getLanguageManager().getPlayerLanguage(player);
		
		Localisation localisation = new Localisation(language);
		localisation.sendMessage(player, "message.language");
		
		if(getSettingsManager().isLoginTranslationEnabled()) {
			sendMessage("message.login", player);
			event.setJoinMessage(null);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(getSettingsManager().isLogoutTranslationEnabled()) {
			sendMessage("message.logout", event.getPlayer());
			event.setQuitMessage(null);
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		if(getSettingsManager().isLogoutTranslationEnabled()) {
			sendMessage("message.logout", event.getPlayer());
			event.setLeaveMessage(null);
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		event.getDeathMessage();
		DamageCause deathCause = player.getLastDamageCause().getCause();
		
		if(player.getKiller() != null && getSettingsManager().isPvpDeathTranslationEnabled()) {
			sendMessage("deaths.pvp", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("wolf") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.wolf", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ocelot") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.ocelot", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("pigman") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.pigzombie", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("zombie") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.zombie", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("skeleton") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.skeleton", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("cave spider") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.cavespider", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("spider") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.spider", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("silverfish") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.silverfish", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("slime") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.slime", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("blew up") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.creeper", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("enderman") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.enderman", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ghast") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.ghast", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("blaze") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.blaze", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ender dragon") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			sendMessage("monsters.enderdragon", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.DROWNING && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.drowning", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.SUFFOCATION && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.suffocation", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.SUICIDE && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.suicide", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.FALL && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.fall", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.VOID && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.void", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.LAVA && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.lava", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.FIRE && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.fire", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.CONTACT && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			sendMessage("deaths.cactus", player);
			event.setDeathMessage(null);
		}
	}
}
