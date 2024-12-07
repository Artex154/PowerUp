package be.artex.powerUp.listener.player;

import be.artex.powerUp.api.enchaments.EffectEnchantment;
import be.artex.powerUp.api.enchaments.Enchantment;
import be.artex.powerUp.api.enchaments.MeleeEnchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.Map;

public class Move implements Listener {
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        ItemStack boots = playerInventory.getBoots();
        Map<Enchantment, Integer> enchantments = Enchantment.getEnchantmentsWithLevels(boots);

        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();

            if (!(enchantment instanceof EffectEnchantment))
                return;

            EffectEnchantment ench = (EffectEnchantment) enchantment;

            int potionLevel = entry.getValue() - 1;

            player.addPotionEffect(new PotionEffect(ench.getEffectType(), 2*20, potionLevel));
        }
    }
}
