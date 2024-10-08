package io.github.toniidev.toniidrugsystem.listeners;

import io.github.toniidev.toniidrugsystem.objects.MarijuanaPlant;
import io.github.toniidev.toniidrugsystem.utils.BlockUtils;
import io.github.toniidev.toniidrugsystem.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BlockListener implements Listener {
    private final Plugin plugin;

    public BlockListener(Plugin p){
        this.plugin = p;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!e.getPlayer().getInventory().getItemInMainHand().isSimilar(MarijuanaPlant.item)) return;

        Location tp = BlockUtils.calculateTopTitlePosition(e.getBlockPlaced());
        Location bp = BlockUtils.calculateBottomTitlePosition(e.getBlockPlaced());

        BlockUtils.spawnTitle("§bPiantina di Marijuana", tp);
        BlockUtils.spawnTitle("§e§lCLICK", bp);

        new MarijuanaPlant(e.getBlockPlaced().getLocation(), plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        MarijuanaPlant plant = MarijuanaPlant.plants.stream()
                .filter(x -> x.getLocation().equals(e.getBlock().getLocation()))
                .findFirst().orElse(null);

        if(plant == null) return;

        e.setDropItems(false);

        Location tp = BlockUtils.calculateTopTitlePosition(e.getBlock());
        Location bp = BlockUtils.calculateBottomTitlePosition(e.getBlock());

        ItemStack s = plant.product;
        s.setAmount(plant.getAmountOfProduct());
        e.getPlayer().getInventory().addItem(s);
        ChatUtils.sendItemsToInventoryAlert(e.getPlayer());

        BlockUtils.removeArmorStandAt(tp);
        BlockUtils.removeArmorStandAt(bp);
        MarijuanaPlant.plants.remove(plant);
    }
}
