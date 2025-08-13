package foxiwhitee.HellIntegrations.integration.avaritia.client.gui;

import appeng.client.gui.AEBaseGui;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.integration.avaritia.container.ContainerNeutronUnifier;
import foxiwhitee.HellIntegrations.utils.helpers.UtilGui;

public class GuiNeutronUnifier extends AEBaseGui {
    public GuiNeutronUnifier(ContainerNeutronUnifier c) {
        super(c);
        this.xSize = 210;
        this.ySize = 199;
    }

    public void drawFG(int offsetX, int offsetY, int mouseX, int mouseY) {}

    public void drawBG(int offsetX, int offsetY, int mouseX, int mouseY) {
        bindTexture(HellCore.MODID.toLowerCase(), "gui/gui_neutron_unifier.png");
        UtilGui.drawTexture(offsetX - 17, offsetY + 7, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
        if (drawUpgrades())
            UtilGui.drawTexture(offsetX + xSize - 15, offsetY + 7 , 211, 0, 35, 104, 35, 104);
    }

    protected boolean drawUpgrades() {
        return false;
    }
}