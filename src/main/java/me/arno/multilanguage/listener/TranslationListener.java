package me.arno.multilanguage.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.arno.multilanguage.Localisation;

public class TranslationListener extends MultiLanguageListener {
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		Localisation.sendMessage(player, "message.language");
		
		if(getSettingsManager().isLoginTranslationEnabled()) {
			Localisation.sendGlobalMessage("message.login", player);
			event.setJoinMessage(null);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(getSettingsManager().isLogoutTranslationEnabled()) {
			Localisation.sendGlobalMessage("message.logout", event.getPlayer());
			event.setQuitMessage(null);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerKick(PlayerKickEvent event) {
		if(getSettingsManager().isLogoutTranslationEnabled()) {
			Localisation.sendGlobalMessage("message.logout", event.getPlayer());
			event.setLeaveMessage(null);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		event.getDeathMessage();
		DamageCause deathCause = player.getLastDamageCause().getCause();
		
		if(player.getKiller() != null && getSettingsManager().isPvpDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.pvp", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("wolf") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.wolf", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ocelot") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.ocelot", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("pigman") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.pigzombie", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("zombie") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.zombie", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("skeleton") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.skeleton", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("cave spider") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.cavespider", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("spider") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.spider", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("silverfish") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.silverfish", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("slime") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.slime", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("blew up") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.creeper", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("enderman") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.enderman", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ghast") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.ghast", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("blaze") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.blaze", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ender dragon") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.enderdragon", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("wither skeleton") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.witch", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("wither") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("monsters.wither", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("anvil") && getSettingsManager().isMonsterDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.anvil", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.DROWNING && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.drowning", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.SUFFOCATION && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.suffocation", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.SUICIDE && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.suicide", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.FALL && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.fall", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.VOID && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.void", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.LAVA && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.lava", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.FIRE && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.fire", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.CONTACT && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.cactus", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.WITHER && getSettingsManager().isNaturalDeathTranslationEnabled()) {
			Localisation.sendGlobalMessage("deaths.wither", player);
			event.setDeathMessage(null);
		}
	}
}
