package foxiwhitee.HellIntegrations.utils.interfaces;

import appeng.helpers.IInterfaceHost;
import appeng.me.helpers.AENetworkProxy;

public class CustomAdvancedDualityInterface extends CustomDualityInterface {
    public static int getNumberOfPatternSlots() {
        return 18;
    }

    @Override
    protected String getLevel() {
        return "advanced";
    }

    public CustomAdvancedDualityInterface(AENetworkProxy networkProxy, IInterfaceHost ih) {
        super(networkProxy, ih);
    }
}
