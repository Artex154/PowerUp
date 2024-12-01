package be.artex.powerUp.listener.item;

import be.artex.powerUp.item.Stacks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Click implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack stack = event.getItem();

        if (stack == null || stack.getType() == Material.AIR)
            return;

        if (stack.equals(Stacks.playItem()));
    }
}
