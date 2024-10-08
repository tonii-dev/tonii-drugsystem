package io.github.toniidev.toniidrugsystem.builders;

import io.github.toniidev.toniidrugsystem.enums.GlassType;
import io.github.toniidev.toniidrugsystem.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Objects;

public class InventoryBuilder {
    private final Inventory inv;
    private ItemStack glass = new ItemStack(Material.AIR);

    public InventoryBuilder(int rows, String title){
        this.inv = Bukkit.createInventory(null, rows * 9, title);
    }

    public InventoryBuilder setItem(int slot, ItemStack s){
        this.inv.setItem(slot, s);
        return this;
    }

    public InventoryBuilder setItems(Map<Integer, ItemStack> map){
        for(Map.Entry<Integer, ItemStack> entry : map.entrySet()){
            this.inv.setItem(entry.getKey(), entry.getValue());
        }
        return this;
    }

    private int findFirstEmpty(){
        int first = -1;
        for(int i = 0; i < this.inv.getSize(); i++){
            if(this.inv.getItem(i) == null || Objects.equals(this.inv.getItem(i), glass)){
                first = i;
                break;
            }
        }
        return first;
    }

    public InventoryBuilder addItem(ItemStack s){
        this.inv.setItem(this.inv.firstEmpty(), s);
        return this;
    }

    public InventoryBuilder fill(GlassType t){
        switch (t) {
            case BLACK -> glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            case WHITE -> glass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
            case GRAY -> glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            case LIGHT_GRAY -> glass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        }
        ItemUtils.changeName(glass, " ");

        for(int i = 0; i < this.inv.getSize(); i++){
            if(this.inv.getItem(i) == null) inv.setItem(i, glass);
        }
        return this;
    }

    public Inventory create(){
        return this.inv;
    }
}
