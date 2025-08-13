package foxiwhitee.HellIntegrations.blocks;

import appeng.block.AEBaseTileBlock;
import appeng.core.features.AEFeature;
import appeng.helpers.AEGlassMaterial;
import appeng.tile.networking.TileEnergyCell;
import appeng.util.Platform;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.client.render.RenderBlockCustomEnergyCell;
import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.tile.energycell.TileAdvancedEnergyCell;
import foxiwhitee.HellIntegrations.tile.energycell.TileHybridEnergyCell;
import foxiwhitee.HellIntegrations.tile.energycell.TileUltimateEnergyCell;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import java.util.EnumSet;
import java.util.List;

public class BlockCustomEnergyCell extends AEBaseTileBlock {
    private String name;
    private IIcon[] icons = new IIcon[8];

    public BlockCustomEnergyCell(String name) {
        super(AEGlassMaterial.INSTANCE);
        this.name = name;
        this.setBlockName(name);
        this.setTileEntity(getTileEntityClass());
        this.setFeature(EnumSet.of(AEFeature.Core));
    }

    @SideOnly(Side.CLIENT)
    protected RenderBlockCustomEnergyCell getRenderer() {
        return new RenderBlockCustomEnergyCell();
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        switch (name) {
            case "advanced_energy_cell": return TileAdvancedEnergyCell.class;
            case "hybrid_energy_cell": return TileHybridEnergyCell.class;
            case "ultimate_energy_cell": return TileUltimateEnergyCell.class;
            default: return TileEnergyCell.class;
        }
    }

    public void registerBlockIcons(IIconRegister register) {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = register.registerIcon( HellCore.MODID.toLowerCase() + ":ae2/energycells/" + name + "_" + i);
        }
    }

    public IIcon getIcon(int direction, int metadata) {
        return icons[metadata];
    }

    @SideOnly(Side.CLIENT)
    public void getCheckedSubBlocks(Item item, CreativeTabs tabs, List<ItemStack> itemStacks) {
        super.getCheckedSubBlocks(item, tabs, itemStacks);
        ItemStack charged = new ItemStack(this, 1);
        NBTTagCompound tag = Platform.openNbtData(charged);
        tag.setDouble("internalCurrentPower", this.getMaxPower());
        tag.setDouble("internalMaxPower", this.getMaxPower());
        itemStacks.add(charged);
    }

    public double getMaxPower() {
        switch (name) {
            case "advanced_energy_cell": return HellConfig.advancedEnergyCellPower;
            case "hybrid_energy_cell": return HellConfig.hybridEnergyCellPower;
            case "ultimate_energy_cell": return HellConfig.ultimateEnergyCellPower;
            default: return 200000.0D;
        }
    }

}
