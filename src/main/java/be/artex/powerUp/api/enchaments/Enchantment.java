package be.artex.powerUp.api.enchaments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Enchantment {
    public static ArrayList<Enchantment> enchants = new ArrayList<>();

    public abstract String getName();
    public abstract int getMaxLevel();
    public abstract ChatColor getNameColor();
    public abstract EnchantmentType getType();

    public static void addEnchant(ItemStack stack, Enchantment enchantment, int level) {
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = meta.getLore();

        if (meta.getLore() == null)
            lore = new ArrayList<>();

        lore.add(enchantment.getNameColor() + enchantment.getName() + " " + level);

        meta.setLore(lore);
        stack.setItemMeta(meta);
    }

    public static EnchantmentType[] getEnchantmentTypeFromStack(ItemStack stack) {
        Material material = stack.getType();
        EnchantmentType[] enchantmentTypes = {};

        if (material.name().endsWith("_SWORD")) {
            return new EnchantmentType[]{EnchantmentType.ALL, EnchantmentType.SWAXE, EnchantmentType.SWORD};
        } else if (material.name().endsWith("_AXE")) {
            return new EnchantmentType[]{EnchantmentType.ALL, EnchantmentType.SWAXE};
        } else if (material.name().endsWith("_HELMET")) {
            return new EnchantmentType[]{EnchantmentType.ALL, EnchantmentType.ARMOR, EnchantmentType.HELMET};
        } else if (material.name().endsWith("_CHESTPLATE")) {
            return new EnchantmentType[]{EnchantmentType.ALL, EnchantmentType.ARMOR, EnchantmentType.CHESTPLATE};
        } else if (material.name().endsWith("_LEGGINGS")) {
            return new EnchantmentType[]{EnchantmentType.ALL, EnchantmentType.ARMOR, EnchantmentType.LEGGINGS};
        } else if (material.name().endsWith("_BOOTS")) {
            return new EnchantmentType[]{EnchantmentType.ALL, EnchantmentType.ARMOR, EnchantmentType.BOOTS};
        }

        return null;
    }


    public static Map<Enchantment, Integer> getEnchantmentsWithLevels(ItemStack stack) {
        Map<Enchantment, Integer> enchantmentsWithLevels = new HashMap<>();

        if (stack == null || !stack.hasItemMeta()) {
            return enchantmentsWithLevels;
        }

        ItemMeta meta = stack.getItemMeta();
        if (!meta.hasLore()) {
            return enchantmentsWithLevels;
        }

        List<String> lore = meta.getLore();
        if (lore == null) {
            return enchantmentsWithLevels;
        }

        for (String loreLine : lore) {
            for (Enchantment enchantment : Enchantment.enchants) {
                if (loreLine.startsWith(enchantment.getNameColor() + enchantment.getName())) {
                    try {
                        String[] parts = loreLine.split(" ");

                        int level = Integer.parseInt(parts[parts.length - 1]);
                        enchantmentsWithLevels.put(enchantment, level);

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid level format in lore: " + loreLine);
                    }

                    break;
                }
            }
        }

        return enchantmentsWithLevels;
    }

}
