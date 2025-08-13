package foxiwhitee.HellIntegrations.integration.draconic.tile;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.ModRecipes;
import foxiwhitee.HellIntegrations.helpers.IAutomatedBlockUpgrade;
import foxiwhitee.HellIntegrations.integration.draconic.DraconicEvolutionIntegration;
import foxiwhitee.HellIntegrations.integration.draconic.items.ItemArialUpgrade;
import foxiwhitee.HellIntegrations.integration.draconic.items.ItemChaosUpgrade;
import foxiwhitee.HellIntegrations.integration.draconic.items.ItemDragonUpgrade;
import foxiwhitee.HellIntegrations.recipes.BaseAutoBlockRecipe;
import foxiwhitee.HellIntegrations.tile.TileAutomatedUpgradeableBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TileAutoAwakenedBlocks extends TileAutomatedUpgradeableBlock {

    @Override
    protected String[] getStatuses() {
        return new String[]{"dragon", "chaos", "arial"};
    }

    @Override
    protected Class<? extends IAutomatedBlockUpgrade>[] getUpgrades() {
        return new Class[]{ItemDragonUpgrade.class, ItemChaosUpgrade.class, ItemArialUpgrade.class};
    }

    @Override
    protected List<BaseAutoBlockRecipe>[] getCrafts() {
        return new List[]{ModRecipes.autoAwakenedBlocksDragonRecipes, ModRecipes.autoAwakenedBlocksChaosRecipes, ModRecipes.autoAwakenedBlocksArialRecipes};
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(DraconicEvolutionIntegration.AUTO_AWAKENER_BLOCKS);
    }

    @Override
    protected long getMaxCount() {
        return HellConfig.speedAutoAwakenedBlocks;
    }
}