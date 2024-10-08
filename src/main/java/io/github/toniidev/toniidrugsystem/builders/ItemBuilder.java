package io.github.toniidev.toniidrugsystem.builders;

import io.github.toniidev.toniidrugsystem.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private final ItemStack stack;
    private final ItemMeta meta;
    private List<String> lore = new ArrayList<>();
    private boolean specialEnchanted = false;
    private String name = " ";

    public ItemBuilder(Material mat){
        this.stack = new ItemStack(mat);
        this.meta = stack.getItemMeta();
    }

    public ItemBuilder(Material mat, int amount){
        this.stack = new ItemStack(mat, amount);
        this.meta = stack.getItemMeta();
    }

    public ItemBuilder(ItemStack s){
        this.stack = s;
        this.meta = stack.getItemMeta();
        if(meta != null){
            if(meta.hasLore()) this.lore = meta.getLore();
        }
    }

    public ItemBuilder setSpecialEnchanted(boolean value){
        this.specialEnchanted = value;
        return this;
    }

    public ItemBuilder setName(String value){
        this.name = value;
        return this;
    }

    public ItemBuilder addLoreLine(String l){
        this.lore.add("§r§7" + l);
        return this;
    }

    public ItemBuilder addBlankLoreLine(){
        this.lore.add(" ");
        return this;
    }

    public ItemStack create(){
        if(ItemUtils.notifyMissingItemMeta(this.stack)) return this.stack;
        assert this.stack.getItemMeta() != null;

        this.meta.setDisplayName(this.name);
        this.meta.setLore(this.lore);
        if(this.specialEnchanted){
            this.meta.addEnchant(this.stack.getType().equals(Material.BOW) ?
                    Enchantment.PROTECTION_ENVIRONMENTAL : Enchantment.ARROW_INFINITE, 1, true);
            this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        stack.setItemMeta(this.meta);
        return stack;
    }
}
