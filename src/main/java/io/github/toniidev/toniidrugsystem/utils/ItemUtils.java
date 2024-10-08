package io.github.toniidev.toniidrugsystem.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {
    public static boolean notifyMissingItemMeta(ItemStack s){
        if(s.getItemMeta() == null){
            ConsoleUtils.sendMissingMetaWarning(s);
            return true;
        }
        return false;
    }

    public static boolean isSpecialEnchanted(ItemStack s){
        if(notifyMissingItemMeta(s)) return false;
        assert s.getItemMeta() != null;

        return s.containsEnchantment(s.getType().equals(Material.BOW) ?
                Enchantment.PROTECTION_ENVIRONMENTAL : Enchantment.ARROW_INFINITE) && s.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS);
    }

    public static List<String> getLore(ItemStack s){
        if(notifyMissingItemMeta(s)) return null;
        assert s.getItemMeta() != null;
        return s.getItemMeta().getLore();
    }

    public static String getLoreLine(ItemStack s, int l){
        List<String> lore = getLore(s);
        if(lore == null) return null;
        if((long) lore.size() < l) {
            ConsoleUtils.sendValueHigherThanPossible(s);
            return null;
        }
        return lore.get(l - 1);
    }

    public static String getName(ItemStack s){
        if(notifyMissingItemMeta(s)) return " ";
        assert s.getItemMeta() != null;
        return s.getItemMeta().getDisplayName();
    }

    public static void changeName(ItemStack s, String name){
        if(notifyMissingItemMeta(s)) return;
        assert s.getItemMeta() != null;
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(name);
        s.setItemMeta(meta);
    }
}
