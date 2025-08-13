package foxiwhitee.hellmod.integration.draconic;

import appeng.api.AEApi;
import appeng.block.AEBaseItemBlock;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.common.ModBlocks;
import com.brandon3055.draconicevolution.common.ModItems;
import com.brandon3055.draconicevolution.common.blocks.BlockDE;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.hellmod.HellCore;
import foxiwhitee.hellmod.ModRecipes;
import foxiwhitee.hellmod.blocks.BaseBlock;
import foxiwhitee.hellmod.config.ContentConfig;
import foxiwhitee.hellmod.config.HellConfig;
import foxiwhitee.hellmod.integration.IIntegration;
import foxiwhitee.hellmod.integration.Integration;
import foxiwhitee.hellmod.integration.draconic.blocks.*;
import foxiwhitee.hellmod.integration.draconic.client.render.*;
import foxiwhitee.hellmod.integration.draconic.client.render.armor.RenderArialArmor;
import foxiwhitee.hellmod.integration.draconic.client.render.armor.RenderChaoticArmor;
import foxiwhitee.hellmod.integration.draconic.client.render.effect.SEffectHandler;
import foxiwhitee.hellmod.integration.draconic.client.render.items.*;
import foxiwhitee.hellmod.integration.draconic.entity.EntityArialHeart;
import foxiwhitee.hellmod.integration.draconic.entity.EntityChaoticHeart;
import foxiwhitee.hellmod.integration.draconic.entity.EntityHeart;
import foxiwhitee.hellmod.integration.draconic.event.OnDropEvent;
import foxiwhitee.hellmod.integration.draconic.helpers.IFusionCraftingInventory;
import foxiwhitee.hellmod.integration.draconic.itemBlock.BlockAwakenedDraconiumItemBlock;
import foxiwhitee.hellmod.integration.draconic.itemBlock.BlockChaosItemBlock;
import foxiwhitee.hellmod.integration.draconic.items.*;
import foxiwhitee.hellmod.integration.draconic.items.armors.ArialArmor;
import foxiwhitee.hellmod.integration.draconic.items.armors.ChaoticArmor;
import foxiwhitee.hellmod.integration.draconic.items.capicators.ArialFluxCapicator;
import foxiwhitee.hellmod.integration.draconic.items.capicators.ChaoticFluxCapicator;
import foxiwhitee.hellmod.integration.draconic.items.tools.*;
import foxiwhitee.hellmod.integration.draconic.items.weapons.ArialBow;
import foxiwhitee.hellmod.integration.draconic.items.weapons.ArialSword;
import foxiwhitee.hellmod.integration.draconic.items.weapons.ChaoticBow;
import foxiwhitee.hellmod.integration.draconic.items.weapons.ChaoticSword;
import foxiwhitee.hellmod.integration.draconic.recipes.FusionRecipe;
import foxiwhitee.hellmod.integration.draconic.tile.*;
import foxiwhitee.hellmod.items.DefaultItem;
import foxiwhitee.hellmod.integration.draconic.event.AEventHandler;
import foxiwhitee.hellmod.integration.draconic.event.CommonEventHandler;
import foxiwhitee.hellmod.integration.draconic.event.CEventHandler;
import foxiwhitee.hellmod.utils.helpers.OreDictUtil;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;
import java.util.Map;

import static foxiwhitee.hellmod.ModRecipes.*;
import static foxiwhitee.hellmod.ModRecipes.registerDraconicAssemblerRecipe;

@Integration(modid = "DraconicEvolution")
public class DraconicEvolutionIntegration implements IIntegration {
    private static Map<String, ResourceLocation> cachedResources = new HashMap<>();

    public static final ItemArmor.ArmorMaterial CHAOTIC_ARMOR = EnumHelper.addArmorMaterial("CHAOTIC_ARMOR", -1, new int[] { 4, 9, 7, 4 }, 40);
    public static final Item.ToolMaterial CHAOTIC = EnumHelper.addToolMaterial("CHAOTIC", 10, -1, 400.0F, 60.0F, 45);

    public static final ItemArmor.ArmorMaterial ARIAL_ARMOR = EnumHelper.addArmorMaterial("ARIAL_ARMOR", -1, new int[] { 4, 9, 7, 4 }, 80);
    public static final Item.ToolMaterial ARIAL = EnumHelper.addToolMaterial("ARIAL", 11, -1, 500.0F, 90.0F, 85);

