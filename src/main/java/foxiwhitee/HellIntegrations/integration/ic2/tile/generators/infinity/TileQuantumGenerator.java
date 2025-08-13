package foxiwhitee.HellIntegrations.integration.ic2.tile.generators.infinity;

import foxiwhitee.HellIntegrations.config.HellConfig;

public class TileQuantumGenerator extends TileInfinityGenerator{
    @Override
    public int getGen() {
        return HellConfig.quantumGeneratorGeneration;
    }

    @Override
    public int getMaxStorage() {
        return HellConfig.quantumGeneratorGeneration * 10;
    }

    @Override
    public int getProduction() {
        return HellConfig.quantumGeneratorGeneration * 2;
    }

    @Override
    public String getName() {
        return "quantumGenerator";
    }
}
