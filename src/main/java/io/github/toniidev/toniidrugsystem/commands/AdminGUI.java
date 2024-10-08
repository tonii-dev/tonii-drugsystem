package io.github.toniidev.toniidrugsystem.commands;

import io.github.toniidev.toniidrugsystem.builders.InventoryBuilder;
import io.github.toniidev.toniidrugsystem.enums.GlassType;
import io.github.toniidev.toniidrugsystem.objects.MarijuanaPlant;
import io.github.toniidev.toniidrugsystem.objects.Product;
import io.github.toniidev.toniidrugsystem.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class AdminGUI implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) {
            ChatUtils.sendMissingPermissionsMessage((Player) sender, command);
            return true;
        }

        Player p = (Player) sender;

        Inventory inv = new InventoryBuilder(1, "Admin")
                .setItem(0, MarijuanaPlant.item)
                .setItem(1, Product.item)
                .fill(GlassType.WHITE)
                .create();
        p.openInventory(inv);

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(!e.getView().getTitle().equals("Admin")) return;
        e.setCancelled(true);
        if(e.getRawSlot() == 0) e.getWhoClicked().getInventory().addItem(MarijuanaPlant.item);
        if(e.getRawSlot() == 1) e.getWhoClicked().getInventory().addItem(Product.item);
    }
}
