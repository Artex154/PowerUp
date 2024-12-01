package be.artex.powerUp.listener.player;

import be.artex.powerUp.api.enchaments.Enchantment;
import be.artex.powerUp.api.enchaments.MeleeEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class DamageEntity implements Listener {
    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player))
            return;

        PlayerInventory inventory = player.getInventory();
        ItemStack stack = inventory.getItemInMainHand();
        Map<Enchantment, Integer> enchantments = Enchantment.getEnchantmentsWithLevels(stack);

        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();

            if (enchantment instanceof MeleeEnchantment) {
                ((MeleeEnchantment) enchantment).onHit(event, level);
            }
        }
    }
}
