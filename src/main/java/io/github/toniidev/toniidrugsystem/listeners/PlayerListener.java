package io.github.toniidev.toniidrugsystem.listeners;

import io.github.toniidev.toniidrugsystem.objects.MarijuanaPlant;
import io.github.toniidev.toniidrugsystem.utils.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getTo() == null) return;
        for(MarijuanaPlant p : MarijuanaPlant.plants){
            if (Math.abs(e.getTo().distance(p.getLocation()) - 10) < 0.1) {
                ChatUtils.sendLightestSmellAlert(e.getPlayer());
            }
            if (Math.abs(e.getTo().distance(p.getLocation()) - 7) < 0.1) {
                ChatUtils.sendLightSmellAlert(e.getPlayer());
            }
            if (Math.abs(e.getTo().distance(p.getLocation()) - 5) < 0.1) {
                ChatUtils.sendStrongSmellAlert(e.getPlayer());
            }
            if (Math.abs(e.getTo().distance(p.getLocation()) - 2) < 0.1) {
                ChatUtils.sendStrongestSmellAlert(e.getPlayer());
            }
        }
    }
}
