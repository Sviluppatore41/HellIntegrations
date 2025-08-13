package foxiwhitee.HellIntegrations.client.gui;

import appeng.client.gui.AEBaseGui;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.container.ContainerCobblestoneDuper;
import net.minecraft.client.gui.GuiButton;

public class GuiCobblestoneDuper extends AEBaseGui {
    public GuiCobblestoneDuper(ContainerCobblestoneDuper container) {
        super(container);
        this.ySize = 199;
        this.xSize = 210;
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        super.actionPerformed(par1GuiButton);
    }

    public void initGui() {
        super.initGui();
    }

    public void drawFG(int offsetX, int offsetY, int mouseX, int mouseY) {}

    public void drawBG(int offsetX, int offsetY, int mouseX, int mouseY) {
        this.bindTexture(HellCore.MODID.toLowerCase(), this.getBackground());
        drawTexturedModalRect(offsetX, offsetY, 23, 28, this.xSize, this.ySize);
    }

    protected String getBackground() {
        return "gui/gui_cobblestone_duper.png";
    }

}
