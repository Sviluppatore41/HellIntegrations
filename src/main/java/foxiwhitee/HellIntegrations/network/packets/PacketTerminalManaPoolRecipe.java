package foxiwhitee.HellIntegrations.network.packets;

import appeng.core.sync.network.INetworkInfo;
import foxiwhitee.HellIntegrations.integration.botania.container.ContainerPartManaPoolPatternTerminal;
import foxiwhitee.HellIntegrations.network.BasePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PacketTerminalManaPoolRecipe extends PacketTerminalRecipes {
    public PacketTerminalManaPoolRecipe(ByteBuf byteBuf) {
        super(byteBuf);
    }

    public PacketTerminalManaPoolRecipe(NBTTagCompound compound) {
        super(compound);
    }

    @Override
    public void handleServerSide(INetworkInfo network, BasePacket packet, EntityPlayer player) {
        super.handleServerSide(network, packet, player);
        if (tag.hasKey("catalist") && player.openContainer instanceof ContainerPartManaPoolPatternTerminal) {
            ((ContainerPartManaPoolPatternTerminal)player.openContainer).setCraftType(tag.getInteger("catalist"));
        }
    }
}
