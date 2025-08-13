package foxiwhitee.HellIntegrations;

import foxiwhitee.HellIntegrations.blocks.*;
import foxiwhitee.HellIntegrations.blocks.assemblers.BlockBaseMolecularAssembler;
import foxiwhitee.HellIntegrations.blocks.assemblers.BlockHybridMolecularAssembler;
import foxiwhitee.HellIntegrations.blocks.assemblers.BlockUltimateMolecularAssembler;
import foxiwhitee.HellIntegrations.blocks.cpu.BlockCustomAccelerators;
import foxiwhitee.HellIntegrations.blocks.cpu.BlockCustomCraftingStorage;
import foxiwhitee.HellIntegrations.blocks.cpu.BlockMEServer;
import foxiwhitee.HellIntegrations.blocks.interfaces.BlockAdvancedInterface;
import foxiwhitee.HellIntegrations.blocks.interfaces.BlockHybridInterface;
import foxiwhitee.HellIntegrations.blocks.interfaces.BlockUltimateInterface;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.items.ItemBlockCustomEnergyCell;
import foxiwhitee.HellIntegrations.items.ItemBlockAAccelerators;
import foxiwhitee.HellIntegrations.tile.TileAdvancedDrive;
import foxiwhitee.HellIntegrations.tile.TileAutoCrystallizer;
import foxiwhitee.HellIntegrations.tile.TileAutoPress;
import foxiwhitee.HellIntegrations.tile.TileCobblestoneDuper;
import foxiwhitee.HellIntegrations.tile.cpu.TileCustomAccelerators;
import foxiwhitee.HellIntegrations.tile.cpu.TileCustomCraftingStorage;
import foxiwhitee.HellIntegrations.tile.cpu.TileMEServer;
import foxiwhitee.HellIntegrations.tile.fluid.TileFluidSupplier;
import foxiwhitee.HellIntegrations.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;


public class ModBlocks {

    public static final Block ADVANCED_ACCELERATORS = new BlockCustomAccelerators().setCreativeTab(HellCore.HELL_TAB);

    public static final Block ADVANCED_INTERFACE = new BlockAdvancedInterface();
    public static final Block HYBRID_INTERFACE = new BlockHybridInterface();
    public static final Block ULTIMATE_INTERFACE = new BlockUltimateInterface();

    public static final Block AUTO_CRYSTALLIZER = new BlockAutoCrystallizer("autoCrystallizer");
    public static final Block AUTO_PRESS = new BlockAutoPress("autoPress");

    public static final Block COBBLESTONE_DUPER = new BlockCobblestoneDuper("cobblestoneDuper");

    public static final Block BASE_MOLECULAR_ASSEMBLER = new BlockBaseMolecularAssembler("baseMolecularAssembler");
    public static final Block HYBRID_MOLECULAR_ASSEMBLER = new BlockHybridMolecularAssembler("hybridMolecularAssembler");
    public static final Block ULTIMATE_MOLECULAR_ASSEMBLER = new BlockUltimateMolecularAssembler("ultimateMolecularAssembler");

    public static final Block ADVANCED_ENERGY_CELL = new BlockCustomEnergyCell("advancedEnergyCell");
    public static final Block HYBRID_ENERGY_CELL = new BlockCustomEnergyCell("hybridEnergyCell");
    public static final Block ULTIMATE_ENERGY_CELL = new BlockCustomEnergyCell("ultimateEnergyCell");

    public static final Block WIRELESS_ALITE = new BlockCustomWireless("wirelessAlite");
    public static final Block WIRELESS_BIMARE = new BlockCustomWireless("wirelessBimare");
    public static final Block WIRELESS_DEFIT = new BlockCustomWireless("wirelessDefit");
    public static final Block WIRELESS_EFRIM = new BlockCustomWireless("wirelessEfrim");
    public static final Block WIRELESS_NUR = new BlockCustomWireless("wirelessNur");
    public static final Block WIRELESS_XAUR = new BlockCustomWireless("wirelessXaur");

    public static final Block ADVANCED_DRIVER = new BlockAdvancedDriver("advancedDriver");
    public static final Block ME_SERVER = new BlockMEServer("meServer");

