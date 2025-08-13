package foxiwhitee.HellIntegrations.items.storage;

import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.utils.localization.LocalizationUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemCustomEmptyCells extends Item {
    private final static String[] prefixes = {"advancedEmptyCell", "hybridEmptyCell", "ultimateEmptyCell", "quantumEmptyCell", "singularEmptyCell"};
    private final IIcon[] icons = new IIcon[prefixes.length];

    public ItemCustomEmptyCells(String name) {
        setCreativeTab(HellCore.HELL_TAB);
        setUnlocalizedName(name);
        hasSubtypes = true;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        meta = Math.max(0, Math.min(meta, prefixes.length - 1));
        return icons[meta];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i < prefixes.length; i++) {
            icons[i] = register.registerIcon(HellCore.MODID.toLowerCase() + ":storageCells/" + prefixes[i]);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        int meta = (stack != null) ? stack.getItemDamage() : 0;
        return LocalizationUtils.localize(getUnlocalizedName() + ".name", prefixes[meta]);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < prefixes.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

}
