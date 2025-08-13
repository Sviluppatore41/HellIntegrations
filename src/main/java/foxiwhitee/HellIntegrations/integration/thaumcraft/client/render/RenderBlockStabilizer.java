package foxiwhitee.HellIntegrations.integration.thaumcraft.client.render;

import foxiwhitee.HellIntegrations.client.render.TileEntitySpecialRendererObjWrapper;
import foxiwhitee.HellIntegrations.integration.thaumcraft.tile.TileStabilizer;
import org.lwjgl.opengl.GL11;

public class RenderBlockStabilizer extends TileEntitySpecialRendererObjWrapper<TileStabilizer> {
    public RenderBlockStabilizer() {
        super(TileStabilizer.class, "models/stabilizer.obj", "textures/blocks/stabilizer.png");
        createList("all");
    }

    public void renderAt(TileStabilizer tileEntity, double x, double y, double z, double f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y, z + 0.5);
        GL11.glRotated((tileEntity.getWorldObj().getTotalWorldTime() % 2800) * 10.0D, 0.0F, -1.0F, 0.0F);
        bindTexture();
        renderPart("all");
        GL11.glPopMatrix();

    }
}