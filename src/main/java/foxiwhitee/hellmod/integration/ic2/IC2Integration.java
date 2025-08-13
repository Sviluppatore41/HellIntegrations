package foxiwhitee.hellmod.integration.ic2;

import appeng.api.AEApi;
import appeng.api.config.SecurityPermissions;
import appeng.client.texture.CableBusTextures;
import appeng.core.sync.GuiBridge;
import appeng.core.sync.GuiHostType;
import appeng.items.misc.ItemEncodedPattern;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.hellmod.HellCore;
import foxiwhitee.hellmod.ModBlocks;
import foxiwhitee.hellmod.blocks.BaseBlock;
import foxiwhitee.hellmod.client.render.assemblers.RenderBlockBaseMolecularAssembler;
import foxiwhitee.hellmod.client.render.assemblers.RenderItemBaseMolecularAssembler;
import foxiwhitee.hellmod.config.ContentConfig;
import foxiwhitee.hellmod.integration.ic2.blocks.*;
import foxiwhitee.hellmod.integration.IIntegration;
import foxiwhitee.hellmod.integration.Integration;
import foxiwhitee.hellmod.integration.ic2.client.render.RenderBlockEUProvider;
import foxiwhitee.hellmod.integration.ic2.client.render.RenderItemEUProvider;
import foxiwhitee.hellmod.integration.ic2.helpers.IEUEnergyGrid;
import foxiwhitee.hellmod.integration.ic2.items.*;
import foxiwhitee.hellmod.integration.ic2.me.EUEnergyGrid;
import foxiwhitee.hellmod.integration.ic2.tile.*;
import foxiwhitee.hellmod.integration.thaumcraft.tile.TileStabilizer;
import foxiwhitee.hellmod.items.ModItemBlock;
import foxiwhitee.hellmod.tile.assemblers.TileBaseMolecularAssembler;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;

@Integration(modid = "IC2")
public class IC2Integration implements IIntegration {

    public static final Item ENERGY_UPGRADES = new ItemEUEnergyUpgrades("energyUpgrades");
    public static final Item ENERGY_STORAGE_UPGRADES = new ItemEUEnergyStorageUpgrades("energyStorageUpgrades");
    public static final Item SUN_UPGRADE = new ItemSunUpgrade("sunUpgrade");

    public static final Block BLOOD_CASING = new BaseBlock("bloodCasing");
    public static final Block NANO_CASING = new BaseBlock("nanoCasing");
    public static final Block QUANTUM_CASING = new BaseBlock("quantumCasing");

    public static final Block ADVANCED_MATTER = new BlockCustomMatterGen("advancedMatter");
    public static final Block NANO_MATTER = new BlockCustomMatterGen("nanoMatter");
    public static final Block QUANTUM_MATTER = new BlockCustomMatterGen("quantumMatter");

    public static final Block ADVANCED_SCANNER = new BlockAdvancedScanner("advancedScanner");

    public static final Block ADVANCED_REPLICATOR = new BlockAutoReplicator("advancedReplicator");
    public static final Block NANO_REPLICATOR = new BlockAutoReplicator("nanoReplicator");
    public static final Block QUANTUM_REPLICATOR = new BlockAutoReplicator("quantumReplicator");

    public static final Block SOLAR_PANEL_TIER1 = new BlockCustomSolarPanel("solarPanelTier1");
    public static final Block SOLAR_PANEL_TIER2 = new BlockCustomSolarPanel("solarPanelTier2");
    public static final Block SOLAR_PANEL_TIER3 = new BlockCustomSolarPanel("solarPanelTier3");
    public static final Block SOLAR_PANEL_TIER4 = new BlockCustomSolarPanel("solarPanelTier4");
    public static final Block SOLAR_PANEL_TIER5 = new BlockCustomSolarPanel("solarPanelTier5");
    public static final Block SOLAR_PANEL_TIER6 = new BlockCustomSolarPanel("solarPanelTier6");
    public static final Block SOLAR_PANEL_TIER7 = new BlockCustomSolarPanel("solarPanelTier7");
    public static final Block SOLAR_PANEL_TIER8 = new BlockCustomSolarPanel("solarPanelTier8");

