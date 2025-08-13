package foxiwhitee.HellIntegrations.integration.draconic.items;

import foxiwhitee.HellIntegrations.helpers.IAutomatedBlockUpgrade;
import foxiwhitee.HellIntegrations.items.DefaultItem;

public class ItemChaosUpgrade extends DefaultItem implements IAutomatedBlockUpgrade {
    public ItemChaosUpgrade(String name, String texture) {
        super(name, texture);
        this.setMaxStackSize(1);
    }
}
