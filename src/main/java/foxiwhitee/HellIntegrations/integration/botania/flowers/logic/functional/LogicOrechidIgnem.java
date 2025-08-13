package foxiwhitee.HellIntegrations.integration.botania.flowers.logic.functional;

import foxiwhitee.HellIntegrations.config.FlowerLogicConfig;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

import java.util.Map;

public class LogicOrechidIgnem extends LogicOrechid {
    @Override
    public int getDefaultNeedTicks() {
        return FlowerLogicConfig.flowerLogicOrechidIgnemTicks;
    }

    @Override
    public long getManaNeed() {
        return FlowerLogicConfig.flowerLogicOrechidIgnemConsume;
    }

    protected Map<String, Integer> getOreMap() {
        return BotaniaAPI.oreWeightsNether;
    }

    protected ItemStack getConsumeItem() {
        return new ItemStack(Blocks.netherrack);
    }
}
