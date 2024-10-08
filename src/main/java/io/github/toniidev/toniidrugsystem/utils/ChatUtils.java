package io.github.toniidev.toniidrugsystem.utils;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class ChatUtils {
    public static void sendMissingPermissionsMessage(Player p, Command c){
        p.sendMessage("§e[tonii-drugsystem] §aComando:§r Impossibile eseguire il comando §7/" + c.getName() + ": " +
                "§r§fnon hai i permessi necessari.");
    }

    public static void sendPlantNeedsWaterAlert(Player p){
        p.sendMessage("§e[BLOCCO] §aPiantina:§r Questa piantina ha bisogno di acqua! Clicca un secchio d'acqua nel tuo inventario per annaffiarla.");
    }

    public static void sendSuccessfulWaterMessage(Player p){
        p.sendMessage("§e[BLOCCO] §aPiantina:§r Hai annaffiato la piantina.");
    }

    public static void sendPlantDoesNotNeedWaterAlert(Player p){
        p.sendMessage("§e[BLOCCO] §aPiantina:§r Questa piantina non ha bisogno di essere innaffiata.");
    }

    public static void sendLightestSmellAlert(Player p){
        p.sendMessage("§e[MONDO] §aOlfatto:§r *Senti un §lleggerissimo§r odore di erba*.");
    }

    public static void sendLightSmellAlert(Player p){
        p.sendMessage("§e[MONDO] §aOlfatto:§r *Senti un §lleggero§r odore di erba*.");
    }

    public static void sendStrongSmellAlert(Player p){
        p.sendMessage("§e[MONDO] §aOlfatto:§r *Senti un §lforte§r odore di erba*.");
    }

    public static void sendStrongestSmellAlert(Player p){
        p.sendMessage("§e[MONDO] §aOlfatto:§r *Senti un §lfortissimo§r odore di erba*.");
    }

    public static void sendMaximumProductAlert(Player p){
        p.sendMessage("§e[BLOCCO] §aPiantina:§r Raccogli il prodotto della piantina per farne produrre altro. È stato raggiunto il limite.");
    }

    public static void sendItemsToInventoryAlert(Player p){
        p.sendMessage("§e[BLOCCO] §aPiantina:§r Il prodotto della piantina è stato aggiunto al tuo inventario.");
    }
}
