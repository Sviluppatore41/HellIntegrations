package foxiwhitee.HellIntegrations.client.gui.interfaces;

import appeng.helpers.IInterfaceHost;
import foxiwhitee.HellIntegrations.container.interfaces.ContainerAdvancedInterface;
import net.minecraft.entity.player.InventoryPlayer;


public class GuiAdvancedInterface extends GuiCustomInterface {
    public GuiAdvancedInterface(final InventoryPlayer inventoryPlayer, final IInterfaceHost te) {
        super(new ContainerAdvancedInterface(inventoryPlayer, te));
        this.ySize = 229;
    }

    @Override
    protected String getBackground() {
        return "gui/advanced_interface.png";
    }

}
