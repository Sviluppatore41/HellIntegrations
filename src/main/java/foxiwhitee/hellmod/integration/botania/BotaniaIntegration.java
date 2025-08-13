package foxiwhitee.hellmod.integration.botania;

import appeng.api.AEApi;
import appeng.api.config.SecurityPermissions;
import appeng.client.texture.CableBusTextures;
import appeng.core.sync.GuiBridge;
import appeng.core.sync.GuiHostType;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.hellmod.blocks.BlockAENetwork;
import foxiwhitee.hellmod.config.ContentConfig;
import foxiwhitee.hellmod.config.HellConfig;
import foxiwhitee.hellmod.HellCore;
import foxiwhitee.hellmod.integration.IIntegration;
import foxiwhitee.hellmod.integration.Integration;
import foxiwhitee.hellmod.integration.botania.blocks.*;
import foxiwhitee.hellmod.integration.botania.client.ManaPoolInfoRenderer;
import foxiwhitee.hellmod.integration.botania.client.render.pools.RenderCustomManaPool;
import foxiwhitee.hellmod.integration.botania.client.render.spreaders.RenderCustomSpreader;
import foxiwhitee.hellmod.integration.botania.client.render.RenderItemManaCharger;
import foxiwhitee.hellmod.integration.botania.client.render.RenderManaCharger;
import foxiwhitee.hellmod.integration.botania.client.render.entity.RenderCustomSpark;
import foxiwhitee.hellmod.integration.botania.container.*;
import foxiwhitee.hellmod.integration.botania.entity.AsgardSpark;
import foxiwhitee.hellmod.integration.botania.entity.HelhelmSpark;
import foxiwhitee.hellmod.integration.botania.entity.MidgardSpark;
import foxiwhitee.hellmod.integration.botania.entity.ValhallaSpark;
import foxiwhitee.hellmod.integration.botania.event.ServerEventHandler;
import foxiwhitee.hellmod.integration.botania.helpers.IManaStorageGrid;
import foxiwhitee.hellmod.integration.botania.items.*;
import foxiwhitee.hellmod.integration.botania.items.ae.*;
import foxiwhitee.hellmod.integration.botania.items.generating.*;
import foxiwhitee.hellmod.integration.botania.items.patterns.*;
import foxiwhitee.hellmod.integration.botania.me.CreativeManaCellHandler;
import foxiwhitee.hellmod.integration.botania.me.ManaCellHandler;
import foxiwhitee.hellmod.integration.botania.me.ManaStorageGrid;
import foxiwhitee.hellmod.integration.botania.parts.*;
import foxiwhitee.hellmod.integration.botania.tile.*;
import foxiwhitee.hellmod.integration.botania.tile.ae.*;
import foxiwhitee.hellmod.integration.botania.tile.ae.ports.TileManaReceiver;
import foxiwhitee.hellmod.integration.botania.tile.ae.ports.TileManaReceiverDwimerite;
import foxiwhitee.hellmod.integration.botania.tile.ae.ports.TileManaSupplier;
import foxiwhitee.hellmod.integration.botania.tile.ae.ports.TileManaSupplierDwimerite;
import foxiwhitee.hellmod.integration.botania.tile.pools.*;
import foxiwhitee.hellmod.integration.botania.tile.spreaders.*;
import foxiwhitee.hellmod.items.DefaultItem;
import foxiwhitee.hellmod.items.ModItemBlock;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;

@Integration(modid = "Botania")
public class BotaniaIntegration implements IIntegration {
    public static final Item ITEM_PARTS_TERMINALS = new ItemMultiBotaniaParts(AEApi.instance().partHelper());

    public static final Item ELVEN_TRADE_PATTERN = new ItemEncodedElvenTradePattern("encodedElvenTradePattern");
    public static final Item MANA_POOL_PATTERN = new ItemEncodedManaPoolPattern("encodedManaPoolPattern");
    public static final Item PETALS_PATTERN = new ItemEncodedPetalsPattern("encodedPetalsPattern");
    public static final Item PURE_DAISY_PATTERN = new ItemEncodedPureDaisyPattern("encodedPureDaisyPattern");
    public static final Item RUNE_ALTAR_PATTERN = new ItemEncodedRuneAltarPattern("encodedRuneAltarPattern");

