package foxiwhitee.hellmod.integration.avaritia;

import appeng.api.AEApi;
import appeng.api.config.SecurityPermissions;
import appeng.client.texture.CableBusTextures;
import appeng.core.sync.GuiBridge;
import appeng.core.sync.GuiHostType;
import appeng.items.misc.ItemEncodedPattern;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.avaritia.items.ItemSingularity;
import fox.spiteful.avaritia.items.LudicrousItems;
import foxiwhitee.hellmod.config.ContentConfig;
import foxiwhitee.hellmod.integration.avaritia.blocks.BlockNeutronUnifier;
import foxiwhitee.hellmod.integration.avaritia.container.ContainerPartBigPatternTerminal;
import foxiwhitee.hellmod.integration.avaritia.container.ContainerPartBigProcessingPatternTerminal;
import foxiwhitee.hellmod.integration.avaritia.container.ContainerPartCraftingTerminal9x9;
import foxiwhitee.hellmod.integration.avaritia.container.ContainerPartNeutronCompressorPatternTerminal;
import foxiwhitee.hellmod.integration.IIntegration;
import foxiwhitee.hellmod.integration.Integration;
import foxiwhitee.hellmod.integration.avaritia.blocks.BlockCustomNeutronCollector;
import foxiwhitee.hellmod.integration.avaritia.items.*;
import foxiwhitee.hellmod.integration.avaritia.parts.PartBigProcessingPatternTerminal;
import foxiwhitee.hellmod.integration.avaritia.tile.TileNeutronUnifier;
import foxiwhitee.hellmod.integration.avaritia.tile.collectors.*;
import foxiwhitee.hellmod.items.ModItemBlock;
import foxiwhitee.hellmod.integration.avaritia.parts.PartBigPatternTerminal;
import foxiwhitee.hellmod.integration.avaritia.parts.PartCraftingTerminal9x9;
import foxiwhitee.hellmod.integration.avaritia.parts.PartNeutronCompressorPatternTerminal;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;

@Integration(modid = "Avaritia")
public class AvaritiaIntegration implements IIntegration {
    public static ItemMultiAvaritiaParts ITEM_PARTS_TERMINALS = new ItemMultiAvaritiaParts(AEApi.instance().partHelper());
    public static final Item BIG_PATTERN = new ItemEncodedBigPattern("encodedBigPattern");
    public static final Item BIG_PROCESSING_PATTERN = new ItemEncodedBigProcessingPattern("encodedBigProcessingPattern");
    public static final Item NEUTRON_PATTERN = new ItemEncodedNeutronPattern("encodedNeutronPattern");

    public static final Block BASIC_NEUTRON_COLLECTOR = new BlockCustomNeutronCollector("basicNeutronCollector");
    public static final Block ADVANCEDN_NEUTRON_COLLECTOR = new BlockCustomNeutronCollector("advancedNeutronCollector");
    public static final Block HYBRID_NEUTRON_COLLECTOR = new BlockCustomNeutronCollector("hybridNeutronCollector");
    public static final Block ULTIMATE_NEUTRON_COLLECTOR = new BlockCustomNeutronCollector("ultimateNeutronCollector");
    public static final Block QUANTIUM_NEUTRON_COLLECTOR = new BlockCustomNeutronCollector("quantiumNeutronCollector");

    public static final Block NEUTRON_UNIFIER = new BlockNeutronUnifier("neutronUnifier");

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

    @Override
    public void preInit(FMLPreInitializationEvent paramFMLPreInitializationEvent) {
        RegisterUtils.registerItem(ITEM_PARTS_TERMINALS, "avaritiaPart");
        if (ContentConfig.enableCollectors) {
            RegisterUtils.registerBlocks(BASIC_NEUTRON_COLLECTOR, ADVANCEDN_NEUTRON_COLLECTOR, HYBRID_NEUTRON_COLLECTOR, ULTIMATE_NEUTRON_COLLECTOR, QUANTIUM_NEUTRON_COLLECTOR);
            RegisterUtils.findClasses("foxiwhitee.hellmod.integration.avaritia.tile.collectors", TileEntity.class).forEach(RegisterUtils::registerTile);
        }
        if (ContentConfig.enableNeutronUnifier) {
            RegisterUtils.registerBlock(NEUTRON_UNIFIER);
            RegisterUtils.registerTile(TileNeutronUnifier.class);
        }
        if (ContentConfig.enableBigPatternTerminal) {
            RegisterUtils.registerItem(BIG_PATTERN);
        }
        if (ContentConfig.enableBigProcessingPatternTerminal) {
            RegisterUtils.registerItem(BIG_PROCESSING_PATTERN);
        }
        if (ContentConfig.enableNeutronPatternTerminal) {
            RegisterUtils.registerItem(NEUTRON_PATTERN);
        }
    }

