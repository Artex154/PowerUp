package be.artex.powerUp.listener.player;

import be.artex.powerUp.item.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

public class Server implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();

        playerInventory.clear();

        player.teleport(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));
        player.getInventory().setItem(4, Stacks.playItem());

        event.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "PowerUp " + ChatColor.GRAY + "| " + ChatColor.WHITE + player.getName() + " joined.");
    }
}
