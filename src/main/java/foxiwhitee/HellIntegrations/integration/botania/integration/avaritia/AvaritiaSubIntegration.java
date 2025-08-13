package foxiwhitee.HellIntegrations.integration.botania.integration.avaritia;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.integration.IIntegration;
import foxiwhitee.HellIntegrations.integration.Integration;
import foxiwhitee.HellIntegrations.integration.botania.integration.avaritia.items.ItemGenerationFlowerCoresAvaritia;
import foxiwhitee.HellIntegrations.integration.botania.integration.avaritia.tile.Signature;
import foxiwhitee.HellIntegrations.integration.botania.integration.avaritia.tile.SubTileHelibrium;
import foxiwhitee.HellIntegrations.integration.botania.integration.avaritia.tile.SubTileMidgaran;
import foxiwhitee.HellIntegrations.integration.botania.integration.avaritia.tile.SubTileValharin;
import foxiwhitee.HellIntegrations.utils.helpers.RegisterUtils;
import net.minecraft.item.Item;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.signature.SubTileSignature;

@Integration(modid = "Botania", dependencies = {"Avaritia"})
public class AvaritiaSubIntegration implements IIntegration {

    public static final Item GENERATION_FLOWER_CORES_AVARITIA = new ItemGenerationFlowerCoresAvaritia("generationFlowerCoresAvaritia");

    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableAsgardandelions) {
            BotaniaAPI.registerSubTile("helibrium", SubTileHelibrium.class);
            BotaniaAPI.registerSubTileSignature(SubTileHelibrium.class, (SubTileSignature) new Signature("helibrium"));
            BotaniaAPI.addSubTileToCreativeMenu("helibrium");
            BotaniaAPI.registerSubTile("valharin", SubTileValharin.class);
            BotaniaAPI.registerSubTileSignature(SubTileValharin.class, (SubTileSignature) new Signature("valharin"));
            BotaniaAPI.addSubTileToCreativeMenu("valharin");
            BotaniaAPI.registerSubTile("midgaran", SubTileMidgaran.class);
            BotaniaAPI.registerSubTileSignature(SubTileMidgaran.class, (SubTileSignature) new Signature("midgaran"));
            BotaniaAPI.addSubTileToCreativeMenu("midgaran");
        }
        if (ContentConfig.enableMEMana && ContentConfig.enableManaGenerator) {
            RegisterUtils.registerItem(GENERATION_FLOWER_CORES_AVARITIA);
        }
    }

    public void init(FMLInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}

}
