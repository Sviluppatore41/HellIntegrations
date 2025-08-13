package foxiwhitee.HellIntegrations.integration.botania.flowers.logic.generating;

import appeng.api.storage.data.IAEStack;
import appeng.util.item.AEItemStack;
import foxiwhitee.HellIntegrations.config.FlowerLogicConfig;
import foxiwhitee.HellIntegrations.integration.botania.flowers.IGeneratingFlowerLogic;
import foxiwhitee.HellIntegrations.integration.botania.flowers.LevelTypes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogicEntropinnyum implements IGeneratingFlowerLogic {
    @Override
    public int getDefaultNeedTicks() {
        return FlowerLogicConfig.flowerLogicEntropinnyumTicks;
    }

    @Override
    public List<LevelTypes> getTypes() {
        return Arrays.asList(LevelTypes.BURNING, LevelTypes.DESTRUCTIVE, LevelTypes.WILD_HUNT);
    }

    @Override
    public long getGenerating() {
        return FlowerLogicConfig.flowerLogicEntropinnyumGenerating;
    }

    @Override
    public int getLevel() {
        return FlowerLogicConfig.flowerLogicEntropinnyumLevel;
    }

    @Override
    public List<IAEStack> getConsumed() {
        return new ArrayList<>(Arrays.asList(AEItemStack.create(new ItemStack(Blocks.tnt))));
    }
}