    public static final Block QUANTUM_GENERATOR = new BlockInfinityGenerator("quantumGenerator");
    public static final Block SINGULAR_GENERATOR = new BlockInfinityGenerator("singularGenerator");

    public static final Block MATTER_UNIFIER = new BlockMatterUnifier("matterUnifier");
    public static final Block QUANTUM_MATTER_UNIFIER = new BlockQuantumMatterUnifier("quantumMatterUnifier");

    public static final Block SYNTHESIZER = new BlockSynthesizer("synthesizer");

    public static final Block ENERGY_PROVIDER = new BlockEUProvider("energyProvider");

    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableSolarPanels) {
            RegisterUtils.registerBlocks(SOLAR_PANEL_TIER1, SOLAR_PANEL_TIER2, SOLAR_PANEL_TIER3, SOLAR_PANEL_TIER4, SOLAR_PANEL_TIER5, SOLAR_PANEL_TIER6, SOLAR_PANEL_TIER7, SOLAR_PANEL_TIER8);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.ic2.tile.generators.panels", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableQuantumGenerators) {
            RegisterUtils.registerBlocks(ItemBlock.class, QUANTUM_GENERATOR, SINGULAR_GENERATOR);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.ic2.tile.generators.infinity", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableSynthesizer) {
            RegisterUtils.registerBlock(SYNTHESIZER);
            RegisterUtils.registerTile(TileSynthesizer.class);
            RegisterUtils.registerItems(SUN_UPGRADE, ENERGY_UPGRADES);
        }
        if (ContentConfig.enableCasings) {
            RegisterUtils.registerBlocks( NANO_CASING, QUANTUM_CASING);

            if (HellCore.BloodMagic) {
                RegisterUtils.registerBlock(BLOOD_CASING);
            }
        }
        if (ContentConfig.enableAdvancedScanner) {
            RegisterUtils.registerBlock(ADVANCED_SCANNER, ItemBlock.class);
            RegisterUtils.registerTile(TileAdvancedScanner.class);
        }
        if (ContentConfig.enableMatterGenerators) {
            RegisterUtils.registerBlocks(ItemBlock.class, ADVANCED_MATTER, NANO_MATTER, QUANTUM_MATTER);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.ic2.tile.matter", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableMatterUnifier) {
            RegisterUtils.registerBlock(MATTER_UNIFIER);
            RegisterUtils.registerTile(TileMatterUnifier.class);

            if (ContentConfig.enableMEEnergy) {
                RegisterUtils.registerBlock(QUANTUM_MATTER_UNIFIER);
                RegisterUtils.registerTile(TileQuantumMatterUnifier.class);
            }
        }
        if (ContentConfig.enableReplicators) {
            RegisterUtils.registerBlocks(ADVANCED_REPLICATOR, NANO_REPLICATOR, QUANTUM_REPLICATOR);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.ic2.tile.replicators", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableMEEnergy) {
            RegisterUtils.registerBlock(ENERGY_PROVIDER);
            RegisterUtils.registerTile(TileEUProvider.class);
        }
        if (ContentConfig.enableNewEnergyStorageUpgrades) {
            RegisterUtils.registerItem(ENERGY_STORAGE_UPGRADES);
        }
    }

    public void init(FMLInitializationEvent e) {
        if (isClient())
            clientInit();
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {
        if (ContentConfig.enableMEEnergy) {
            RegisterUtils.registerItemRenderer(Item.getItemFromBlock(ENERGY_PROVIDER), (IItemRenderer) new RenderItemEUProvider());
            RegisterUtils.registerTileRenderer(TileEUProvider.class, (TileEntitySpecialRenderer) new RenderBlockEUProvider());
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (ContentConfig.enableMEEnergy) {
            AEApi.instance().registries().gridCache().registerGridCache(IEUEnergyGrid.class, EUEnergyGrid.class);
        }
    }
}
