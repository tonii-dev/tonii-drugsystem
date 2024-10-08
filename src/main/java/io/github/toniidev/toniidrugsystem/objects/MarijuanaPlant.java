package io.github.toniidev.toniidrugsystem.objects;

import io.github.toniidev.toniidrugsystem.builders.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarijuanaPlant {
    public static final List<MarijuanaPlant> plants = new ArrayList<>();

    private int amountOfProduct;
    private final Location location;
    private final Plugin plugin;
    private boolean needsWater;

    private final Random rnd = new Random();
    private final int thc = rnd.nextInt(99);
    private final int cbd = 100 - thc;

    public static final ItemStack item = new ItemBuilder(Material.OAK_SAPLING)
            .setName("§aPiantina di Marijuana")
            .addLoreLine("Una piantina di marijuana.")
            .addLoreLine("Cresce rigogliosa se trattata con cura!")
            .addBlankLoreLine()
            .addLoreLine("§ePiazza §6§lTASTO DESTRO")
            .addLoreLine("§eGestione §6§lTASTO DESTRO§r§7 [una volta piazzata]")
            .create();

    public ItemStack product = new ItemBuilder(Product.item.clone())
            .setName("§a§lInfiorescenza")
            .addBlankLoreLine()
            .addLoreLine("Percentuale THC: §f" + thc + "%")
            .addLoreLine("Percentuale CBD: §f" + cbd + "%")
            .create();

    public MarijuanaPlant(Location loc, Plugin p){
        this.location = loc;
        this.plugin = p;
        this.needsWater = true;
        plants.add(this);
        produce();
    }

    public Location getLocation(){
        return this.location;
    }

    public int getAmountOfProduct(){
        return this.amountOfProduct;
    }

    public void reduceAmountOfProduct(int value){
        this.amountOfProduct -= value;
    }

    public boolean needsWater(){
        return this.needsWater;
    }

    private void produce(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!needsWater && amountOfProduct < 64) amountOfProduct++;
                produce();
            }
        }.runTaskLater(plugin, 10L);
        if(amountOfProduct % 4 == 0) this.needsWater = true;
    }

    public void water(){
        this.needsWater = false;
    }
}
