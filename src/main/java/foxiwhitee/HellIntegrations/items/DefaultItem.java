package foxiwhitee.HellIntegrations.items;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.item.Item;

public class DefaultItem extends Item {
    boolean mode = false;

    public DefaultItem(String name, String texture) {
        this.canRepair = false;
        this.setUnlocalizedName(name);
        this.setTextureName(HellCore.MODID.toLowerCase() + ":" + texture);
        this.setCreativeTab(HellCore.HELL_TAB);
        //this.setMaxDamage(800);
        this.maxStackSize = 64;
    }

    public DefaultItem(String name) {
        this(name, name);
    }
}
