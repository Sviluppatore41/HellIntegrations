package foxiwhitee.HellIntegrations.integration.ic2.blocks;

import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.ic2.client.gui.GuiInfinityGenerator;
import foxiwhitee.HellIntegrations.integration.ic2.container.ContainerInfinityGenerator;
import foxiwhitee.HellIntegrations.integration.ic2.helpers.ISpecialSintezatorPanel;
import foxiwhitee.HellIntegrations.integration.ic2.tile.generators.infinity.TileInfinityGenerator;
import foxiwhitee.HellIntegrations.integration.ic2.tile.generators.infinity.TileQuantumGenerator;
import foxiwhitee.HellIntegrations.integration.ic2.tile.generators.infinity.TileSingularGenerator;
import foxiwhitee.HellIntegrations.utils.handler.GuiHandlers;
import foxiwhitee.HellIntegrations.utils.handler.SimpleGuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SimpleGuiHandler(index = GuiHandlers.infinityGenerator, tile = TileInfinityGenerator.class, gui = GuiInfinityGenerator.class, container = ContainerInfinityGenerator.class, integration = "IC2")
public class BlockInfinityGenerator extends BaseIC2Block implements ISpecialSintezatorPanel {
    private String name;
    public BlockInfinityGenerator(String name) {
        super(name);
        this.name = name;
        setBlockName(name);
        setHardness(3.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab((CreativeTabs) HellCore.HELL_TAB);
        setBlockTextureName(HellCore.MODID.toLowerCase() + ":generators/" + name);
    }

    public int getGen() {
        switch (name) {
            case "singularGenerator": return HellConfig.panel2GenDay;
            default: return HellConfig.panel1GenDay;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch (name) {
            case "singularGenerator": return new TileSingularGenerator();
            default: return new TileQuantumGenerator();
        }
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3) {
        if (player.isSneaking())
            return false;
        if (world.isRemote)
            return true;
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null)
            player.openGui(HellCore.instance, GuiHandlers.infinityGenerator, world, i, j, k);
        return true;
    }

    @Override
    public double getDayGen(ItemStack paramItemStack) {
        return getGen();
    }

    @Override
    public double getNightGen(ItemStack paramItemStack) {
        return getGen();
    }
}