    public static final Block FLUID_RECEIVER = new BlockFluidReceiver("fluidReceiver");
    public static final Block FLUID_SUPPLIER = new BlockAENetwork("fluidSupplier", TileFluidSupplier.class);

    public static void registerBlocks() {
        if (ContentConfig.enableInterfaces) {
            RegisterUtils.registerBlocks(ADVANCED_INTERFACE, HYBRID_INTERFACE, ULTIMATE_INTERFACE);
            RegisterUtils.findClasses("foxiwhitee.HellIntegrations.tile.interfaces", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableAutoCrystallizer) {
            RegisterUtils.registerBlock(AUTO_CRYSTALLIZER);
            RegisterUtils.registerTile(TileAutoCrystallizer.class);
        }
        if (ContentConfig.enableAutoPress) {
            RegisterUtils.registerBlock(AUTO_PRESS);
            RegisterUtils.registerTile(TileAutoPress.class);
        }
        if (ContentConfig.enableMEServer) {
            RegisterUtils.registerBlock(ME_SERVER);
            RegisterUtils.registerTile(TileMEServer.class);
        }
        if (ContentConfig.enableAdvancedDriver) {
            RegisterUtils.registerBlock(ADVANCED_DRIVER);
            RegisterUtils.registerTile(TileAdvancedDrive.class);
        }
        if (ContentConfig.enableAccelerators) {
            RegisterUtils.registerBlock(ADVANCED_ACCELERATORS, ItemBlockAAccelerators.class);
            RegisterUtils.registerTile(TileCustomAccelerators.class);
        }
        if (ContentConfig.enableCraftingStorages) {
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage256K", 262144);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage1M", 1048576);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage4M", 4194304);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage16M", 16777216);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage65M", 67108864);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage262M", 268435456);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage1G", 1073741824);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage4G", 4294967296L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage16G", 17179869184L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage67G", 68719476736L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage268G", 274877906944L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage1T", 1099511627776L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage4T", 4398046511104L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage17T", 17592186044416L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage68T", 70368744177664L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage274T", 281474976710656L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage1P", 1125899906842624L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage4P", 4503599627370496L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage17P", 1794402976530432L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage70P", 72057594037927936L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage281P", 288230376151711744L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage1E", 1152921504606846976L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage4E", 4611686018427387904L);
            BlockCustomCraftingStorage.registerCraftingStorage("blockCraftingStorage9E", Long.MAX_VALUE);
            BlockCustomCraftingStorage.registerBlocks();
            RegisterUtils.registerTile(TileCustomCraftingStorage.class);
        }
        if (ContentConfig.enableCobblestoneDuper) {
            RegisterUtils.registerBlock(COBBLESTONE_DUPER);
            RegisterUtils.registerTile(TileCobblestoneDuper.class);
        }
        if (ContentConfig.enableMolecularAssemblers) {
            RegisterUtils.registerBlocks(BASE_MOLECULAR_ASSEMBLER, HYBRID_MOLECULAR_ASSEMBLER, ULTIMATE_MOLECULAR_ASSEMBLER);
            RegisterUtils.findClasses("foxiwhitee.HellIntegrations.tile.assemblers", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableEnergyCells) {
            RegisterUtils.registerBlocks(ItemBlockCustomEnergyCell.class, ADVANCED_ENERGY_CELL, HYBRID_ENERGY_CELL, ULTIMATE_ENERGY_CELL);
            RegisterUtils.findClasses("foxiwhitee.HellIntegrations.tile.energycell", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableWireless) {
            RegisterUtils.registerBlocks(WIRELESS_ALITE, WIRELESS_BIMARE, WIRELESS_DEFIT, WIRELESS_EFRIM, WIRELESS_NUR, WIRELESS_XAUR);
            RegisterUtils.findClasses("foxiwhitee.HellIntegrations.tile.wireless", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableMEFluid) {
            RegisterUtils.registerBlocks(FLUID_RECEIVER, FLUID_SUPPLIER);
            RegisterUtils.findClasses("foxiwhitee.HellIntegrations.tile.fluid", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
    }
}