    public static final Item CHAOTIC_HELM = (ItemArmor)new ChaoticArmor(CHAOTIC_ARMOR, 0, "chaoticHelm");
    public static final Item CHAOTIC_CHEST = (ItemArmor)new ChaoticArmor(CHAOTIC_ARMOR, 1, "chaoticChest");
    public static final Item CHAOTIC_LEGS = (ItemArmor)new ChaoticArmor(CHAOTIC_ARMOR, 2, "chaoticLegs");
    public static final Item CHAOTIC_BOOTS = (ItemArmor)new ChaoticArmor(CHAOTIC_ARMOR, 3, "chaoticBoots");

    public static final Item ARIAL_HELM = (ItemArmor)new ArialArmor(ARIAL_ARMOR, 0, "arialHelm");
    public static final Item ARIAL_CHEST = (ItemArmor)new ArialArmor(ARIAL_ARMOR, 1, "arialChest");
    public static final Item ARIAL_LEGS = (ItemArmor)new ArialArmor(ARIAL_ARMOR, 2, "arialLegs");
    public static final Item ARIAL_BOOTS = (ItemArmor)new ArialArmor(ARIAL_ARMOR, 3, "arialBoots");

    public static final Item CHAOTIC_CAPICATOR = (Item)new ChaoticFluxCapicator("chaoticCapacitor");
    public static final Item ARIAL_CAPICATOR = (Item)new ArialFluxCapicator("arialCapacitor");

    public static final Item CHAOTIC_SWORD = (ItemSword)new ChaoticSword(CHAOTIC, "chaoticSword");
    public static final Item CHAOTIC_BOW = (ItemBow)new ChaoticBow( "chaoticBow");
    public static final Item ARIAL_SWORD = (ItemSword)new ArialSword(ARIAL, "arialSword");
    public static final Item ARIAL_BOW = (ItemBow)new ArialBow( "arialBow");

    public static final Item CHAOTIC_PICKAXE = (Item)new ChaoticPickaxe(CHAOTIC, "chaoticPickaxe");
    public static final Item CHAOTIC_SHOVEL = (Item)new ChaoticShovel(CHAOTIC, "chaoticShovel");
    public static final Item CHAOTIC_AXE = (Item)new ChaoticAxe(CHAOTIC, "chaoticAxe");
    public static final Item CHAOTIC_DISTRUCTION_STAFF = (Item)new ChaoticDistructionStaff(CHAOTIC, "chaoticDistructionStaff");

    public static final Item ARIAL_PICKAXE = (Item)new ArialPickaxe(ARIAL, "arialPickaxe");
    public static final Item ARIAL_SHOVEL = (Item)new ArialShovel(ARIAL, "arialShovel");
    public static final Item ARIAL_AXE = (Item)new ArialAxe(ARIAL, "arialAxe");
    public static final Item ARIAL_DISTRUCTION_STAFF = (Item)new ArialDistructionStaff(ARIAL, "arialDistructionStaff");

    public static final Item UPGRADE_DRAGON_AUTO_AWAKENED_BLOCKS = new ItemDragonUpgrade("upgradeDragonAutoAwakenedBlocks", "draconic/upgradeDragonAutoAwakenedBlocks");
    public static final Item UPGRADE_CHAOS_AUTO_AWAKENED_BLOCKS = new ItemChaosUpgrade("upgradeChaosAutoAwakenedBlocks", "draconic/upgradeChaosAutoAwakenedBlocks");
    public static final Item UPGRADE_ARIAL_AUTO_AWAKENED_BLOCKS = new ItemArialUpgrade("upgradeArialAutoAwakenedBlocks", "draconic/upgradeArialAutoAwakenedBlocks");

    public static final Item ARIAL_CORE = new DefaultItem("arialCore", "draconic/arialCore") {
        @Override
        public boolean hasEffect(ItemStack par1ItemStack, int pass) {
            return true;
        }
    };
    public static final Item ARIAL_ENERGY_CORE = new DefaultItem("arialEnergyCore", "draconic/arialEnergyCore") {
        @Override
        public boolean hasEffect(ItemStack par1ItemStack, int pass) {
            return true;
        }
    };
    public static final Item ARIAL_HEART = new ItemArialHeart("arialHeart");
    public static final Item CHAOTIC_HEART = new ItemChaoticHeart("chaoticHeart");

