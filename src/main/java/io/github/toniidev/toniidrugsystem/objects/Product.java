package io.github.toniidev.toniidrugsystem.objects;

import io.github.toniidev.toniidrugsystem.builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Product {
    public static final ItemStack item = new ItemBuilder(Material.GREEN_DYE)
            .setName("§a§lInfiorescenza")
            .addLoreLine("Un'infiorescenza di una piantina di Marijuana.")
            .setSpecialEnchanted(true)
            .create();
}
