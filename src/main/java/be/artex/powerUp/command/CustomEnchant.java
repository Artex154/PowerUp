package be.artex.powerUp.command;

import be.artex.powerUp.api.enchaments.Enchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CustomEnchant implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player))
            return false;

        ItemStack stack = player.getInventory().getItemInMainHand();

        if (stack == null || stack.getType().equals(Material.AIR))
            return false;

        if (args.length == 0)
            return false;

        String enchantName = args[0];

        if (args.length < 2)
            args = new String[]{args[0], "0"};

        int level;

        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            level = 0;
        }

        if (level == 0)
            return false;

        Enchantment enchant = null;

        for (Enchantment enchantment : Enchantment.enchants) {
            if (!enchantment.getName().equalsIgnoreCase(ChatColor.stripColor(enchantName)))
                continue;

            enchant = enchantment;
            break;
        }

        if (enchant == null)
            return false;

        if (level == 0)
            return false;

        if (level > enchant.getMaxLevel()) {
            Enchantment.addEnchant(stack, enchant, enchant.getMaxLevel());
            return true;
        }

        Enchantment.addEnchant(stack, enchant, level);
        return true;
    }
}
