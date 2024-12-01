package be.artex.powerUp.api.enchaments;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public abstract class MeleeEnchantment extends Enchantment {
    public abstract void onHit(EntityDamageByEntityEvent event, int level);
}
