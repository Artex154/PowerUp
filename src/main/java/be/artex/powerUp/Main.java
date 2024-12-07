package be.artex.powerUp;

import be.artex.powerUp.api.enchaments.Enchantment;
import be.artex.powerUp.command.CustomEnchant;
import be.artex.powerUp.enchant.Frozen;
import be.artex.powerUp.enchant.Ninja;
import be.artex.powerUp.listener.player.DamageEntity;
import be.artex.powerUp.listener.player.Move;
import be.artex.powerUp.listener.player.Server;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // initializePlatform(Material.BARRIER);
        // this.getServer().getPluginManager().registerEvents(new Server(), this);
        getServer().getPluginManager().registerEvents(new DamageEntity(), this);
        getServer().getPluginManager().registerEvents(new Move(), this);

        getCommand("ce").setExecutor(new CustomEnchant());

        Enchantment.enchants.add(new Frozen());
        Enchantment.enchants.add(new Ninja());
    }

    private void initializePlatform(Material material) {
        World world = Bukkit.getWorlds().get(0);
        int centerX = 0;
        int centerY = 120;
        int centerZ = 0;
        int platformSize = 30;
        int borderHeight = 4;

        int startX = centerX - platformSize / 2;
        int endX = centerX + platformSize / 2;
        int startZ = centerZ - platformSize / 2;
        int endZ = centerZ + platformSize / 2;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                Block block = world.getBlockAt(x, centerY, z);

                if (x == startX || x == endX || z == startZ || z == endZ) {
                    for (int y = centerY; y < centerY + borderHeight; y++) {
                        Block borderBlock = world.getBlockAt(x, y, z);
                        borderBlock.setType(material);
                    }
                } else {
                    block.setType(material);
                }

            }

        }

    }

}
