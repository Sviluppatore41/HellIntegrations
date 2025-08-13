package foxiwhitee.HellIntegrations.integration.thaumcraft.client.render;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderItemStabilizer implements IItemRenderer {

    private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(HellCore.MODID.toLowerCase(), "models/stabilizer.obj"));

    public boolean handleRenderType(ItemStack is, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glTranslated(0.0D, -0.5D, 0.0D);
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        switch (type) {
            case ENTITY:
                GL11.glScaled(1.35D, 1.35D, 1.35D);
                GL11.glTranslated(0.0D, 0.0D, 0.0D);
                break;
        }
        (Minecraft.getMinecraft()).renderEngine.bindTexture(this.getTexture());
        this.model.renderAll();
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getTexture() {
        return new ResourceLocation(HellCore.MODID.toLowerCase(), "textures/blocks/stabilizer.png");
    }
}
