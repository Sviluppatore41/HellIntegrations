package foxiwhitee.HellIntegrations.integration.botania.items.ae;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.item.Item;

public class ItemUpgradeFlowerSynthesizer extends Item {
    public ItemUpgradeFlowerSynthesizer(String name) {
        setCreativeTab(HellCore.HELL_TAB);
        setUnlocalizedName(name);
        setTextureName(HellCore.MODID.toLowerCase() + ":botania/upgrades/" + name);
    }
}