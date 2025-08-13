package foxiwhitee.hellmod.items.storage;

import foxiwhitee.hellmod.HellCore;
import foxiwhitee.hellmod.utils.localization.LocalizationUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemCustomStorageComponent extends Item {
    private final static String[] prefixes = {"256K", "1M", "4M", "16M", "65M", "262M", "1G", "4G", "16G", "67G", "268G", "1T", "4T", "68T", "274T", "1P", "4P", "17P", "70P", "281P", "1E", "4E", "9E"};
    private final IIcon[] icons = new IIcon[prefixes.length];
    private final String name;

    public ItemCustomStorageComponent(String name) {
        this.name = name;
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
            icons[i] = register.registerIcon(HellCore.MODID + ":storageComponents/" + name + prefixes[i]);
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
