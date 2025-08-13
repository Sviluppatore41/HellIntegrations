package foxiwhitee.HellIntegrations.integration.thaumcraft.items;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.utils.localization.LocalizationUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class ThaumBooks extends Item {
    public enum Type {
        RESEARCH, DISTORTION
    }
    private final Type typeBook;
    public ThaumBooks(String name, String texture, Type typeBook) {
        this.canRepair = false;
        this.setUnlocalizedName(name);
        this.setTextureName(HellCore.MODID.toLowerCase()+":"+texture);
        this.setCreativeTab(HellCore.HELL_TAB);
        this.maxStackSize = 1;
        this.setMaxDamage(1);
        this.typeBook = typeBook;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (typeBook == Type.RESEARCH) {
            par3List.add(LocalizationUtils.localize("tooltip.book.thaum.desc"));
        } else if (typeBook == Type.DISTORTION) {
            par3List.add(LocalizationUtils.localize("tooltip.book.distortion.desc"));
        } else {
            par3List.add(LocalizationUtils.localize("tooltip.book.error"));
        }

    }

    @Override
    @Subscribe
    public ItemStack onItemRightClick(ItemStack heldStack, World world, EntityPlayer player){
        if (!world.isRemote) {
            if (typeBook == Type.RESEARCH) {
                player.addChatMessage(new ChatComponentText(LocalizationUtils.localize("tooltip.book.thaum.message.1")));
                player.addChatMessage(new ChatComponentText(LocalizationUtils.localize("tooltip.book.thaum.message.2")));
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "tc research @p all");
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "tc aspect @p all 1");
            } else if (typeBook == Type.DISTORTION) {
                player.addChatMessage(new ChatComponentText(LocalizationUtils.localize("tooltip.book.distortion.message.1")));
                player.addChatMessage(new ChatComponentText(LocalizationUtils.localize("tooltip.book.distortion.message.2")));
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "tc warp @p set 0 perm");
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "tc warp @p set 0 temp");
                MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), "tc warp @p set 0 all");
            }

        }
        heldStack.damageItem(2, player);
        return super.onItemRightClick(heldStack, world, player);
    }
}