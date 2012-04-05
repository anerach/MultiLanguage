package me.plugin.multilanguage.listeners;

import me.plugin.multilanguage.Localisation;
import me.plugin.multilanguage.MultiLanguage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class LanguageListener implements Listener {
	private MultiLanguage plugin;
	
	public LanguageListener(MultiLanguage plugin) {
		this.plugin = plugin;
	}
	
	public void sendMessage(String msg, Player player) {
		Localisation localisation;
		
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			localisation = new Localisation(plugin, plugin.playerLanguages.get(p.getName()));
			p.sendMessage(localisation.getMessage(msg, player));
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		sendMessage("message.login", player);
		event.setJoinMessage(null);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		sendMessage("message.logout", player);
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		Player player = event.getPlayer();
		sendMessage("message.logout", player);
		event.setLeaveMessage(null);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		event.getDeathMessage();
		DamageCause deathCause = player.getLastDamageCause().getCause();
		if(player.getKiller() != null) {
			sendMessage("deaths.pvp", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("wolf")) {
			sendMessage("monsters.wolf", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ocelot")) {
			sendMessage("monsters.ocelot", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("zombie")) {
			sendMessage("monsters.zombie", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("skeleton")) {
			sendMessage("monsters.skeleton", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("pigzombie")) {
			sendMessage("monsters.pigzombie", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("cave spider")) {
			sendMessage("monsters.cavespider", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("spider")) {
			sendMessage("monsters.spider", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("silverfish")) {
			sendMessage("monsters.silverfish", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("slime")) {
			sendMessage("monsters.slime", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("creeper")) {
			sendMessage("monsters.creeper", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("enderman")) {
			sendMessage("monsters.enderman", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ghast")) {
			sendMessage("monsters.ghast", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("blaze")) {
			sendMessage("monsters.blaze", player);
			event.setDeathMessage(null);
		} else if(event.getDeathMessage().toLowerCase().contains("ender dragon")) {
			sendMessage("monsters.enderdragon", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.DROWNING) {
			sendMessage("deaths.drowning", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.SUFFOCATION) {
			sendMessage("deaths.suffocation", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.SUICIDE) {
			sendMessage("deaths.suicide", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.FALL) {
			sendMessage("deaths.fall", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.VOID) {
			sendMessage("deaths.void", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.LAVA) {
			sendMessage("deaths.lava", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.FIRE) {
			sendMessage("deaths.fire", player);
			event.setDeathMessage(null);
		} else if(deathCause == DamageCause.CONTACT) {
			sendMessage("deaths.cactus", player);
			event.setDeathMessage(null);
		}
	}
}
