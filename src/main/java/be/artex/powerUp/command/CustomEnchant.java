package be.artex.powerUp.command;

import be.artex.powerUp.api.enchaments.Enchantment;
import be.artex.powerUp.api.enchaments.EnchantmentType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEnchant implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This can only be used by a player !");
            return true;
        }

        ItemStack stack = player.getInventory().getItemInMainHand();

        if (stack == null || stack.getType().equals(Material.AIR)) {
            player.sendMessage(ChatColor.RED + "This enchant cannot be applied to this item.");
            return true;
        }

        String enchantName = args[0];

        if (args.length <= 1)
            return false;

        int level;

        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            level = 0;
        }

        if (level == 0) {
            player.sendMessage(ChatColor.RED + "You cannot apply an enchantment level 0.");
            return true;
        }

        Enchantment enchant = null;

        for (Enchantment enchantment : Enchantment.enchants) {
            if (!enchantment.getName().equalsIgnoreCase(ChatColor.stripColor(enchantName)))
                continue;

            enchant = enchantment;
            break;
        }

        if (enchant == null) {
            player.sendMessage(ChatColor.RED + "This enchantment was not found.");
            return true;
        }

        EnchantmentType[] types = Enchantment.getEnchantmentTypeFromStack(stack);

        if (types == null) {
            player.sendMessage(ChatColor.RED + "This item cannot have any enchantments.");
            return true;
        }

        boolean canBeAdded = false;
        
        for (EnchantmentType type : types) {
            if (!type.equals(enchant.getType()))
                continue;

            canBeAdded = true;
        }

        if (!canBeAdded) {
            player.sendMessage(ChatColor.RED + "This enchant cannot be applied to this item.");
            return true;
        }

        if (level > enchant.getMaxLevel()) {
            Enchantment.addEnchant(stack, enchant, enchant.getMaxLevel());
            return true;
        }

        Enchantment.addEnchant(stack, enchant, level);
        return true;
    }
}
