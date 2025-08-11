package foxiwhitee.hellmod.network.packets;

import appeng.core.sync.network.INetworkInfo;
import cpw.mods.fml.common.network.ByteBufUtils;
import foxiwhitee.hellmod.container.terminals.ContainerPatternTerminal;
import foxiwhitee.hellmod.integration.draconic.container.ContainerDraconicAssembler;
import foxiwhitee.hellmod.network.BasePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PacketTerminalRecipes extends BasePacket {
    protected NBTTagCompound tag;
    public PacketTerminalRecipes(ByteBuf byteBuf) {
        super(byteBuf);
    }

    public PacketTerminalRecipes(NBTTagCompound compound) {
        ByteBufUtils.writeTag(getDataBuffer(), compound);
    }

    @Override
    public void handleServerSide(INetworkInfo network, BasePacket packet, EntityPlayer player) {
        tag = ByteBufUtils.readTag(getDataBuffer());
        if (player.openContainer instanceof ContainerPatternTerminal) {
            ((ContainerPatternTerminal)player.openContainer).getTerminal().readNEINBT(tag);
        }
    }

}
