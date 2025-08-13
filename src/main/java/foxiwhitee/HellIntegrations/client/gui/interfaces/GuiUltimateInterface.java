package foxiwhitee.HellIntegrations.client.gui.interfaces;

import appeng.helpers.IInterfaceHost;
import foxiwhitee.HellIntegrations.container.interfaces.ContainerUltimateInterface;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiUltimateInterface extends GuiCustomInterface {
    public GuiUltimateInterface(final InventoryPlayer inventoryPlayer, final IInterfaceHost te) {
        super(new ContainerUltimateInterface(inventoryPlayer, te));
        this.ySize = 250;
    }

    @Override
    protected String getBackground() {
        return "gui/ultimate_interface.png";
    }

}
