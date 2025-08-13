package foxiwhitee.HellIntegrations.integration.ic2.tile.replicators;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.ic2.IC2Integration;
import net.minecraft.item.ItemStack;

public class TileAutoQuantumReplicator extends TileAutoReplicator{
    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(IC2Integration.QUANTUM_REPLICATOR);
    }

    @Override
    public String getName() {
        return "quantum_replicator";
    }

    @Override
    public byte getDiscount() {
        return HellConfig.quantum_replicator_discount;
    }

    @Override
    public long getItemsPerSec() {
        return HellConfig.quantum_replicator_speed;
    }
}
