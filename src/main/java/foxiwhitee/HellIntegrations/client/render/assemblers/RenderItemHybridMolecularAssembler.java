package foxiwhitee.HellIntegrations.client.render.assemblers;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.util.ResourceLocation;

public class RenderItemHybridMolecularAssembler extends RenderItemCustomMolecularAssembler{
    @Override
    protected ResourceLocation getTexture() {
        return new ResourceLocation(HellCore.MODID.toLowerCase(), "textures/blocks/ae2/hybrid_molecular_assembler.png");
    }
}
