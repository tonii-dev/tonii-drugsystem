package io.github.toniidev.toniidrugsystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockUtils {
    public static void spawnTitle(String t, Location l){
        assert l.getWorld() != null;
        ArmorStand s = l.getWorld().spawn(l, ArmorStand.class);

        s.setCustomName(t);
        s.setGravity(false);
        s.setCustomNameVisible(true);
        s.setInvisible(true);
        s.setInvulnerable(true);
        s.setCollidable(false);
    }

    public static Location calculateBottomTitlePosition(Block b){
        return b.getLocation().clone().add(0.5, -1, 0.5);
    }

    public static Location calculateTopTitlePosition(Block b){
        return calculateBottomTitlePosition(b).clone().add(0, 0.25, 0);
    }

    public static void replaceBottomText(Block b, String newTitle){
        Location loc = calculateBottomTitlePosition(b);
        removeArmorStandAt(loc);
        spawnTitle(newTitle, loc);
    }

    public static boolean searchForArmorStands(Location exactLoc){
        return getArmorStandAt(exactLoc) != null;
    }

    @Nullable
    public static ArmorStand getArmorStandAt(Location exactLoc){
        assert exactLoc.getWorld() != null;
        return exactLoc.getWorld().getNearbyEntities(exactLoc, 0.1, 0.1, 0.1).stream()
                .filter(entity -> entity instanceof ArmorStand)
                .map(entity -> (ArmorStand) entity)
                .findFirst().orElse(null);
    }

    public static void removeArmorStandAt(Location exactLoc){
        assert exactLoc.getWorld() != null;
        if(!searchForArmorStands(exactLoc)){
            Bukkit.getLogger().warning("Nessun Armor Stand trovato. Posizione: " + exactLoc);
            return;
        }

        Objects.requireNonNull(getArmorStandAt(exactLoc)).remove();
    }
}
