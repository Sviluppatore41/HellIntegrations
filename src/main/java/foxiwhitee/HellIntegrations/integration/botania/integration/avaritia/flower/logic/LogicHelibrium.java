package foxiwhitee.HellIntegrations.integration.botania.integration.avaritia.flower.logic;

import foxiwhitee.HellIntegrations.config.FlowerLogicConfig;
import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.flowers.IGeneratingFlowerLogic;
import foxiwhitee.HellIntegrations.integration.botania.flowers.IManaGenerator;
import foxiwhitee.HellIntegrations.integration.botania.flowers.LevelTypes;

import java.util.Arrays;
import java.util.List;

public class LogicHelibrium implements IGeneratingFlowerLogic {
    @Override
    public int getDefaultNeedTicks() {
        return FlowerLogicConfig.flowerLogicHelibriumTicks;
    }

    @Override
    public List<LevelTypes> getTypes() {
        return Arrays.asList(LevelTypes.ASGARD);
    }

    @Override
    public long getGenerating() {
        return HellConfig.helibriumGeneration;
    }

    @Override
    public int getLevel() {
        return FlowerLogicConfig.flowerLogicHelibriumLevel;
    }

    @Override
    public void generate(IManaGenerator generator, int slot) {
        generator.setStoredMana(Math.min(generator.getMaxStoredMana(), generator.getStoredMana() + generator.calculateEffectsGenerating(getGenerating(), slot)));
    }
}
