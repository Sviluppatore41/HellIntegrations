package foxiwhitee.HellIntegrations.integration.botania.client.gui;


import appeng.api.storage.ITerminalHost;
import foxiwhitee.HellIntegrations.client.gui.terminals.GuiPatternTerminal;
import foxiwhitee.HellIntegrations.integration.botania.container.ContainerPartManaPoolPatternTerminal;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiPartManaPoolPatternTerminal extends GuiPatternTerminal {
    public GuiPartManaPoolPatternTerminal(InventoryPlayer inventoryPlayer, ITerminalHost te) {
        super(inventoryPlayer, te, new ContainerPartManaPoolPatternTerminal(inventoryPlayer, te), 511, 250);
    }

    @Override
    protected String getBackground() {
        return "gui/gui_terminal_botania_pattern_mana_pool.png";
    }

}

