package foxiwhitee.HellIntegrations.client.gui;

import foxiwhitee.HellIntegrations.container.ContainerMEServer;

public class GuiMEServer extends HellBaseGui{
    public GuiMEServer(ContainerMEServer container) {
        super(container, 316, 274);
    }

    @Override
    protected String getBackground() {
        return "gui/gui_me_server.png";
    }
}
