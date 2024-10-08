package io.github.toniidev.toniidrugsystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class ConsoleUtils {
    public static void sendMissingMetaWarning(ItemStack s){
        Bukkit.getLogger().warning("Impossibile interagire con " + s.getType() + ": non ha ItemMeta.");
    }

    public static void sendValueHigherThanPossible(ItemStack s){
        Bukkit.getLogger().warning("Impossibile interagire con " + s.getType() + ": l'intero specificato è troppo grande.");
    }
}