    public static final Item ASGARD_SPARK = (Item)new ItemCustomSpark("asgardSpark", HellConfig.manaPerSecSparkAsgard);
    public static final Item HELHELM_SPARK = (Item)new ItemCustomSpark("helhelmSpark", HellConfig.manaPerSecSparkHelhelm);
    public static final Item VALHALLA_SPARK = (Item)new ItemCustomSpark("valhallaSpark", HellConfig.manaPerSecSparkValhalla);
    public static final Item MIDGARD_SPARK = (Item)new ItemCustomSpark("midgardSpark", HellConfig.manaPerSecSparkMidgard);

    public static final Item MANA_STORAGE_COMPONENT = new ItemManaStorageComponent("manaStorageComponent");
    public static final Item EMPTY_MANA_STORAGE_CELL = new ItemEmptyManaCell("emptyManaStorageCell");
    public static final Item CREATIVE_MANA_STORAGE_CELL = new ItemManaCreativeCell("creativeManaStorageCell");
    public static final Item MANA_STORAGE_CELL = new ItemManaCell("manaStorageCell");

    public static final Item MANA_DROP = new ItemManaDrop("manaDrop");

    public static final Item GENERATION_FLOWER_CORES = new ItemGenerationFlowerCores("generationFlowerCores");
    public static final Item FUNCTIONAL_FLOWER_CORES = new ItemFunctionalFlowerCores("functionalFlowerCores");
    public static final Item ESSENCE_OF_LIFE = new ItemEssenceOfLife("essenceOfLife");
    public static final Item CORE_OF_LIFE = new ItemCoreOfLife("coreOfLife");

    public static final Item UPGRADE_GENERATOR_MULTIPLY = new ItemUpgradeGeneratorMultiply("upgradeGeneratorMultiply");
    public static final Item UPGRADE_GENERATOR_SPEED = new ItemUpgradeGeneratorSpeed("upgradeGeneratorSpeed");

    public static final Item UPGRADE_FLOWER_SYNTHESIZER = new ItemUpgradeFlowerSynthesizer("upgradeFlowerSynthesizer");

    public static final Item ASGARDIAN_INGOT = new DefaultItem("asgardianIngot", "magic/asgardianIngot");
    public static final Item HELHEIM_INGOT = new DefaultItem("helheimIngot", "magic/helheimIngot");
    public static final Item VALHALLA_INGOT = new DefaultItem("valhallaIngot", "magic/valhallaIngot");
    public static final Item MIDGARD_INGOT = new DefaultItem("midgardIngot", "magic/midgardIngot");

    public static final Block MANA_CHARGER = new BlockManaCharger("manaCharger");
    public static final Block ASGARD_POOL = new BlockCustomManaPool("asgardPool", HellConfig.manaAsgardPool);
    public static final Block HELHELM_POOL = new BlockCustomManaPool("helhelmPool", HellConfig.manaHelhelmPool);
    public static final Block VALHALLA_POOL = new BlockCustomManaPool("valhallaPool", HellConfig.manaValhallaPool);
    public static final Block MIDGARD_POOL = new BlockCustomManaPool("midgardPool", HellConfig.manaMidgardPool);
    public static final Block ASGARD_SPREADER = new BlockCustomSpreader("asgardSpreader");
    public static final Block HELHELM_SPREADER = new BlockCustomSpreader("helhelmSpreader");
    public static final Block VALHALLA_SPREADER = new BlockCustomSpreader("valhallaSpreader");
    public static final Block MIDGARD_SPREADER = new BlockCustomSpreader("midgardSpreader");

    public static final Block MANA_SUPPLIER = new BlockAENetwork("manaSupplier", TileManaSupplier.class);
    public static final Block MANA_SUPPLIER_DWIMERITE = new BlockAENetwork("manaSupplierDwimerite", TileManaSupplierDwimerite.class);
    public static final Block MANA_RECEIVER = new BlockAENetwork("manaReceiver", TileManaReceiver.class);
    public static final Block MANA_RECEIVER_DWIMERITE = new BlockAENetwork("manaReceiverDwimerite", TileManaReceiverDwimerite.class);

    public static final Block MANA_GENERATOR_TIER1 = new BlockManaGenerator("manaGeneratorTier1", 1);
    public static final Block MANA_GENERATOR_TIER2 = new BlockManaGenerator("manaGeneratorTier2", 2);
    public static final Block MANA_GENERATOR_TIER3 = new BlockManaGenerator("manaGeneratorTier3", 3);
    public static final Block MANA_GENERATOR_TIER4 = new BlockManaGenerator("manaGeneratorTier4", 4);
    public static final Block MANA_GENERATOR_TIER5 = new BlockManaGenerator("manaGeneratorTier5", 5);

