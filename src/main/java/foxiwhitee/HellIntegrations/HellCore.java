package foxiwhitee.HellIntegrations;

import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import foxiwhitee.HellIntegrations.commands.CommandReloadLocalization;
import foxiwhitee.HellIntegrations.integration.IntegrationLoader;
import foxiwhitee.HellIntegrations.network.NetworkManager;
import foxiwhitee.HellIntegrations.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import foxiwhitee.HellIntegrations.utils.helpers.GuiHandler;
import foxiwhitee.HellIntegrations.utils.localization.LocalizationUtils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import static foxiwhitee.HellIntegrations.HellCore.*;

@Mod(modid = MODID, name = MODNAME, version = VERSION, dependencies = DEPEND)
public class HellCore {
    public static final String
            MODID = "HellIntegrations",
            MODNAME = "Hell Integrations",
            VERSION = "1.0.0",
            DEPEND = "required-after:appliedenergistics2;";// +
                    //"required-after:Avaritia;" +
                    //"required-after:AdvancedSolarPanel;" +
                    //"required-after:Botania;" +
                    //"required-after:DraconicEvolution;" +
                   // "required-after:Thaumcraft;";


    public static final CreativeTabs HELL_TAB = new CreativeTabs("HELL_TAB") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.bedrock);
        }
    };
    public static boolean BloodMagic;

    @Mod.Instance(MODID)
    public static HellCore instance;

    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @SidedProxy(clientSide = "foxiwhitee.HellIntegrations.proxy.ClientProxy", serverSide = "foxiwhitee.HellIntegrations.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Instance
    public static HellCore INS;

    public static Block pfpConverter;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
        NetworkRegistry.INSTANCE.registerGuiHandler(INS, new GuiHandler());
        IntegrationLoader.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        IntegrationLoader.init(e);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
        NetworkManager.instance = new NetworkManager("hellintegrations");
        IntegrationLoader.postInit(e);
        LocalizationUtils.findUnlocalizedNames();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandReloadLocalization());
    }
}
