package foxiwhitee.HellIntegrations.tile.interfaces;

import appeng.tile.misc.TileInterface;
import foxiwhitee.HellIntegrations.helpers.IInterfaceTerminalSupport;
import foxiwhitee.HellIntegrations.utils.interfaces.CustomUltimateDualityInterface;
import foxiwhitee.HellIntegrations.utils.interfaces.ICustomTileInterface;

public class TileUltimateInterface extends TileInterface {
    public TileUltimateInterface() {
        super();
        ((ICustomTileInterface) this).setDuality(new CustomUltimateDualityInterface(this.getProxy(), this));
    }

    public IInterfaceTerminalSupport.PatternsConfiguration[] getPatternsConfigurations() {
        return new IInterfaceTerminalSupport.PatternsConfiguration[] {
                new IInterfaceTerminalSupport.PatternsConfiguration(0, 9),
                new IInterfaceTerminalSupport.PatternsConfiguration(9, 9),
                new IInterfaceTerminalSupport.PatternsConfiguration(18, 9),
                new IInterfaceTerminalSupport.PatternsConfiguration(27, 9) };
    }
}
