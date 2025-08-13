package foxiwhitee.hellmod.items;

import appeng.block.AEBaseItemBlock;
import appeng.me.helpers.IGridProxyable;
import foxiwhitee.hellmod.config.HellConfig;
import foxiwhitee.hellmod.ModBlocks;
import foxiwhitee.hellmod.integration.avaritia.AvaritiaIntegration;
import foxiwhitee.hellmod.integration.avaritia.blocks.BlockCustomNeutronCollector;
import foxiwhitee.hellmod.integration.botania.BotaniaIntegration;
import foxiwhitee.hellmod.integration.botania.blocks.BlockCustomManaPool;
import foxiwhitee.hellmod.integration.botania.blocks.BlockCustomSpreader;
import foxiwhitee.hellmod.integration.draconic.DraconicEvolutionIntegration;
import foxiwhitee.hellmod.integration.ic2.IC2Integration;
import foxiwhitee.hellmod.integration.ic2.blocks.BlockAutoReplicator;
import foxiwhitee.hellmod.integration.ic2.blocks.BlockCustomMatterGen;
import foxiwhitee.hellmod.integration.ic2.blocks.BlockCustomSolarPanel;
import foxiwhitee.hellmod.integration.thaumcraft.ThaumcraftIntegration;
import foxiwhitee.hellmod.utils.localization.LocalizationUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class ModItemBlock extends AEBaseItemBlock {
    private final Block blockType;

    public ModItemBlock(Block b) {
        super(b);
        this.blockType = b;
    }

    public String getUnlocalizedName() {
        return this.blockType.getUnlocalizedName();
    }

    public String getUnlocalizedName(ItemStack i) {
        return this.blockType.getUnlocalizedName();
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (!super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata))
            return false;
        if (this.blockType instanceof net.minecraft.block.ITileEntityProvider) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof IGridProxyable)
                ((IGridProxyable) tile).getProxy().setOwner(player);
        }
        return true;
    }

    @Override
    public void addCheckedInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean advancedToolTips) {
        super.addCheckedInformation(itemStack, player, list, advancedToolTips);
        if (HellConfig.enable_tooltips) {
            if (this.blockType.equals(ModBlocks.ADVANCED_INTERFACE)){
                list.add(LocalizationUtils.localize("tooltip.interface", 18));
            } else if (this.blockType.equals(ModBlocks.HYBRID_INTERFACE)){
                list.add(LocalizationUtils.localize("tooltip.interface", 27));
            } else if (this.blockType.equals(ModBlocks.ULTIMATE_INTERFACE)){
                list.add(LocalizationUtils.localize("tooltip.interface", 36));
            } else if (this.blockType.equals(ModBlocks.AUTO_CRYSTALLIZER)){
                list.add(LocalizationUtils.localize("tooltip.autoCrystallizer"));
            } else if (this.blockType.equals(ModBlocks.AUTO_PRESS)){
                list.add(LocalizationUtils.localize("tooltip.autoPress"));
            } else if (this.blockType.equals(DraconicEvolutionIntegration.AUTO_AWAKENER_BLOCKS)){
                list.add(LocalizationUtils.localize("tooltip.autoAwakener"));
            } else if (this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER1) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER2) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER3) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER4) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER5) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER6) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER7) ||
                    this.blockType.equals(IC2Integration.SOLAR_PANEL_TIER8)) {

                list.add(LocalizationUtils.localize("tooltip.panel.generation.day") + ((BlockCustomSolarPanel) (this.blockType)).getGenDay());
                list.add(LocalizationUtils.localize("tooltip.panel.generation.night") + ((BlockCustomSolarPanel) (this.blockType)).getGenNight());
                list.add(LocalizationUtils.localize("tooltip.panel.output") + ((BlockCustomSolarPanel) (this.blockType)).getGenDay() * 2);
                list.add(LocalizationUtils.localize("tooltip.panel.storage") + ((BlockCustomSolarPanel) (this.blockType)).getGenDay() * 10);
            } else if (this.blockType.equals(ThaumcraftIntegration.STABILIZER)) {
                list.add(LocalizationUtils.localize("tooltip.stabilizer"));
            } else if (this.blockType.equals(AvaritiaIntegration.BASIC_NEUTRON_COLLECTOR) ||
                    this.blockType.equals(AvaritiaIntegration.ADVANCEDN_NEUTRON_COLLECTOR) ||
                    this.blockType.equals(AvaritiaIntegration.HYBRID_NEUTRON_COLLECTOR) ||
                    this.blockType.equals(AvaritiaIntegration.ULTIMATE_NEUTRON_COLLECTOR) ||
                    this.blockType.equals(AvaritiaIntegration.QUANTIUM_NEUTRON_COLLECTOR)) {
                list.add(LocalizationUtils.localize("tooltip.neutronCollector", ((BlockCustomNeutronCollector)blockType).getStack().stackSize, ((BlockCustomNeutronCollector)blockType).getStack().getDisplayName(), ((BlockCustomNeutronCollector)blockType).getTicks()));
            } else if (this.blockType.equals(BotaniaIntegration.ASGARD_POOL) ||
                    this.blockType.equals(BotaniaIntegration.HELHELM_POOL) ||
                    this.blockType.equals(BotaniaIntegration.VALHALLA_POOL) ||
                    this.blockType.equals(BotaniaIntegration.MIDGARD_POOL)) {
                list.add(LocalizationUtils.localize("tooltip.customManaPool", ((BlockCustomManaPool)blockType).getMaxMana() / 1000000));
            } else if (this.blockType.equals(BotaniaIntegration.ASGARD_SPREADER) ||
                    this.blockType.equals(BotaniaIntegration.HELHELM_SPREADER) ||
                    this.blockType.equals(BotaniaIntegration.VALHALLA_SPREADER) ||
                    this.blockType.equals(BotaniaIntegration.MIDGARD_SPREADER)) {
                list.add(LocalizationUtils.localize("tooltip.customManaSpreader", ((BlockCustomSpreader)blockType).getManaPerSec()));
            } else if (this.blockType.equals(IC2Integration.ADVANCED_MATTER) ||
                    this.blockType.equals(IC2Integration.NANO_MATTER) ||
                    this.blockType.equals(IC2Integration.QUANTUM_MATTER)) {
                list.add(LocalizationUtils.localize("tooltip.customMatterGen.matter", ((BlockCustomMatterGen)blockType).getMatter()));
                list.add(LocalizationUtils.localize("tooltip.customMatterGen.tank", ((BlockCustomMatterGen)blockType).getTank()));
            } else if (this.blockType.equals(IC2Integration.ADVANCED_SCANNER)) {
                list.add(LocalizationUtils.localize("tooltip.advancedScanner"));
            } else if (this.blockType.equals(IC2Integration.ADVANCED_REPLICATOR) ||
                    this.blockType.equals(IC2Integration.NANO_REPLICATOR) ||
                    this.blockType.equals(IC2Integration.QUANTUM_REPLICATOR)) {
                list.add(LocalizationUtils.localize("tooltip.customReplicator.speed", ((BlockAutoReplicator)blockType).getItemsPerSec()));
                list.add(LocalizationUtils.localize("tooltip.customReplicator.discount", ((BlockAutoReplicator)blockType).getDiscount()).replace("PROS", "%"));
                list.add(LocalizationUtils.localize("tooltip.customReplicator.desc"));
            } else if (this.blockType.equals(ModBlocks.BASE_MOLECULAR_ASSEMBLER)) {
                list.add(LocalizationUtils.localize("tooltip.assembler.speed", HellConfig.basic_molecular_assembler_speed));
            } else if (this.blockType.equals(ModBlocks.HYBRID_MOLECULAR_ASSEMBLER)) {
                list.add(LocalizationUtils.localize("tooltip.assembler.speed", HellConfig.hybrid_molecular_assembler_speed));
            } else if (this.blockType.equals(ModBlocks.ULTIMATE_MOLECULAR_ASSEMBLER)) {
                list.add(LocalizationUtils.localize("tooltip.assembler.speed", HellConfig.ultimate_molecular_assembler_speed));
            } else if (this.blockType.equals(ModBlocks.COBBLESTONE_DUPER)) {
                list.add(LocalizationUtils.localize("tooltip.cobblestoneDuper"));
            }
        }
    }

}

