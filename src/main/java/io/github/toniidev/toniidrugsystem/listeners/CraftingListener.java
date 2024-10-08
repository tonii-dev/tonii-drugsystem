package io.github.toniidev.toniidrugsystem.listeners;

import io.github.toniidev.toniidrugsystem.builders.CraftingBuilder;
import io.github.toniidev.toniidrugsystem.objects.Product;
import io.github.toniidev.toniidrugsystem.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {
    public Inventory jointCrafting = new CraftingBuilder(new ItemStack(Material.BONE))
            .setFirstRow("aaa")
            .setSecondRow("cee")
            .setThirdRow("arr")
            .setItem('a', new ItemStack(Material.AIR))
            .setItem('c', new ItemStack(Material.PAPER))
            .setItem('r', new ItemStack(Material.PAPER))
            .setItem('e', Product.item)
            .createInventory();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(ItemUtils.getName(e.getPlayer().getInventory().getItemInMainHand()).endsWith("Infiorescenza"))
            e.getPlayer().openInventory(jointCrafting);
    }
}