    @Override
    public void init(FMLInitializationEvent paramFMLInitializationEvent) {
        if (isClient())
            clientInit();
        OreDictionary.registerOre("catalyst", new ItemStack(LudicrousItems.resource, 64, 5));
        for (int i = 0; i < ItemSingularity.types.length; i++) {
            OreDictionary.registerOre("singularity", new ItemStack(LudicrousItems.singularity, 64, i));
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent paramFMLPostInitializationEvent) {
        if (ContentConfig.enableBigPatternTerminal) {
            guiBridges.put(0, EnumHelper.addEnum(GuiBridge.class, "PartPatternTerminal9x9", new Class[] { Class.class, Class.class, GuiHostType.class, SecurityPermissions.class }, new Object[] { ContainerPartBigPatternTerminal.class, PartBigPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT }));
        }
        if (ContentConfig.enableBigCraftingTerminal) {
            guiBridges.put(1, EnumHelper.addEnum(GuiBridge.class, "PartCraftingTerminal9x9", new Class[] { Class.class, Class.class, GuiHostType.class, SecurityPermissions.class }, new Object[] { ContainerPartCraftingTerminal9x9.class, PartCraftingTerminal9x9.class, GuiHostType.WORLD, SecurityPermissions.CRAFT }));
        }
        if (ContentConfig.enableNeutronPatternTerminal) {
            guiBridges.put(2, EnumHelper.addEnum(GuiBridge.class, "PartNeutronCompressorPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartNeutronCompressorPatternTerminal.class, PartNeutronCompressorPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));
        }
        if (ContentConfig.enableBigProcessingPatternTerminal) {
            guiBridges.put(3, EnumHelper.addEnum(GuiBridge.class, "PartPatternProcessingTerminal9x9", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartBigProcessingPatternTerminal.class, PartBigProcessingPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));
        }

        if (isClient()) {
            registerClientCableBusTextures();
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerClientCableBusTextures() {
        if (ContentConfig.enableBigPatternTerminal) {
            cableBusTexturesBright.put(0, EnumHelper.addEnum(CableBusTextures.class, "BigPatternTerminal", new Class[] { String.class }, new Object[] { "PartBigPatternTerm_Bright" }));
            cableBusTexturesDark.put(0, EnumHelper.addEnum(CableBusTextures.class, "BigPatternTerminal", new Class[] { String.class }, new Object[] { "PartBigPatternTerm_Dark" }));
            cableBusTexturesColored.put(0, EnumHelper.addEnum(CableBusTextures.class, "BigPatternTerminal", new Class[] { String.class }, new Object[] { "PartBigPatternTerm_Colored" }));
        }
        if (ContentConfig.enableBigCraftingTerminal) {
            cableBusTexturesBright.put(1, EnumHelper.addEnum(CableBusTextures.class, "CraftingTerminal9x9", new Class[] { String.class }, new Object[] { "PartCraftingTerminal9x9_Bright" }));
            cableBusTexturesDark.put(1, EnumHelper.addEnum(CableBusTextures.class, "CraftingTerminal9x9", new Class[] { String.class }, new Object[] { "PartCraftingTerminal9x9_Dark" }));
            cableBusTexturesColored.put(1, EnumHelper.addEnum(CableBusTextures.class, "CraftingTerminal9x9", new Class[] { String.class }, new Object[] { "PartCraftingTerminal9x9_Colored" }));
        }
        if (ContentConfig.enableNeutronPatternTerminal) {
            cableBusTexturesBright.put(2, EnumHelper.addEnum(CableBusTextures.class, "NeutronCompressorPatternTerminal", new Class[] { String.class }, new Object[] { "PartNeutronCompressorPatternTerminal_Bright" }));
            cableBusTexturesDark.put(2, EnumHelper.addEnum(CableBusTextures.class, "NeutronCompressorPatternTerminal", new Class[] { String.class }, new Object[] { "PartNeutronCompressorPatternTerminal_Dark" }));
            cableBusTexturesColored.put(2, EnumHelper.addEnum(CableBusTextures.class, "NeutronCompressorPatternTerminal", new Class[] { String.class }, new Object[] { "PartNeutronCompressorPatternTerminal_Colored" }));
        }
        if (ContentConfig.enableBigProcessingPatternTerminal) {
            cableBusTexturesBright.put(3, EnumHelper.addEnum(CableBusTextures.class, "BigPatternTerminalProcessing", new Class[] { String.class }, new Object[] { "PartBigPatternTermProcessing_Bright" }));
            cableBusTexturesDark.put(3, EnumHelper.addEnum(CableBusTextures.class, "BigPatternTerminalProcessing", new Class[] { String.class }, new Object[] { "PartBigPatternTermProcessing_Dark" }));
            cableBusTexturesColored.put(3, EnumHelper.addEnum(CableBusTextures.class, "BigPatternTerminalProcessing", new Class[] { String.class }, new Object[] { "PartBigPatternTermProcessing_Colored" }));
        }
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {}
}
