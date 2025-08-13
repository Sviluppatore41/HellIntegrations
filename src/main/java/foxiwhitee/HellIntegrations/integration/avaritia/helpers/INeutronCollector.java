package foxiwhitee.HellIntegrations.integration.avaritia.helpers;

import net.minecraft.item.ItemStack;

public interface INeutronCollector {
    ItemStack getStack();

    default int getTicks() {
        return 0;
    }
}