package foxiwhitee.HellIntegrations.integration.avaritia.items;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import foxiwhitee.HellIntegrations.helpers.UniversalPatternHelper;
import foxiwhitee.HellIntegrations.integration.avaritia.recipes.CustomExtremeShapelessRecipe;
import foxiwhitee.HellIntegrations.items.patterns.ItemUniversalEncodedPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemEncodedBigProcessingPattern extends ItemUniversalEncodedPattern {
    public ItemEncodedBigProcessingPattern(String name) {
        super(name);
    }

    public ICraftingPatternDetails getPatternForItem(ItemStack is, World w) {
        try {
            return new UniversalPatternHelper(is, false, 9, 9, matrix -> {
                NBTTagList outTag = is.getTagCompound().getTagList("out", 10);
                NBTTagList inTag = is.getTagCompound().getTagList("in", 10);
                ItemStack out = ItemStack.loadItemStackFromNBT(outTag.getCompoundTagAt(0)), gs;
                List in = new ArrayList();
                for(int x = 0; x < inTag.tagCount(); ++x) {
                    gs = ItemStack.loadItemStackFromNBT(inTag.getCompoundTagAt(x));
                    in.add(gs);
                }
                if (out != null) {
                    return new CustomExtremeShapelessRecipe(out, in);
                }
                return null;
            });
        } catch (Throwable e) {
            return null;
        }
    }
}
