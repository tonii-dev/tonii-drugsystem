package io.github.toniidev.toniidrugsystem;

import io.github.toniidev.toniidrugsystem.commands.AdminGUI;
import io.github.toniidev.toniidrugsystem.listeners.CraftingListener;
import io.github.toniidev.toniidrugsystem.listeners.InteractListener;
import io.github.toniidev.toniidrugsystem.listeners.BlockListener;
import io.github.toniidev.toniidrugsystem.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new AdminGUI(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingListener(), this);
        Bukkit.getPluginCommand("admingui").setExecutor(new AdminGUI());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
