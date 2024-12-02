package be.artex.powerUp.enchant;

import be.artex.powerUp.api.enchaments.EnchantmentType;
import be.artex.powerUp.api.enchaments.MeleeEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Frozen extends MeleeEnchantment {
    @Override
    public String getName() {
        return "Frozen";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public ChatColor getNameColor() {
        return ChatColor.AQUA;
    }

    @Override
    public EnchantmentType getType() {
        return EnchantmentType.SWORD;
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event, int level) {
        int chance = level * 20;
        int random = new Random().nextInt(100);

        if (random < chance) {
            Entity ent = event.getEntity();
            PotionEffect eff = new PotionEffect(PotionEffectType.SLOWNESS, 5 * 20, 0);

            ent.setFreezeTicks(5 * 20);
            eff.apply((LivingEntity) ent);
        }

    }

}
