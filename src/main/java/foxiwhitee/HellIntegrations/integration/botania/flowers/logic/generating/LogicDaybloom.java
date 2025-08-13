package foxiwhitee.HellIntegrations.integration.botania.flowers.logic.generating;

import foxiwhitee.HellIntegrations.config.FlowerLogicConfig;
import foxiwhitee.HellIntegrations.integration.botania.flowers.IGeneratingFlowerLogic;
import foxiwhitee.HellIntegrations.integration.botania.flowers.LevelTypes;

import java.util.Arrays;
import java.util.List;

public class LogicDaybloom implements IGeneratingFlowerLogic {

    @Override
    public int getDefaultNeedTicks() {
        return FlowerLogicConfig.flowerLogicDaybloomTicks;
    }

    @Override
    public List<LevelTypes> getTypes() {
        return Arrays.asList(LevelTypes.ETERNAL_SOLSTICE);
    }

    @Override
    public long getGenerating() {
        return FlowerLogicConfig.flowerLogicDaybloomGenerating;
    }

    @Override
    public int getLevel() {
        return FlowerLogicConfig.flowerLogicDaybloomLevel;
    }
}
