package be.artex.powerUp.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Stacks {
    public  static ItemStack playItem() {
        ItemStack stack = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "PowerUp " + ChatColor.GRAY + "| " + ChatColor.WHITE + "Play");
        meta.addEnchant(Enchantment.AQUA_AFFINITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        stack.setItemMeta(meta);

        return stack;
    }
}
