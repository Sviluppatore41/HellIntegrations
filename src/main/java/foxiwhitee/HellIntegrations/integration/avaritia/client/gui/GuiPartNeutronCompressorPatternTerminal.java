package foxiwhitee.HellIntegrations.integration.avaritia.client.gui;

import appeng.api.storage.ITerminalHost;
import foxiwhitee.HellIntegrations.client.gui.terminals.GuiPatternTerminal;
import foxiwhitee.HellIntegrations.integration.avaritia.container.ContainerPartNeutronCompressorPatternTerminal;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiPartNeutronCompressorPatternTerminal extends GuiPatternTerminal {

    public GuiPartNeutronCompressorPatternTerminal(InventoryPlayer inventoryPlayer, ITerminalHost te) {
        super(inventoryPlayer, te, new ContainerPartNeutronCompressorPatternTerminal(inventoryPlayer, te), 511, 250);
    }

    @Override
    protected String getBackground() {
        return "gui/gui_terminal_avaritia_pattern_neutron.png";
    }

}

