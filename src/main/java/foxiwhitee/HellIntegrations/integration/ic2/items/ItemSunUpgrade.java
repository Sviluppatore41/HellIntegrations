package foxiwhitee.HellIntegrations.integration.ic2.items;

import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.draconic.DraconicEvolutionIntegration;
import foxiwhitee.HellIntegrations.utils.helpers.EnergyUtility;
import foxiwhitee.HellIntegrations.utils.localization.LocalizationUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemSunUpgrade extends Item {
    public ItemSunUpgrade(String name) {
        this.setUnlocalizedName(name);
        this.setCreativeTab(HellCore.HELL_TAB);
        this.setMaxStackSize(1);
        this.setTextureName(HellCore.MODID.toLowerCase() + ":ic2/upgrades/" + name);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(String.format(LocalizationUtils.localize("tooltip.wyvern_storage_card.desc"), LocalizationUtils.localize(DraconicEvolutionIntegration.DRACONIC_ASSEMBLER.getUnlocalizedName() + ".name"), EnergyUtility.formatNumber(HellConfig.energyUpgradeWywern)));
    }
}
