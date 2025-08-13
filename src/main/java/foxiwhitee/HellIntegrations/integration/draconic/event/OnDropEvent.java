package foxiwhitee.HellIntegrations.integration.draconic.event;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import foxiwhitee.HellIntegrations.integration.draconic.DraconicEvolutionIntegration;
import foxiwhitee.HellIntegrations.utils.localization.LocalizationUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.Iterator;

public class OnDropEvent {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onDropEvent(LivingDropsEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof com.brandon3055.draconicevolution.common.entity.EntityChaosGuardian) {
            Entity attacker = event.source.getEntity();
            if (attacker instanceof EntityPlayer)
                ((EntityPlayer)attacker).inventory.addItemStackToInventory(new ItemStack(DraconicEvolutionIntegration.CHAOTIC_HEART));
            Iterator i$ = event.entity.worldObj.playerEntities.iterator();
            while (i$.hasNext()) {
                Object o = i$.next();
                if (o instanceof EntityPlayer)
                    ((EntityPlayer)o).addChatComponentMessage((IChatComponent)new ChatComponentText(LocalizationUtils.localize("msg.de.dragonDeath.txt")));
            }
        }
    }
}
