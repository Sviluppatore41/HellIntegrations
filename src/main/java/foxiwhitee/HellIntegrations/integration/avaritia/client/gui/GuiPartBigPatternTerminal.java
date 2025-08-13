package foxiwhitee.HellIntegrations.integration.avaritia.client.gui;

import appeng.api.storage.ITerminalHost;

import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.client.gui.terminals.GuiPatternTerminal;
import foxiwhitee.HellIntegrations.integration.avaritia.container.ContainerPartBigPatternTerminal;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPartBigPatternTerminal extends GuiPatternTerminal {
    private static final ResourceLocation GUI = new ResourceLocation(HellCore.MODID.toLowerCase(), "textures/gui/gui_terminal_avaritia_pattern_big_1.png");

    public GuiPartBigPatternTerminal(InventoryPlayer inventoryPlayer, ITerminalHost te) {
        super(inventoryPlayer, te, new ContainerPartBigPatternTerminal(inventoryPlayer, te), 550, 250);
        this.hasSubstitutions = true;
    }

    @Override
    protected ResourceLocation getBackgroundLocation() {
        return GUI;
    }

    protected String getBackground() {
        return "gui/gui_terminal_avaritia_pattern_big_1.png";
    }

}