    public static final Item DRACONIC_ASSEMBLER_UPGRADES = new ItemDraconicAssemblerUpgrades("draconicAssemblerUpgrades");
    public static final Item DRACONIC_ENERGY_UPGRADES = new ItemDraconicEnergyUpgrades("draconicEnergyUpgrades");

    public static final Item CHAOTIC_INGOT = new DefaultItem("ingot_chaotic", "draconic/ingot_chaotic");
    public static final Item CHAOS_FRAGMENT = new DefaultItem("fragment_chaos", "draconic/fragment_chaos");
    public static final Item CHAOTIC_ENERGY_CORE = new DefaultItem("chaoticEnergyCore", "draconic/chaoticEnergyCore");

    public static final Block AUTO_AWAKENER_BLOCKS = new BlockAutoAwakenedBlocks("autoAwakener");

    public static final Block CHAOTIC_BLOCK = new BlockChaos();
    public static final Block ARIAL_BLOCK = new BaseBlock("arialBlock").setBlockTextureName(HellCore.MODID + ":draconic/arialBlock");

    public static final Block DRACONIC_ASSEMBLER = new BlockDraconicAssembler("dragonAssembler");

    public static final Block FUSION_CORE = new BlockFusionCraftingCore("fusionCore");
    public static final Block FUSION_INJECTOR = new BlockFusionInjector("fusionInjector");

