package foxiwhitee.HellIntegrations.utils.interfaces;

import appeng.helpers.IInterfaceHost;
import appeng.me.helpers.AENetworkProxy;

public class CustomUltimateDualityInterface extends CustomDualityInterface {
    public static int getNumberOfPatternSlots() {
        return 36;
    }

    @Override
    protected String getLevel() {
        return "ultimate";
    }

    public CustomUltimateDualityInterface(AENetworkProxy proxy, IInterfaceHost host) {
        super(proxy, host);
    }
}
