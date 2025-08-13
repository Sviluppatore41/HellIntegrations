package foxiwhitee.HellIntegrations.integration.draconic.items;

import foxiwhitee.HellIntegrations.helpers.IAutomatedBlockUpgrade;
import foxiwhitee.HellIntegrations.items.DefaultItem;

public class ItemArialUpgrade extends DefaultItem implements IAutomatedBlockUpgrade {

    public ItemArialUpgrade(String name, String texture) {
        super(name, texture);
        this.setMaxStackSize(1);
    }
}
