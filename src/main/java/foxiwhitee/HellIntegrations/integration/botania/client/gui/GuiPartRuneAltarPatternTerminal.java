package foxiwhitee.HellIntegrations.integration.botania.client.gui;


import appeng.api.storage.ITerminalHost;
import foxiwhitee.HellIntegrations.client.gui.terminals.GuiPatternTerminal;
import foxiwhitee.HellIntegrations.integration.botania.container.ContainerPartRuneAltarPatternTerminal;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiPartRuneAltarPatternTerminal extends GuiPatternTerminal {

    public GuiPartRuneAltarPatternTerminal(InventoryPlayer inventoryPlayer, ITerminalHost te) {
        super(inventoryPlayer, te, new ContainerPartRuneAltarPatternTerminal(inventoryPlayer, te), 511, 250);
        this.hasCycleItems = true;
        this.cycleItemsCordX = 403;
        this.cycleItemsCordY = 90;
    }

    @Override
    protected String getBackground() {
        return "gui/gui_terminal_botania_pattern_rune_altar.png";
    }

}

