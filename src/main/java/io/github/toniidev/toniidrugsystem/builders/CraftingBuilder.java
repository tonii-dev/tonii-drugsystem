package io.github.toniidev.toniidrugsystem.builders;

import io.github.toniidev.toniidrugsystem.enums.GlassType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CraftingBuilder {
    private final ItemStack product;

    private final Map<Character, ItemStack> items = new HashMap<>();
    private char[] firstRow = null;
    private char[] secondRow = null;
    private char[] thirdRow = null;

    public CraftingBuilder(ItemStack p){
        this.product = p;
    }

    public CraftingBuilder setItem(Character c, ItemStack s){
        items.put(c, s);
        return this;
    }

    public CraftingBuilder setFirstRow(String firstRow) {
        this.firstRow = firstRow.toCharArray();
        return this;
    }

    public CraftingBuilder setSecondRow(String secondRow) {
        this.secondRow = secondRow.toCharArray();
        return this;
    }

    public CraftingBuilder setThirdRow(String thirdRow) {
        this.thirdRow = thirdRow.toCharArray();
        return this;
    }

    public Inventory createInventory(){
        Inventory inv = new InventoryBuilder(5, "Crafting")
                .fill(GlassType.WHITE)
                .create();

        for(int i = 0; i < 3; i++){
            int firstRowRule = 11;
            inv.setItem(i + firstRowRule, items.get(firstRow[i]));
        }
        for(int i = 0; i < 3; i++){
            int secondRowRule = 20;
            inv.setItem(i + secondRowRule, items.get(secondRow[i]));
        }
        for(int i = 0; i < 3; i++){
            int thirdRowRule = 29;
            inv.setItem(i + thirdRowRule, items.get(thirdRow[i]));
        }

        inv.setItem(24, product);

        return inv;
    }
}
