package foxiwhitee.HellIntegrations.integration.avaritia.parts;

import foxiwhitee.HellIntegrations.integration.avaritia.AvaritiaIntegration;
import foxiwhitee.HellIntegrations.parts.PartPatternTerminal;
import net.minecraft.item.ItemStack;

public abstract class PartAvaritiaPatternTerminal extends PartPatternTerminal {

    public PartAvaritiaPatternTerminal(ItemStack is) {
        super(is);
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) { return new ItemStack(AvaritiaIntegration.ITEM_PARTS_TERMINALS); }
}
