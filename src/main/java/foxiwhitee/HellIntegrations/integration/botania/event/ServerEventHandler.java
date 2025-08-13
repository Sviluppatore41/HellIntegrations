package foxiwhitee.HellIntegrations.integration.botania.event;

import appeng.me.storage.CellInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;
import foxiwhitee.HellIntegrations.utils.cells.CustomCellInventory;
import net.minecraft.item.Item;
import net.minecraftforge.event.world.WorldEvent;

public class ServerEventHandler {
    public static final ServerEventHandler INSTANCE = new ServerEventHandler();

    @SubscribeEvent
    public final void worldLoad(WorldEvent.Load e) {
        CellInventory.addBasicBlackList(Item.getIdFromItem(BotaniaIntegration.MANA_DROP), 32767);
        CustomCellInventory.addBasicBlackList(Item.getIdFromItem(BotaniaIntegration.MANA_DROP), 32767);
    }
}
