package foxiwhitee.HellIntegrations.tile;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.ModBlocks;
import foxiwhitee.HellIntegrations.ModRecipes;
import foxiwhitee.HellIntegrations.recipes.BaseAutoBlockRecipe;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TileAutoPress extends TileAutomatedBlock{
    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(ModBlocks.AUTO_PRESS);
    }

    @Override
    protected List<BaseAutoBlockRecipe> getRecipes() {
        return ModRecipes.autoPressRecipes;
    }

    @Override
    protected long getMaxCount() {
        return HellConfig.speedAutoPress;
    }
}
