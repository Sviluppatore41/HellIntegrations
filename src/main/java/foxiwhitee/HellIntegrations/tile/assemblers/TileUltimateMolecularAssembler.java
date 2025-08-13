package foxiwhitee.HellIntegrations.tile.assemblers;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.ModBlocks;
import net.minecraft.item.ItemStack;

public class TileUltimateMolecularAssembler extends TileCustomMolecularAssembler{

    public TileUltimateMolecularAssembler() {
        getProxy().setIdlePowerUsage(HellConfig.ultimate_molecular_assembler_power);
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(ModBlocks.ULTIMATE_MOLECULAR_ASSEMBLER);
    }

    @Override
    public long getMaxCount() {
        return HellConfig.ultimate_molecular_assembler_speed - 1L;
    }

    @Override
    protected double getPower() {
        return HellConfig.ultimate_molecular_assembler_power;
    }

    @Override
    public String getName() {
        return ModBlocks.ULTIMATE_MOLECULAR_ASSEMBLER.getUnlocalizedName();
    }
}
