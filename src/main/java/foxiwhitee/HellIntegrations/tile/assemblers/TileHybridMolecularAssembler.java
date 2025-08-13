package foxiwhitee.HellIntegrations.tile.assemblers;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.ModBlocks;
import net.minecraft.item.ItemStack;

public class TileHybridMolecularAssembler extends TileCustomMolecularAssembler{

    public TileHybridMolecularAssembler() {
        getProxy().setIdlePowerUsage(HellConfig.hybrid_molecular_assembler_power);
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(ModBlocks.HYBRID_MOLECULAR_ASSEMBLER);
    }

    @Override
    public long getMaxCount() {
        return HellConfig.hybrid_molecular_assembler_speed - 1L;
    }

    @Override
    protected double getPower() {
        return HellConfig.hybrid_molecular_assembler_power;
    }

    @Override
    public String getName() {
        return ModBlocks.HYBRID_MOLECULAR_ASSEMBLER.getUnlocalizedName();
    }
}
