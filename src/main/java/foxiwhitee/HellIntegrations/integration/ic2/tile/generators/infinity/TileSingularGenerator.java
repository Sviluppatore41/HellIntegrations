package foxiwhitee.HellIntegrations.integration.ic2.tile.generators.infinity;

import foxiwhitee.HellIntegrations.config.HellConfig;

public class TileSingularGenerator extends TileInfinityGenerator{
    @Override
    public int getGen() {
        return HellConfig.singularGeneratorGeneration;
    }

    @Override
    public int getMaxStorage() {
        return HellConfig.singularGeneratorGeneration * 10;
    }

    @Override
    public int getProduction() {
        return HellConfig.singularGeneratorGeneration * 2;
    }

    @Override
    public String getName() {
        return "singularGenerator";
    }
}
