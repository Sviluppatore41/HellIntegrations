package foxiwhitee.HellIntegrations.integration.thaumcraft;

import appeng.api.AEApi;
import appeng.api.config.SecurityPermissions;
import appeng.client.texture.CableBusTextures;
import appeng.core.sync.GuiBridge;
import appeng.core.sync.GuiHostType;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.integration.thaumcraft.client.render.RenderBlockStabilizer;
import foxiwhitee.HellIntegrations.integration.thaumcraft.client.render.RenderItemStabilizer;
import foxiwhitee.HellIntegrations.integration.IIntegration;
import foxiwhitee.HellIntegrations.integration.Integration;
import foxiwhitee.HellIntegrations.integration.thaumcraft.blocks.BlockStabilizer;
import foxiwhitee.HellIntegrations.integration.thaumcraft.container.ContainerPartAlchemicalConstructionPatternTerminal;
import foxiwhitee.HellIntegrations.integration.thaumcraft.container.ContainerPartInfusionPatternTerminal;
import foxiwhitee.HellIntegrations.integration.thaumcraft.items.*;
import foxiwhitee.HellIntegrations.integration.thaumcraft.parts.PartAlchemicalConstructionPatternTerminal;
import foxiwhitee.HellIntegrations.integration.thaumcraft.parts.PartInfusionPatternTerminal;
import foxiwhitee.HellIntegrations.integration.thaumcraft.tile.TileStabilizer;
import foxiwhitee.HellIntegrations.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;

@Integration(modid = "Thaumcraft")
public class ThaumcraftIntegration implements IIntegration {
    public static final Item ITEM_PARTS_TERMINALS = new ItemMultiThaumcraftParts(AEApi.instance().partHelper());

    public static final Item RESEARCH_BOOK = new ThaumBooks("researchBook", "magic/researchBook", ThaumBooks.Type.RESEARCH);
    public static final Item DISTORTION_BOOK = new ThaumBooks("distortionBook", "magic/distortionBook", ThaumBooks.Type.DISTORTION);
    public static final Item ITEM_STABILIZATION_CHECKER = new ItemStabilizationChecker();

    public static final Item ALCHEMICAL_CONSTRUCTION_PATTERN = new ItemEncodedAlchemicalConstructionPattern("encodedAlchemicalConstructionPattern");
    public static final Item INFUSION_PATTERN = new ItemEncodedInfusionPattern("encodedInfusionPattern");

    public static final Block STABILIZER = new BlockStabilizer("stabilizer");

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


    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableBooks) {
            RegisterUtils.registerItems(RESEARCH_BOOK, DISTORTION_BOOK);
        }
        if (ContentConfig.enableStabilizationChecker) {
            RegisterUtils.registerItem(ITEM_STABILIZATION_CHECKER);
        }
        if (ContentConfig.enableStabilizer) {
            RegisterUtils.registerBlock(STABILIZER);
            RegisterUtils.registerTile(TileStabilizer.class);
        }
        if (ContentConfig.enableInfusionPatternTerminal) {
            RegisterUtils.registerItem(INFUSION_PATTERN);
        }
        if (ContentConfig.enableCruciblePatternTerminal) {
            RegisterUtils.registerItem(ALCHEMICAL_CONSTRUCTION_PATTERN);
        }
    }

    public void init(FMLInitializationEvent e) {
        if (isClient())
            clientInit();
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {
        if (ContentConfig.enableStabilizer) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileStabilizer.class, new RenderBlockStabilizer());
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(STABILIZER), new RenderItemStabilizer());
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (ContentConfig.enableInfusionPatternTerminal) {
            guiBridges.put(1, EnumHelper.addEnum(GuiBridge.class, "PartInfusionPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartInfusionPatternTerminal.class, PartInfusionPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));
        }
        if (ContentConfig.enableCruciblePatternTerminal) {
            guiBridges.put(0, EnumHelper.addEnum(GuiBridge.class, "PartAlchemicalConstructionPatternTerminal", new Class[]{Class.class, Class.class, GuiHostType.class, SecurityPermissions.class}, new Object[]{ContainerPartAlchemicalConstructionPatternTerminal.class, PartAlchemicalConstructionPatternTerminal.class, GuiHostType.WORLD, SecurityPermissions.CRAFT}));
        }

        if (isClient()) {
            registerClientCableBusTextures();
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerClientCableBusTextures() {
        if (ContentConfig.enableInfusionPatternTerminal) {
            cableBusTexturesBright.put(1, EnumHelper.addEnum(CableBusTextures.class, "InfusionPatternTerminal", new Class[]{String.class}, new Object[]{"PartInfusionPatternTerminal_Bright"}));
            cableBusTexturesDark.put(1, EnumHelper.addEnum(CableBusTextures.class, "InfusionPatternTerminal", new Class[]{String.class}, new Object[]{"PartInfusionPatternTerminal_Dark"}));
            cableBusTexturesColored.put(1, EnumHelper.addEnum(CableBusTextures.class, "InfusionPatternTerminal", new Class[]{String.class}, new Object[]{"PartInfusionPatternTerminal_Colored"}));
        }
        if (ContentConfig.enableCruciblePatternTerminal) {
            cableBusTexturesBright.put(0, EnumHelper.addEnum(CableBusTextures.class, "AlchemicalConstructionPatternTerminal", new Class[]{String.class}, new Object[]{"PartAlchemicalConstructionPatternTerm_Bright"}));
            cableBusTexturesDark.put(0, EnumHelper.addEnum(CableBusTextures.class, "AlchemicalConstructionPatternTerminal", new Class[]{String.class}, new Object[]{"PartAlchemicalConstructionPatternTerm_Dark"}));
            cableBusTexturesColored.put(0, EnumHelper.addEnum(CableBusTextures.class, "AlchemicalConstructionPatternTerminal", new Class[]{String.class}, new Object[]{"PartAlchemicalConstructionPatternTerm_Colored"}));
        }
    }
}
