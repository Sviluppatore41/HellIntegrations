package foxiwhitee.HellIntegrations.client.gui.interfaces;

import appeng.helpers.IInterfaceHost;
import foxiwhitee.HellIntegrations.container.interfaces.ContainerHybridInterface;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiHybridInterface extends GuiCustomInterface {
    public GuiHybridInterface(final InventoryPlayer inventoryPlayer, final IInterfaceHost te) {
        super(new ContainerHybridInterface(inventoryPlayer, te));
        this.ySize = 250;
    }

    @Override
    protected String getBackground() {
        return "gui/hybrid_interface.png";
    }

}
