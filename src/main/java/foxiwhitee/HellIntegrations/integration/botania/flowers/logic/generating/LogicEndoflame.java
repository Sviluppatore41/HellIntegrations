package foxiwhitee.HellIntegrations.integration.botania.flowers.logic.generating;

import foxiwhitee.HellIntegrations.config.FlowerLogicConfig;
import foxiwhitee.HellIntegrations.integration.botania.flowers.IGeneratingFlowerLogic;
import foxiwhitee.HellIntegrations.integration.botania.flowers.IManaGenerator;
import foxiwhitee.HellIntegrations.integration.botania.flowers.LevelTypes;
import net.minecraft.tileentity.TileEntityFurnace;

import java.util.Arrays;
import java.util.List;

public class LogicEndoflame implements IGeneratingFlowerLogic {
    @Override
    public int getDefaultNeedTicks() {
        return FlowerLogicConfig.flowerLogicEndoflameTicks;
    }

    @Override
    public List<LevelTypes> getTypes() {
        return Arrays.asList(LevelTypes.BURNING, LevelTypes.DESTRUCTIVE, LevelTypes.WILD_HUNT);
    }

    @Override
    public long getGenerating() {
        return FlowerLogicConfig.flowerLogicEndoflameGenerating;
    }

    @Override
    public int getLevel() {
        return FlowerLogicConfig.flowerLogicEndoflameLevel;
    }

    @Override
    public void generate(IManaGenerator generator, int slot) {
        if (TileEntityFurnace.getItemBurnTime(generator.getStackInSLot(slot)) > 0) {
            IGeneratingFlowerLogic.super.generate(generator, slot);
        }
    }
}
