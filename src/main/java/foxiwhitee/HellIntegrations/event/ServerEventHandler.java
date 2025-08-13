package foxiwhitee.HellIntegrations.event;

import appeng.me.storage.CellInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import foxiwhitee.HellIntegrations.items.fluid.ItemFluidDrop;
import foxiwhitee.HellIntegrations.utils.cells.CustomCellInventory;
import net.minecraft.item.Item;
import net.minecraftforge.event.world.WorldEvent;

public class ServerEventHandler {
    public static final foxiwhitee.HellIntegrations.integration.botania.event.ServerEventHandler INSTANCE = new foxiwhitee.HellIntegrations.integration.botania.event.ServerEventHandler();

    @SubscribeEvent
    public final void worldLoad(WorldEvent.Load e) {
        CellInventory.addBasicBlackList(Item.getIdFromItem(ItemFluidDrop.DROP), 32767);
        CustomCellInventory.addBasicBlackList(Item.getIdFromItem(ItemFluidDrop.DROP), 32767);
    }
}