    public static final Block CUSTOM_UPGRADE_MODIFIER = new BlockCustomUpgradeModifier("customUpgradeModifier");

    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableAutoAwakener) {
            RegisterUtils.registerBlock(AUTO_AWAKENER_BLOCKS);
            RegisterUtils.registerTile(TileAutoAwakenedBlocks.class);
            RegisterUtils.registerItems(UPGRADE_DRAGON_AUTO_AWAKENED_BLOCKS, UPGRADE_CHAOS_AUTO_AWAKENED_BLOCKS, UPGRADE_ARIAL_AUTO_AWAKENED_BLOCKS);
        }
        if ((ContentConfig.enableChaosBlocks && ContentConfig.enableChaosHeart) || (ContentConfig.enableArialBlock && ContentConfig.enableArialHeart)) {
            EntityRegistry.registerModEntity(EntityHeart.class, "Persistent Heart", 12, HellCore.instance, 32, 5, true);
        }
        if (ContentConfig.enableChaosBlocks) {
            RegisterUtils.registerBlock(CHAOTIC_BLOCK, BlockChaosItemBlock.class);

            if (ContentConfig.enableChaosHeart) {
                EntityRegistry.registerModEntity(EntityChaoticHeart.class, "Chaotic Heart", 13, HellCore.instance, 32, 5, true);
                RegisterUtils.registerItem(CHAOTIC_HEART);
            }
        }
        if (ContentConfig.enableArialBlock) {
            RegisterUtils.registerBlock(ARIAL_BLOCK, ItemBlock.class);

            if (ContentConfig.enableArialHeart) {
                EntityRegistry.registerModEntity(EntityArialHeart.class, "Arial Heart", 14, HellCore.instance, 32, 5, true);
                RegisterUtils.registerItem(ARIAL_HEART);
            }
        }
        if (ContentConfig.enableAssembler) {
            RegisterUtils.registerBlock(DRACONIC_ASSEMBLER);
            RegisterUtils.registerTile(TileDraconicAssembler.class);
            RegisterUtils.registerItem(DRACONIC_ASSEMBLER_UPGRADES);
        }
        if (ContentConfig.enableFusion) {
            RegisterUtils.registerBlocks(FUSION_CORE, FUSION_INJECTOR);
            RegisterUtils.registerTiles(TileFusionCraftingCore.class, TileFusionInjector.class);
        }
        if (ContentConfig.enableEnergyUpgrades) {
            RegisterUtils.registerItem(DRACONIC_ENERGY_UPGRADES);
        }
        if (ContentConfig.enableArialCores) {
            RegisterUtils.registerItems(ARIAL_ENERGY_CORE, ARIAL_CORE);
        }
        if (ContentConfig.enableChaoticEnergyCore) {
            RegisterUtils.registerItem(CHAOTIC_ENERGY_CORE);
        }
        if (ContentConfig.enableChaosIngot) {
            RegisterUtils.registerItem(CHAOTIC_INGOT);
        }
        if (ContentConfig.enableChaosFragment) {
            RegisterUtils.registerItem(CHAOS_FRAGMENT);
        }
        if (ContentConfig.enableChaosArmorAndTools) {
            RegisterUtils.registerItems(CHAOTIC_HELM, CHAOTIC_CHEST, CHAOTIC_LEGS, CHAOTIC_BOOTS, CHAOTIC_CAPICATOR, CHAOTIC_DISTRUCTION_STAFF, CHAOTIC_SWORD, CHAOTIC_BOW,
                    CHAOTIC_PICKAXE, CHAOTIC_SHOVEL, CHAOTIC_AXE);
        }
        if (ContentConfig.enableArialArmorAndTools) {
            RegisterUtils.registerItems(ARIAL_HELM, ARIAL_CHEST, ARIAL_LEGS, ARIAL_BOOTS, ARIAL_CAPICATOR, ARIAL_DISTRUCTION_STAFF, ARIAL_SWORD, ARIAL_BOW,
                    ARIAL_PICKAXE, ARIAL_SHOVEL, ARIAL_AXE);
        }
        if (ContentConfig.enableUpgradeModifier) {
            RegisterUtils.registerBlock(CUSTOM_UPGRADE_MODIFIER, ItemBlock.class);
            RegisterUtils.registerTile(TileCustomUpgradeModifier.class);
        }
    }

    public void init(FMLInitializationEvent e) {
        if (isClient())
            clientInit();
        if (ContentConfig.enableChaosHeart) {
            FMLCommonHandler.instance().bus().register(new OnDropEvent());
        }
        if (ContentConfig.enableChaosArmorAndTools || ContentConfig.enableArialArmorAndTools) {
            FMLCommonHandler.instance().bus().register(new CommonEventHandler());
        }
        if (ContentConfig.enableChaosArmorAndTools) {
            MinecraftForge.EVENT_BUS.register(new CEventHandler());
            FMLCommonHandler.instance().bus().register(new CEventHandler());
        }
        if (ContentConfig.enableArialArmorAndTools) {
            MinecraftForge.EVENT_BUS.register(new AEventHandler());
            FMLCommonHandler.instance().bus().register(new AEventHandler());
        }
        if (ContentConfig.enableChaosBlocks) {
            BlockChaosItemBlock.update();
        }
        BlockAwakenedDraconiumItemBlock.update();
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {
        SEffectHandler.iniEffectRenderer();
        if (ContentConfig.enableChaosArmorAndTools) {
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_HELM, new RenderChaoticArmor((ItemArmor) CHAOTIC_HELM));
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_CHEST, new RenderChaoticArmor((ItemArmor) CHAOTIC_CHEST));
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_LEGS, new RenderChaoticArmor((ItemArmor) CHAOTIC_LEGS));
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_BOOTS, new RenderChaoticArmor((ItemArmor) CHAOTIC_BOOTS));

            MinecraftForgeClient.registerItemRenderer(CHAOTIC_BOW, new RenderChaoticBow());
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_BOW, new RenderChaoticBowModel());
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_SWORD, new RenderChaoticTools("models/tools/ChaoticSword.obj", "textures/models/tools/ChaoticSword.png", (IRenderTweak) CHAOTIC_SWORD));

            MinecraftForgeClient.registerItemRenderer(CHAOTIC_PICKAXE, new RenderChaoticTools("models/tools/ChaoticPickaxe.obj", "textures/models/tools/ChaoticPickaxe.png", (IRenderTweak) CHAOTIC_PICKAXE));
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_AXE, new RenderChaoticTools("models/tools/ChaoticLumberAxe.obj", "textures/models/tools/ChaoticLumberAxe.png", (IRenderTweak) CHAOTIC_AXE));
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_SHOVEL, new RenderChaoticTools("models/tools/ChaoticShovel.obj", "textures/models/tools/ChaoticShovel.png", (IRenderTweak) CHAOTIC_SHOVEL));
            MinecraftForgeClient.registerItemRenderer(CHAOTIC_DISTRUCTION_STAFF, new RenderChaoticTools("models/tools/ChaoticStaffOfPower.obj", "textures/models/tools/ChaoticStaffOfPower.png", (IRenderTweak) CHAOTIC_DISTRUCTION_STAFF));
        }
        if (ContentConfig.enableArialArmorAndTools) {
            MinecraftForgeClient.registerItemRenderer(ARIAL_HELM, new RenderArialArmor((ItemArmor) ARIAL_HELM));
            MinecraftForgeClient.registerItemRenderer(ARIAL_CHEST, new RenderArialArmor((ItemArmor) ARIAL_CHEST));
            MinecraftForgeClient.registerItemRenderer(ARIAL_LEGS, new RenderArialArmor((ItemArmor) ARIAL_LEGS));
            MinecraftForgeClient.registerItemRenderer(ARIAL_BOOTS, new RenderArialArmor((ItemArmor) ARIAL_BOOTS));

            MinecraftForgeClient.registerItemRenderer(ARIAL_BOW, new RenderArialBow());
            MinecraftForgeClient.registerItemRenderer(ARIAL_BOW, new RenderArialBowModel());
            MinecraftForgeClient.registerItemRenderer(ARIAL_SWORD, new RenderArialTools("models/tools/ArialSword.obj", "textures/models/tools/ArialSword.png", (IRenderTweak) ARIAL_SWORD));

            MinecraftForgeClient.registerItemRenderer(ARIAL_PICKAXE, new RenderArialTools("models/tools/ArialPickaxe.obj", "textures/models/tools/ArialPickaxe.png", (IRenderTweak) ARIAL_PICKAXE));
            MinecraftForgeClient.registerItemRenderer(ARIAL_AXE, new RenderArialTools("models/tools/ArialLumberAxe.obj", "textures/models/tools/ArialLumberAxe.png", (IRenderTweak) ARIAL_AXE));
            MinecraftForgeClient.registerItemRenderer(ARIAL_SHOVEL, new RenderArialTools("models/tools/ArialShovel.obj", "textures/models/tools/ArialShovel.png", (IRenderTweak) ARIAL_SHOVEL));
            MinecraftForgeClient.registerItemRenderer(ARIAL_DISTRUCTION_STAFF, new RenderArialTools("models/tools/ArialStaffOfPower.obj", "textures/models/tools/ArialStaffOfPower.png", (IRenderTweak) ARIAL_DISTRUCTION_STAFF));
        }
        if (ContentConfig.enableChaosBlocks && ContentConfig.enableChaosHeart) {
            RenderingRegistry.registerEntityRenderingHandler(EntityChaoticHeart.class, new RenderChaoticHeart());
        }
        if (ContentConfig.enableArialBlock && ContentConfig.enableArialHeart) {
            RenderingRegistry.registerEntityRenderingHandler(EntityArialHeart.class, new RenderArialHeart());
        }
        if (ContentConfig.enableAssembler) {
            RegisterUtils.registerItemRenderer(Item.getItemFromBlock(DRACONIC_ASSEMBLER), (IItemRenderer) new RenderItemDraconicAssembler());
            RegisterUtils.registerTileRenderer(TileDraconicAssembler.class, (TileEntitySpecialRenderer) new RenderBlockDraconicAssembler());
        }
        if (ContentConfig.enableUpgradeModifier) {
            RegisterUtils.registerItemRenderer(Item.getItemFromBlock(CUSTOM_UPGRADE_MODIFIER), (IItemRenderer) new RenderItemCustomUpgradeModifier());
            RegisterUtils.registerTileRenderer(TileCustomUpgradeModifier.class, (TileEntitySpecialRenderer) new RenderCustomUpgradeModifier());
        }
        if (ContentConfig.enableFusion) {
            RegisterUtils.registerItemRenderer(Item.getItemFromBlock(FUSION_CORE), (IItemRenderer) new RenderItemFusionCraftingCore());
            RegisterUtils.registerTileRenderer(TileFusionCraftingCore.class, (TileEntitySpecialRenderer) new RenderTileFusionCraftingCore());
            RegisterUtils.registerItemRenderer(Item.getItemFromBlock(FUSION_INJECTOR), (IItemRenderer) new RenderItemFusionInjector());
            RegisterUtils.registerTileRenderer(TileFusionInjector.class, (TileEntitySpecialRenderer) new RenderTileFusionInjector());
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (ContentConfig.enableAutoAwakener) {
            registerAutoAwakenedBlocksDragonRecipe(new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconicBlock, 4),
                    new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconiumBlock, 4, 2),
                    new ItemStack(com.brandon3055.draconicevolution.common.ModItems.dragonHeart),
                    new ItemStack(ModItems.draconicCore, 16),
                    new ItemStack(Blocks.tnt));
            registerAutoAwakenedBlocksChaosRecipe(new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconicBlock, 4),
                    new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconiumBlock, 4, 2),
                    new ItemStack(com.brandon3055.draconicevolution.common.ModItems.dragonHeart),
                    new ItemStack(ModItems.draconicCore, 16),
                    new ItemStack(Blocks.tnt));
            registerAutoAwakenedBlocksArialRecipe(new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconicBlock, 4),
                    new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconiumBlock, 4, 2),
                    new ItemStack(com.brandon3055.draconicevolution.common.ModItems.dragonHeart),
                    new ItemStack(ModItems.draconicCore, 16),
                    new ItemStack(Blocks.tnt));

            if (ContentConfig.enableChaosBlocks) {
                if (ContentConfig.enableChaosHeart) {
                    registerAutoAwakenedBlocksChaosRecipe(new ItemStack(CHAOTIC_BLOCK, 4),
                            new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconicBlock, 4, 1),
                            new ItemStack(CHAOTIC_HEART),
                            new ItemStack(ModItems.awakenedCore, HellConfig.coresNeedsForChaotic),
                            new ItemStack(Blocks.tnt));
                    registerAutoAwakenedBlocksArialRecipe(new ItemStack(CHAOTIC_BLOCK, 4),
                            new ItemStack(com.brandon3055.draconicevolution.common.ModBlocks.draconicBlock, 4, 1),
                            new ItemStack(CHAOTIC_HEART),
                            new ItemStack(ModItems.awakenedCore, HellConfig.coresNeedsForChaotic),
                            new ItemStack(Blocks.tnt));
                }
                if (ContentConfig.enableArialBlock && ContentConfig.enableArialHeart) {
                    registerAutoAwakenedBlocksArialRecipe(new ItemStack(ARIAL_BLOCK, 4),
                            new ItemStack(CHAOTIC_BLOCK, 4, 1),
                            new ItemStack(ARIAL_HEART),
                            new ItemStack(ModItems.awakenedCore, HellConfig.coresNeedsForArial),
                            new ItemStack(Blocks.tnt));
                }
            }
        }
        if (ContentConfig.enableAssembler) {
            registerDraconicAssemblerRecipe(new ItemStack(ModItems.draconiumEnergyCore, 1), 0, 10000,
                    new ItemStack(ModItems.draconicCore, 2),
                    new ItemStack(ModItems.draconiumIngot, 6),
                    new ItemStack(Blocks.redstone_block, 8));
            removeCraftingRecipe(new ItemStack(ModItems.draconiumEnergyCore));
            registerDraconicAssemblerRecipe(new ItemStack(ModItems.draconiumEnergyCore, 1, 1), 1, 100000,
                    new ItemStack(ModItems.wyvernCore, 2),
                    new ItemStack(ModItems.draconiumEnergyCore, 6),
                    new ItemStack(ModItems.draconiumIngot, 8));
            removeCraftingRecipe(new ItemStack(ModItems.draconiumEnergyCore, 1, 1));

            if (ContentConfig.enableChaosIngot && ContentConfig.enableChaosFragment) {
                registerDraconicAssemblerRecipe(new ItemStack(CHAOS_FRAGMENT, 3), 2, 100000, new ItemStack(CHAOTIC_INGOT));

                if (ContentConfig.enableChaoticEnergyCore) {
                    registerDraconicAssemblerRecipe(new ItemStack(CHAOTIC_ENERGY_CORE, 1), 2, 1000000,
                            new ItemStack(ModItems.awakenedCore, 2),
                            new ItemStack(ModItems.draconiumEnergyCore, 6, 1),
                            new ItemStack(CHAOTIC_INGOT, 8),
                            new ItemStack(CHAOS_FRAGMENT, 4));

                    if (ContentConfig.enableArialCores && ContentConfig.enableArialBlock) {
                        registerDraconicAssemblerRecipe(new ItemStack(ARIAL_ENERGY_CORE, 1), 3, 10000000,
                                new ItemStack(ModItems.chaoticCore, 2),
                                new ItemStack(CHAOTIC_ENERGY_CORE, 6),
                                new ItemStack(ARIAL_BLOCK),
                                new ItemStack(CHAOS_FRAGMENT, 8));
                    }
                }
            }
            if (ContentConfig.enableChaosFragment && ContentConfig.enableChaoticEnergyCore && ContentConfig.enableArialCores && ContentConfig.enableArialBlock) {
                registerDraconicAssemblerRecipe(new ItemStack(ARIAL_ENERGY_CORE, 1), 3, 10000000,
                        new ItemStack(ModItems.chaoticCore, 2),
                        new ItemStack(CHAOTIC_ENERGY_CORE, 6),
                        new ItemStack(ARIAL_BLOCK),
                        new ItemStack(CHAOS_FRAGMENT, 8));
            }
            if (ContentConfig.enableEnergyUpgrades) {
                registerDraconicAssemblerRecipe(new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 0), 1, 75000,
                        new ItemStack(ModBlocks.draconiumBlock, 6, 2),
                        new ItemStack(ModBlocks.energyCrystal, 2),
                        new ItemStack(ModItems.draconiumEnergyCore, 4));
                registerDraconicAssemblerRecipe(new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 1), 2, 1500000,
                        new ItemStack(ModBlocks.draconicBlock, 8),
                        new ItemStack(ModItems.awakenedCore, 2),
                        new ItemStack(ModItems.draconiumEnergyCore, 6, 1),
                        new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 0),
                        new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 0));

                if (ContentConfig.enableChaosBlocks && ContentConfig.enableChaoticEnergyCore) {
                    registerDraconicAssemblerRecipe(new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 2), 3, 750000000,
                            new ItemStack(CHAOTIC_BLOCK, 12),
                            new ItemStack(ModItems.chaoticCore, 1),
                            new ItemStack(CHAOTIC_ENERGY_CORE, 8),
                            new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 1),
                            new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 1));
                }
                if (ContentConfig.enableArialBlock && ContentConfig.enableArialCores) {
                    registerDraconicAssemblerRecipe(new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 3), 4, 75000000000L,
                            new ItemStack(ARIAL_BLOCK, 4),
                            new ItemStack(ARIAL_CORE, 1),
                            new ItemStack(ARIAL_ENERGY_CORE, 8),
                            new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 2),
                            new ItemStack(DRACONIC_ENERGY_UPGRADES, 1, 2));
                }
            }
            if (ContentConfig.enableAutoAwakener) {
                registerDraconicAssemblerRecipe(new ItemStack(UPGRADE_DRAGON_AUTO_AWAKENED_BLOCKS), 1, 10000000,
                        AEApi.instance().definitions().materials().basicCard().maybeStack(16).get(),
                        AEApi.instance().definitions().materials().advCard().maybeStack(16).get(),
                        new ItemStack(ModBlocks.draconicBlock, 8),
                        new ItemStack(ModItems.awakenedCore));
                if (ContentConfig.enableChaosBlocks && ContentConfig.enableChaosFragment) {
                    registerDraconicAssemblerRecipe(new ItemStack(UPGRADE_CHAOS_AUTO_AWAKENED_BLOCKS), 2, 1000000000,
                            new ItemStack(UPGRADE_DRAGON_AUTO_AWAKENED_BLOCKS),
                            AEApi.instance().definitions().materials().basicCard().maybeStack(32).get(),
                            AEApi.instance().definitions().materials().advCard().maybeStack(32).get(),
                            new ItemStack(CHAOTIC_BLOCK, 4),
                            new ItemStack(ModItems.chaoticCore),
                            new ItemStack(CHAOS_FRAGMENT, 8));
                }
                if (ContentConfig.enableArialBlock && ContentConfig.enableArialCores && ContentConfig.enableChaosFragment) {
                    registerDraconicAssemblerRecipe(new ItemStack(UPGRADE_ARIAL_AUTO_AWAKENED_BLOCKS), 3, 100000000000L,
                            new ItemStack(UPGRADE_CHAOS_AUTO_AWAKENED_BLOCKS),
                            AEApi.instance().definitions().materials().basicCard().maybeStack(64).get(),
                            AEApi.instance().definitions().materials().advCard().maybeStack(64).get(),
                            new ItemStack(ARIAL_BLOCK, 4),
                            new ItemStack(ARIAL_CORE),
                            new ItemStack(CHAOS_FRAGMENT, 64));
                }
            }
            if (ContentConfig.enableStorageCells) {
                registerDraconicAssemblerRecipe(new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 1, 0), 0, 100000,
                        AEApi.instance().definitions().materials().emptyStorageCell().maybeStack(2).get(),
                        AEApi.instance().definitions().materials().singularity().maybeStack(4).get(),
                        AEApi.instance().definitions().materials().calcProcessor().maybeStack(8).get(),
                        AEApi.instance().definitions().materials().engProcessor().maybeStack(8).get(),
                        AEApi.instance().definitions().materials().logicProcessor().maybeStack(8).get());
                registerDraconicAssemblerRecipe(new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 1, 1), 0, 100000,
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 2, 0),
                        AEApi.instance().definitions().materials().singularity().maybeStack(8).get(),
                        AEApi.instance().definitions().materials().calcProcessor().maybeStack(16).get(),
                        AEApi.instance().definitions().materials().engProcessor().maybeStack(16).get(),
                        AEApi.instance().definitions().materials().logicProcessor().maybeStack(16).get());
                registerDraconicAssemblerRecipe(new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 1, 2), 0, 100000,
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 2, 1),
                        AEApi.instance().definitions().materials().singularity().maybeStack(16).get(),
                        AEApi.instance().definitions().materials().calcProcessor().maybeStack(32).get(),
                        AEApi.instance().definitions().materials().engProcessor().maybeStack(32).get(),
                        AEApi.instance().definitions().materials().logicProcessor().maybeStack(32).get());
                registerDraconicAssemblerRecipe(new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 1, 3), 0, 100000,
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 2, 2),
                        AEApi.instance().definitions().materials().singularity().maybeStack(32).get(),
                        AEApi.instance().definitions().materials().calcProcessor().maybeStack(64).get(),
                        AEApi.instance().definitions().materials().engProcessor().maybeStack(64).get(),
                        AEApi.instance().definitions().materials().logicProcessor().maybeStack(64).get());
                registerDraconicAssemblerRecipe(new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 1, 4), 0, 100000,
                        AEApi.instance().definitions().materials().singularity().maybeStack(64).get(),
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 8, 0),
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 8, 1),
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 8, 2),
                        new ItemStack(foxiwhitee.hellmod.ModItems.CUSTOM_EMPTY_STORAGE_CELLS, 8, 3));
            }
            if (ContentConfig.enableFusion) {
                registerFusionRecipe(new ItemStack(DRACONIC_ASSEMBLER), new ItemStack(FUSION_CORE), 0, 100000,
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(FUSION_INJECTOR),
                        new ItemStack(ModItems.draconicCore),
                        new ItemStack(ModItems.draconicCore));
            }
        }
    }

    public static void register(BlockDE block, Class<? extends ItemBlock> item) {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        GameRegistry.registerBlock(block, item, name.substring(name.indexOf(":") + 1));
    }

    public static FusionRecipe findRecipeFusion(IFusionCraftingInventory inventory, World world, int x, int y, int z) {
        if (inventory == null || OreDictUtil.isEmpty(inventory.getStackInCore(0)))
            return null;
        for (FusionRecipe recipe : ModRecipes.fusionRecipes) {
            if (recipe.matches(inventory, world, x, y, z))
                return recipe;
        }
        return null;
    }

    public static void bindResource(String rs) {
        bindTexture(getResource(rs));
    }

    public static void bindTexture(ResourceLocation texture) {
        (Minecraft.getMinecraft()).renderEngine.bindTexture(texture);
    }

    public static ResourceLocation getResource(String rs) {
        if (!cachedResources.containsKey(rs))
            cachedResources.put(rs, new ResourceLocation(HellCore.MODID + ":" + rs));
        return cachedResources.get(rs);
    }
}
