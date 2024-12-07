package be.artex.powerUp.enchant;

import be.artex.powerUp.api.enchaments.EffectEnchantment;
import be.artex.powerUp.api.enchaments.EnchantmentType;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffectType;

public class Ninja extends EffectEnchantment {
    @Override
    public PotionEffectType getEffectType() {
        return PotionEffectType.SPEED;
    }

    @Override
    public String getName() {
        return "Ninja";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public ChatColor getNameColor() {
        return ChatColor.YELLOW;
    }

    @Override
    public EnchantmentType getType() {
        return EnchantmentType.BOOTS;
    }
}
