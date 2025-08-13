package foxiwhitee.hellmod.integration.ic2.blocks;

import appeng.block.AEBaseBlock;
import appeng.block.AEBaseTileBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.hellmod.config.HellConfig;
import foxiwhitee.hellmod.HellCore;
import foxiwhitee.hellmod.integration.ic2.client.gui.GuiCustomSolarPanel;
import foxiwhitee.hellmod.integration.ic2.container.ContainerCustomSolarPanel;
import foxiwhitee.hellmod.integration.ic2.helpers.ISpecialSintezatorPanel;
import foxiwhitee.hellmod.integration.ic2.tile.generators.panels.*;
import foxiwhitee.hellmod.utils.handler.GuiHandlers;
import foxiwhitee.hellmod.utils.handler.SimpleGuiHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SimpleGuiHandler(index = GuiHandlers.customSolarPanel, tile = TileCustomSolarPanel.class, gui = GuiCustomSolarPanel.class, container = ContainerCustomSolarPanel.class, integration = "IC2")
public class BlockCustomSolarPanel extends AEBaseTileBlock implements ISpecialSintezatorPanel {
    @SideOnly(Side.CLIENT)
    private IIcon topIcon, downIcon;
    private String name;
    public BlockCustomSolarPanel(String name) {
        super(Material.rock);
        this.name = name;
        setBlockName(name);
        setHardness(3.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab((CreativeTabs) HellCore.HELL_TAB);
        Class<? extends TileEntity> tile;
        switch (name) {
            case "panel2": tile = TileSolarPanelLevel2.class;
            case "panel3": tile = TileSolarPanelLevel3.class;
            case "panel4": tile = TileSolarPanelLevel4.class;
            case "panel5": tile = TileSolarPanelLevel5.class;
            case "panel6": tile = TileSolarPanelLevel6.class;
            case "panel7": tile = TileSolarPanelLevel7.class;
            case "panel8": tile = TileSolarPanelLevel8.class;
            default: tile = TileSolarPanelLevel1.class;
        }
        setTileEntity(tile);
    }

    public int getGenDay() {
        switch (name) {
            case "panel2": return HellConfig.panel2GenDay;
            case "panel3": return HellConfig.panel3GenDay;
            case "panel4": return HellConfig.panel4GenDay;
            case "panel5": return HellConfig.panel5GenDay;
            case "panel6": return HellConfig.panel6GenDay;
            case "panel7": return HellConfig.panel7GenDay;
            case "panel8": return HellConfig.panel8GenDay;
            default: return HellConfig.panel1GenDay;
        }
    }

    public int getGenNight() {
        switch (name) {
            case "panel2": return HellConfig.panel2GenNight;
            case "panel3": return HellConfig.panel3GenNight;
            case "panel4": return HellConfig.panel4GenNight;
            case "panel5": return HellConfig.panel5GenNight;
            case "panel6": return HellConfig.panel6GenNight;
            case "panel7": return HellConfig.panel7GenNight;
            case "panel8": return HellConfig.panel8GenNight;
            default: return HellConfig.panel1GenNight;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {

        switch(side) {
            case 0:
                return downIcon;
            case 1:
                return topIcon;
            default:
                return blockIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        switch(side) {
            case 0:
                return downIcon;
            case 1:
                return topIcon;
            default:
                return blockIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        blockIcon = register.registerIcon( HellCore.MODID+ ":panels/" + this.getUnlocalizedName().replace("tile.", "") + "Side");
        topIcon = register.registerIcon(HellCore.MODID+ ":panels/" + this.getUnlocalizedName().replace("tile.", "") + "Top");
        downIcon = register.registerIcon(HellCore.MODID+ ":panels/bottom");
    }

    @Override
    public boolean onActivated(World w, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (player.isSneaking())
            return false;
        if (w.isRemote)
            return true;
        TileEntity tileentity = w.getTileEntity(x, y, z);
        if (tileentity != null)
            player.openGui(HellCore.instance, GuiHandlers.customSolarPanel, w, x, y, z);
        return true;
    }

    @Override
    public double getDayGen(ItemStack paramItemStack) {
        return getGenDay();
    }

    @Override
    public double getNightGen(ItemStack paramItemStack) {
        return getGenNight();
    }
}

