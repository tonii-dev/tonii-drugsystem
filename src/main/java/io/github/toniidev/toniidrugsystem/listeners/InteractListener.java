package io.github.toniidev.toniidrugsystem.listeners;

import io.github.toniidev.toniidrugsystem.builders.InventoryBuilder;
import io.github.toniidev.toniidrugsystem.enums.GlassType;
import io.github.toniidev.toniidrugsystem.objects.MarijuanaPlant;
import io.github.toniidev.toniidrugsystem.objects.Product;
import io.github.toniidev.toniidrugsystem.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InteractListener implements Listener {
    private static final Map<Player, MarijuanaPlant> interacting = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        assert e.getClickedBlock() != null;

        MarijuanaPlant p = MarijuanaPlant.plants.stream()
                .filter(x -> x.getLocation().equals(e.getClickedBlock().getLocation()))
                .findFirst().orElse(null);

        if(p == null) return;

        ItemStack s = Product.item;
        s.setAmount(p.getAmountOfProduct());
        Inventory inv = new InventoryBuilder(1, "Piantina")
                .setItem(0, s)
                .fill(GlassType.WHITE)
                .create();

        if(p.getAmountOfProduct() == 64) ChatUtils.sendMaximumProductAlert(e.getPlayer());
        else if(p.needsWater()) ChatUtils.sendPlantNeedsWaterAlert(e.getPlayer());
        e.getPlayer().openInventory(inv);
        interacting.put(e.getPlayer(), p);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(!e.getView().getTitle().equals("Piantina")) return;
        e.setCancelled(true);
        if(e.getCurrentItem() == null) return;

        MarijuanaPlant p = interacting.get((Player) e.getWhoClicked());

        if(e.getCurrentItem().isSimilar(Product.item)) {
            if(e.getInventory().firstEmpty() != -1){
                ItemStack s = p.product;
                s.setAmount(1);
                e.getWhoClicked().getInventory().addItem(s);
                e.getCurrentItem().setAmount(e.getCurrentItem().getAmount() - 1);
                p.reduceAmountOfProduct(1);
            }
            else ChatUtils.sendFullInventoryAlert((Player) e.getWhoClicked());
        }
        else if(e.getCurrentItem().getType().equals(Material.WATER_BUCKET)){
            if(p.getAmountOfProduct() != 64){
                if(p.needsWater()){
                    e.getCurrentItem().setAmount(e.getCurrentItem().getAmount() - 1);
                    ChatUtils.sendSuccessfulWaterMessage((Player) e.getWhoClicked());
                    p.water();
                }
                else ChatUtils.sendPlantDoesNotNeedWaterAlert((Player) e.getWhoClicked());
            }
            else ChatUtils.sendMaximumProductAlert((Player) e.getWhoClicked());
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        interacting.remove(e.getPlayer());
    }
}
