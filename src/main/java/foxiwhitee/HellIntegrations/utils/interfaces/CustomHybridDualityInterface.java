package foxiwhitee.HellIntegrations.utils.interfaces;

import appeng.helpers.IInterfaceHost;
import appeng.me.helpers.AENetworkProxy;

public class CustomHybridDualityInterface extends CustomDualityInterface {
    public static int getNumberOfPatternSlots() {
        return 27;
    }

    @Override
    protected String getLevel() {
        return "hybrid";
    }

    public CustomHybridDualityInterface(AENetworkProxy networkProxy, IInterfaceHost ih) {
        super(networkProxy, ih);
    }

}
