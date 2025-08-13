package foxiwhitee.HellIntegrations.tile.assemblers;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.ModBlocks;
import net.minecraft.item.ItemStack;

public class TileBaseMolecularAssembler extends TileCustomMolecularAssembler{

    public TileBaseMolecularAssembler() {
        getProxy().setIdlePowerUsage(HellConfig.basic_molecular_assembler_power);
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(ModBlocks.BASE_MOLECULAR_ASSEMBLER);
    }

    @Override
    public long getMaxCount() {
        return HellConfig.basic_molecular_assembler_speed - 1L;
    }

    @Override
    protected double getPower() {
        return HellConfig.basic_molecular_assembler_power;
    }

    @Override
    public String getName() {
        return ModBlocks.BASE_MOLECULAR_ASSEMBLER.getUnlocalizedName();
    }
}
