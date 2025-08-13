package foxiwhitee.HellIntegrations.tile.interfaces;

import appeng.tile.misc.TileInterface;
import foxiwhitee.HellIntegrations.helpers.IInterfaceTerminalSupport;
import foxiwhitee.HellIntegrations.utils.interfaces.CustomAdvancedDualityInterface;
import foxiwhitee.HellIntegrations.utils.interfaces.ICustomTileInterface;

public class TileAdvancedInterface extends TileInterface {
    public TileAdvancedInterface() {
        super();
        ((ICustomTileInterface) this).setDuality(new CustomAdvancedDualityInterface(this.getProxy(), this));
    }

    public IInterfaceTerminalSupport.PatternsConfiguration[] getPatternsConfigurations() {
        return new IInterfaceTerminalSupport.PatternsConfiguration[] {
                new IInterfaceTerminalSupport.PatternsConfiguration(0, 9),
                new IInterfaceTerminalSupport.PatternsConfiguration(9, 9)
        };
    }

}