    public static final Block FLOWER_SYNTHESIZER = new BlockFlowerSynthesizer("flowerSynthesizer");

    private static HashMap<Integer, GuiBridge> guiBridges = new HashMap<>();

    private static HashMap<Integer, CableBusTextures> cableBusTexturesBright = new HashMap<>();
    private static HashMap<Integer, CableBusTextures> cableBusTexturesDark = new HashMap<>();
    private static HashMap<Integer, CableBusTextures> cableBusTexturesColored = new HashMap<>();

    public static GuiBridge getGuiBridge(int id) {
        return guiBridges.get(id);
    }

    public static CableBusTextures getBusTextureBright(int id) {
        return cableBusTexturesBright.get(id);
    }

    public static CableBusTextures getBusTextureDark(int id) {
        return cableBusTexturesDark.get(id);
    }

    public static CableBusTextures getBusTextureColored(int id) {
        return cableBusTexturesColored.get(id);
    }

    public static int sparkColorAsgard;
    public static int sparkColorHelhelm;
    public static int sparkColorValhalla;
    public static int sparkColorMidgard;

    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableCharger) {
            RegisterUtils.registerBlock(MANA_CHARGER);
            RegisterUtils.registerTile(TileManaCharger.class);
        }
        if (ContentConfig.enableSparks) {
            EntityRegistry.registerModEntity(AsgardSpark.class, "AsgardSpark", 1, HellCore.instance, 64, 10, false);
            EntityRegistry.registerModEntity(HelhelmSpark.class, "HelhelmSpark", 2, HellCore.instance, 64, 10, false);
            EntityRegistry.registerModEntity(ValhallaSpark.class, "ValhallaSpark", 3, HellCore.instance, 64, 10, false);
            EntityRegistry.registerModEntity(MidgardSpark.class, "MidgardSpark", 4, HellCore.instance, 64, 10, false);
            RegisterUtils.registerItems(ASGARD_SPARK, HELHELM_SPARK, VALHALLA_SPARK, MIDGARD_SPARK);
        }
        if (ContentConfig.enablePools) {
            RegisterUtils.registerBlocks(ASGARD_POOL, HELHELM_POOL, VALHALLA_POOL, MIDGARD_POOL);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.botania.tile.pools", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableSpreaders) {
            RegisterUtils.registerBlocks(ASGARD_SPREADER, HELHELM_SPREADER, VALHALLA_SPREADER, MIDGARD_SPREADER);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.botania.tile.spreaders", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableIngots) {
            RegisterUtils.registerItems(ASGARDIAN_INGOT, HELHEIM_INGOT, VALHALLA_INGOT, MIDGARD_INGOT);
        }
        if (ContentConfig.enableMEMana) {
            RegisterUtils.registerItems(MANA_DROP, MANA_STORAGE_CELL, MANA_STORAGE_COMPONENT, CREATIVE_MANA_STORAGE_CELL, EMPTY_MANA_STORAGE_CELL);
            RegisterUtils.registerBlocks(MANA_SUPPLIER, MANA_SUPPLIER_DWIMERITE, MANA_RECEIVER, MANA_RECEIVER_DWIMERITE);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.botania.tile.ae.ports", TileEntity.class).forEach(RegisterUtils::registerTile);

            if (ContentConfig.enableFLowerSynthesizer) {
                RegisterUtils.registerBlock(FLOWER_SYNTHESIZER);
                RegisterUtils.registerTile(TileFlowerSynthesizer.class);
                RegisterUtils.registerItems(FUNCTIONAL_FLOWER_CORES, UPGRADE_FLOWER_SYNTHESIZER);
            }
            if (ContentConfig.enableManaGenerator) {
                RegisterUtils.registerBlocks(MANA_GENERATOR_TIER1, MANA_GENERATOR_TIER2, MANA_GENERATOR_TIER3, MANA_GENERATOR_TIER4, MANA_GENERATOR_TIER5);
                RegisterUtils.findClasses("foxiwhitee.hellmod.integration.botania.tile.ae", TileManaGenerator.class).forEach(RegisterUtils::registerTile);
                RegisterUtils.registerItems(GENERATION_FLOWER_CORES, CORE_OF_LIFE, ESSENCE_OF_LIFE, UPGRADE_GENERATOR_MULTIPLY, UPGRADE_GENERATOR_SPEED);
            }
        }
        if (ContentConfig.enableElvenTradePatternTerminal) {
            RegisterUtils.registerItem(ELVEN_TRADE_PATTERN);
        }
        if (ContentConfig.enableManaPoolPatternTerminal) {
            RegisterUtils.registerItem(MANA_POOL_PATTERN);
        }
        if (ContentConfig.enablePureDaisyPatternTerminal) {
            RegisterUtils.registerItem(PURE_DAISY_PATTERN);
        }
        if (ContentConfig.enablePetalsPatternTerminal) {
            RegisterUtils.registerItem(PETALS_PATTERN);
        }
        if (ContentConfig.enableRuneAltarPatternTerminal) {
            RegisterUtils.registerItem(RUNE_ALTAR_PATTERN);
        }
    }

    public void init(FMLInitializationEvent e) {
        if (isClient())
            clientInit();
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ManaPoolInfoRenderer());
        if (ContentConfig.enableSparks) {
            initHex();
            RenderingRegistry.registerEntityRenderingHandler(AsgardSpark.class, (Render)new RenderCustomSpark());
            RenderingRegistry.registerEntityRenderingHandler(HelhelmSpark.class, (Render)new RenderCustomSpark());
            RenderingRegistry.registerEntityRenderingHandler(ValhallaSpark.class, (Render)new RenderCustomSpark());
            RenderingRegistry.registerEntityRenderingHandler(MidgardSpark.class, (Render)new RenderCustomSpark());
        }
        if (ContentConfig.enableCharger) {
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock((Block) MANA_CHARGER), (IItemRenderer)new RenderItemManaCharger());
            ClientRegistry.bindTileEntitySpecialRenderer(TileManaCharger.class, (TileEntitySpecialRenderer)new RenderManaCharger());
        }
        if (ContentConfig.enablePools) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileAsgardManaPool.class, (TileEntitySpecialRenderer)new RenderCustomManaPool());
            ClientRegistry.bindTileEntitySpecialRenderer(TileHelHelmManaPool.class, (TileEntitySpecialRenderer)new RenderCustomManaPool());
            ClientRegistry.bindTileEntitySpecialRenderer(TileValhallaManaPool.class, (TileEntitySpecialRenderer)new RenderCustomManaPool());
            ClientRegistry.bindTileEntitySpecialRenderer(TileMidgardManaPool.class, (TileEntitySpecialRenderer)new RenderCustomManaPool());
        }
        if (ContentConfig.enableSpreaders) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileAsgardSpreader.class, (TileEntitySpecialRenderer)new RenderCustomSpreader());
            ClientRegistry.bindTileEntitySpecialRenderer(TileHelhelmSpreader.class, (TileEntitySpecialRenderer)new RenderCustomSpreader());
            ClientRegistry.bindTileEntitySpecialRenderer(TileValhallaSpreader.class, (TileEntitySpecialRenderer)new RenderCustomSpreader());
            ClientRegistry.bindTileEntitySpecialRenderer(TileMidgardSpreader.class, (TileEntitySpecialRenderer)new RenderCustomSpreader());
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (ContentConfig.enableMEMana) {
            MinecraftForge.EVENT_BUS.register(ServerEventHandler.INSTANCE);
            AEApi.instance().registries().cell().addCellHandler(new ManaCellHandler());
            AEApi.instance().registries().cell().addCellHandler(new CreativeManaCellHandler());
            AEApi.instance().registries().gridCache().registerGridCache(IManaStorageGrid.class, ManaStorageGrid.class);
        }

        if (isClient()) {
            postInitClient();
        }
    }

    @SideOnly(Side.CLIENT)
    private void postInitClient() {
        if (ContentConfig.enableElvenTradePatternTerminal) {
            guiBridges.put(2, EnumHelper.addEnum(GuiBridge.class, "PartElvenTradePatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartElvenTradePatternTerminal.class, PartElvenTradePatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));

            cableBusTexturesBright.put(2, EnumHelper.addEnum(CableBusTextures.class, "ElvenTradePatternTerminal", new Class[] { String.class }, new Object[] { "PartElvenTradePatternTerminal_Bright" }));
            cableBusTexturesDark.put(2, EnumHelper.addEnum(CableBusTextures.class, "ElvenTradePatternTerminal", new Class[] { String.class }, new Object[] { "PartElvenTradePatternTerminal_Dark" }));
            cableBusTexturesColored.put(2, EnumHelper.addEnum(CableBusTextures.class, "ElvenTradePatternTerminal", new Class[] { String.class }, new Object[] { "PartElvenTradePatternTerminal_Colored" }));
        }
        if (ContentConfig.enableManaPoolPatternTerminal) {
            guiBridges.put(0, EnumHelper.addEnum(GuiBridge.class, "PartManaPoolPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartManaPoolPatternTerminal.class, PartManaPoolPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));

            cableBusTexturesBright.put(0, EnumHelper.addEnum(CableBusTextures.class, "ManaPoolPatternTerminal", new Class[]{String.class}, new Object[]{"PartManaPoolPatternTerm_Bright"}));
            cableBusTexturesDark.put(0, EnumHelper.addEnum(CableBusTextures.class, "ManaPoolPatternTerminal", new Class[]{String.class}, new Object[]{"PartManaPoolPatternTerm_Dark"}));
            cableBusTexturesColored.put(0, EnumHelper.addEnum(CableBusTextures.class, "ManaPoolPatternTerminal", new Class[]{String.class}, new Object[]{"PartManaPoolPatternTerm_Colored"}));
        }
        if (ContentConfig.enablePureDaisyPatternTerminal) {
            guiBridges.put(4, EnumHelper.addEnum(GuiBridge.class, "PartPureDaisyPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartPureDaisyPatternTerminal.class, PartPureDaisyPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));

            cableBusTexturesBright.put(4, EnumHelper.addEnum(CableBusTextures.class, "PureDaisyPatternTerminal", new Class[] { String.class }, new Object[] { "PartPureDaisyPatternTerminal_Bright" }));
            cableBusTexturesDark.put(4, EnumHelper.addEnum(CableBusTextures.class, "PureDaisyPatternTerminal", new Class[] { String.class }, new Object[] { "PartPureDaisyPatternTerminal_Dark" }));
            cableBusTexturesColored.put(4, EnumHelper.addEnum(CableBusTextures.class, "PartPureDaisyPatternTerminal", new Class[] { String.class }, new Object[] { "PartPureDaisyPatternTerminal_Colored" }));
        }
        if (ContentConfig.enablePetalsPatternTerminal) {
            guiBridges.put(3, EnumHelper.addEnum(GuiBridge.class, "PartPetalsPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartPetalsPatternTerminal.class, PartPetalsPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));

            cableBusTexturesBright.put(3, EnumHelper.addEnum(CableBusTextures.class, "PetalsPatternTerminal", new Class[] { String.class }, new Object[] { "PartPetalsPatternTerminal_Bright" }));
            cableBusTexturesDark.put(3, EnumHelper.addEnum(CableBusTextures.class, "PetalsPatternTerminal", new Class[] { String.class }, new Object[] { "PartPetalsPatternTerminal_Dark" }));
            cableBusTexturesColored.put(3, EnumHelper.addEnum(CableBusTextures.class, "PetalsPatternTerminal", new Class[] { String.class }, new Object[] { "PartPetalsPatternTerminal_Colored" }));
        }
        if (ContentConfig.enableRuneAltarPatternTerminal) {
            guiBridges.put(5, EnumHelper.addEnum(GuiBridge.class, "PartRuneAltarPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartRuneAltarPatternTerminal.class, PartRuneAltarPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));

            cableBusTexturesBright.put(5, EnumHelper.addEnum(CableBusTextures.class, "RuneAltarPatternTerminal", new Class[] { String.class }, new Object[] { "PartRuneAltarPatternTerminal_Bright" }));
            cableBusTexturesDark.put(5, EnumHelper.addEnum(CableBusTextures.class, "RuneAltarPatternTerminal", new Class[] { String.class }, new Object[] { "PartRuneAltarPatternTerminal_Dark" }));
            cableBusTexturesColored.put(5, EnumHelper.addEnum(CableBusTextures.class, "RuneAltarPatternTerminal", new Class[] { String.class }, new Object[] { "PartRuneAltarPatternTerminal_Colored" }));
        }
    }


    public static void initHex() {
        sparkColorAsgard = parseColor("#D66122", 16711680);
        sparkColorHelhelm = parseColor("#6f00cc", 16711680);
        sparkColorValhalla = parseColor("#d9c836", 16711680);
        sparkColorMidgard = parseColor("#1D4DE1", 16711680);
    }

    public static int parseColor(String hex, int defaultValue) {
        if (hex.startsWith("#"))
            hex = hex.substring(1);
        try {
            return Integer.parseInt(hex, 16);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}
