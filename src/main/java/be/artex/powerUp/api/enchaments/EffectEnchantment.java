package be.artex.powerUp.api.enchaments;

import org.bukkit.potion.PotionEffectType;

public abstract class EffectEnchantment extends Enchantment {
    public abstract PotionEffectType getEffectType();
}
