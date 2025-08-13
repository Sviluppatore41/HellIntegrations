package foxiwhitee.HellIntegrations.integration.thaumcraft.parts;

import foxiwhitee.HellIntegrations.integration.thaumcraft.ThaumcraftIntegration;
import foxiwhitee.HellIntegrations.parts.PartPatternTerminal;
import net.minecraft.item.ItemStack;

public abstract class PartThaumcraftPatternTerminal extends PartPatternTerminal {

    public PartThaumcraftPatternTerminal(ItemStack is) {
        super(is);
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) { return new ItemStack(ThaumcraftIntegration.ITEM_PARTS_TERMINALS); }
}
