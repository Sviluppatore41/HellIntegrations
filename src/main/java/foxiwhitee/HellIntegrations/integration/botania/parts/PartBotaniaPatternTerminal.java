package foxiwhitee.HellIntegrations.integration.botania.parts;

import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;
import foxiwhitee.HellIntegrations.parts.PartPatternTerminal;
import net.minecraft.item.ItemStack;

public abstract class PartBotaniaPatternTerminal extends PartPatternTerminal {

    public PartBotaniaPatternTerminal(ItemStack is) {
        super(is);
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) { return new ItemStack(BotaniaIntegration.ITEM_PARTS_TERMINALS); }
}
