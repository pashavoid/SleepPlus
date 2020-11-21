package ru.pashavoid.sleeplus;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Sleep extends JavaPlugin implements Listener {

    /*
        «Sleep+» by pashavoid
        Copyright 2020 pashavoid Licensed under the Apache License, Version 2.0 (the «License»);
    */

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    private void onSleep(PlayerBedEnterEvent e){

        final Player p = e.getPlayer();
        final Location loc = p.getLocation();
        final long time = p.getWorld().getTime();
        p.setPlayerTime(0, false);
        p.setPlayerWeather(WeatherType.CLEAR);
        p.sendMessage(ChatColor.AQUA + "[Sleep+] " + ChatColor.GRAY + "Your time is set to the day before the start of the next day.");
        e.setCancelled(true);

        int taskId = (new BukkitRunnable() {
            public void run() {
                p.sendMessage(ChatColor.AQUA + "[Sleep+] " + ChatColor.GRAY + "Time reset to the time in the world.");
                p.resetPlayerTime();
                p.resetPlayerWeather();
            }
        }).runTaskLater(this, (24000L - time)).getTaskId();
    }
}