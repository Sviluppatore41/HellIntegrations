package foxiwhitee.HellIntegrations.integration.nei;


import codechicken.nei.api.API;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.IConfigureNEI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.integration.avaritia.client.gui.GuiPartBigPatternTerminal;
import foxiwhitee.HellIntegrations.integration.avaritia.client.gui.GuiPartNeutronCompressorPatternTerminal;
import foxiwhitee.HellIntegrations.integration.botania.client.gui.*;
import foxiwhitee.HellIntegrations.integration.draconic.DraconicEvolutionIntegration;
import foxiwhitee.HellIntegrations.integration.draconic.client.gui.GuiDraconicAssembler;
import foxiwhitee.HellIntegrations.integration.draconic.client.gui.GuiFusionCraftingCore;
import foxiwhitee.HellIntegrations.integration.nei.avaritia.ExtremeRecipeOverlayHandler;
import foxiwhitee.HellIntegrations.integration.nei.botania.BotaniaRecipeOverlayHandler;
import foxiwhitee.HellIntegrations.integration.nei.draconic.DraconicNEIGuiHandler;
import foxiwhitee.HellIntegrations.integration.nei.draconic.assembler.DrAssemblerRecipeOverlayHandler;
import foxiwhitee.HellIntegrations.integration.nei.draconic.assembler.GuiDraconicAssemblerHandler;
import foxiwhitee.HellIntegrations.integration.nei.draconic.fusion.GuiFusionCraftingHandler;
import foxiwhitee.HellIntegrations.integration.nei.thaumcraft.InfusionRecipeOverlayHandler;
import foxiwhitee.HellIntegrations.integration.thaumcraft.client.gui.GuiPartAlchemicalConstructionPatternTerminal;
import foxiwhitee.HellIntegrations.integration.thaumcraft.client.gui.GuiPartInfusionPatternTerminal;
import net.minecraft.item.ItemStack;
import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.nbt.NBTTagCompound;
import java.awt.Rectangle;
import java.util.List;

public class NEIHellConfig implements IConfigureNEI {
    @Override
    public void loadConfig() {
        List<TemplateRecipeHandler> recipeHandlerList = new ArrayList<>();

        if (ContentConfig.enableAssembler) {
            recipeHandlerList.add(new GuiDraconicAssemblerHandler());
            API.registerGuiOverlay(GuiDraconicAssembler.class, "drassembler", 5, 11);
            GuiInfo.customSlotGuis.add(GuiDraconicAssembler.class);
            API.registerGuiOverlayHandler(GuiDraconicAssembler.class, new DrAssemblerRecipeOverlayHandler(), "drassembler");
            sendHandlerInfo("foxiwhitee.HellIntegrations.integration.nei.draconic.assembler.GuiDraconicAssemblerHandler", DraconicEvolutionIntegration.DRACONIC_ASSEMBLER.getUnlocalizedName().replace("tile.", ""), 112, 164, 2);
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.DRACONIC_ASSEMBLER), "drassembler");
        }
        if (ContentConfig.enableFusion) {
            recipeHandlerList.add(new GuiFusionCraftingHandler());
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.FUSION_CORE), "fusioncrafting");
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.FUSION_INJECTOR, 1, 0), "fusioncrafting");
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.FUSION_INJECTOR, 1, 1), "fusioncrafting");
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.FUSION_INJECTOR, 1, 2), "fusioncrafting");
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.FUSION_INJECTOR, 1, 3), "fusioncrafting");
            API.addRecipeCatalyst(new ItemStack(DraconicEvolutionIntegration.FUSION_INJECTOR, 1, 4), "fusioncrafting");
            sendHandlerInfo("foxiwhitee.HellIntegrations.integration.nei.draconic.fusion.GuiFusionCraftingHandler", DraconicEvolutionIntegration.FUSION_CORE.getUnlocalizedName().replace("tile.", ""), 112, 164, 2);
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(Arrays.asList(GuiFusionCraftingCore.class ), Collections.singletonList(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(98, 63, 11, 31), "fusioncrafting", new Object[0])));
        }
        if (ContentConfig.enableUpgradeModifier) {
            API.registerNEIGuiHandler(new DraconicNEIGuiHandler());
        }
        if (ContentConfig.enableNeutronPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartNeutronCompressorPatternTerminal.class, new TerminalsRecipeOverlayHandler(1), "extreme_compression");
        }
        BotaniaRecipeOverlayHandler botaniaRecipeOverlayHandler = new BotaniaRecipeOverlayHandler();

        if (ContentConfig.enableElvenTradePatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartElvenTradePatternTerminal.class, botaniaRecipeOverlayHandler, null);
        }
        if (ContentConfig.enableManaPoolPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartManaPoolPatternTerminal.class, botaniaRecipeOverlayHandler, null);
        }
        if (ContentConfig.enablePetalsPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartPetalsPatternTerminal.class, botaniaRecipeOverlayHandler, null);
        }
        if (ContentConfig.enablePureDaisyPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartPureDaisyPatternTerminal.class, botaniaRecipeOverlayHandler, null);
        }
        if (ContentConfig.enableRuneAltarPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartRuneAltarPatternTerminal.class, botaniaRecipeOverlayHandler, null);
        }
        if (ContentConfig.enableCruciblePatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartAlchemicalConstructionPatternTerminal.class, new TerminalsRecipeOverlayHandler(1), "cruciblerecipe");
        }
        if (ContentConfig.enableInfusionPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartInfusionPatternTerminal.class, new InfusionRecipeOverlayHandler(), "infusionCrafting");
        }
        if (ContentConfig.enableBigPatternTerminal) {
            API.registerGuiOverlayHandler(GuiPartBigPatternTerminal.class, new ExtremeRecipeOverlayHandler(), "extreme");
            API.registerGuiOverlay(GuiPartBigPatternTerminal.class, "extreme", 267, 15);
        }

        recipeHandlerList.forEach(handler -> {
            API.registerRecipeHandler(handler);
            API.registerUsageHandler(handler);
        });
    }

    private void sendHandlerInfo(String handler, String nameItem, int height, int width, int perPage) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("handler", handler);
        tag.setString("modName", HellCore.MODNAME);
        tag.setString("modId", HellCore.MODID.toLowerCase());
        tag.setString("itemName", HellCore.MODID.toLowerCase() + ":" + nameItem);
        tag.setInteger("handlerHeight", height);
        tag.setInteger("handlerWidth", width);
        tag.setInteger("maxRecipesPerPage", perPage);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", tag);
    }

    @Override
    public String getName() {
        return HellCore.MODNAME;
    }

    @Override
    public String getVersion() {
        return HellCore.VERSION;
    }
}
