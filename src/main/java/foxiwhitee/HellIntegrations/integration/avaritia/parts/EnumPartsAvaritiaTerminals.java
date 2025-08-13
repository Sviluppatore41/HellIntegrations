package foxiwhitee.HellIntegrations.integration.avaritia.parts;

import appeng.api.parts.IPart;
import appeng.core.features.AEFeature;
import appeng.core.localization.GuiText;
import appeng.integration.IntegrationType;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.integration.thaumcraft.ThaumcraftIntegration;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum EnumPartsAvaritiaTerminals {
    InvalidTypeAvaritia(-1, EnumSet.of(AEFeature.Core), EnumSet.noneOf(IntegrationType.class), (Class)null, false),
    PART_BIG_PATTERN_TERMINAL(0, EnumSet.of(AEFeature.Core), EnumSet.noneOf(IntegrationType.class), (Class) PartBigPatternTerminal.class, ContentConfig.enableBigPatternTerminal),
    PART_CRAFTING_TERMINAL_9X9(1, EnumSet.of(AEFeature.Core), EnumSet.noneOf(IntegrationType.class), (Class) PartCraftingTerminal9x9.class, ContentConfig.enableBigCraftingTerminal),
    PART_NEUTRON_COMPRESSOR_PATTERN_TERMINAL(2, EnumSet.of(AEFeature.Core), EnumSet.noneOf(IntegrationType.class), PartNeutronCompressorPatternTerminal.class, ContentConfig.enableNeutronPatternTerminal),
    PART_BIG_PROCESSING_PATTERN_TERMINAL(3, EnumSet.of(AEFeature.Core), EnumSet.noneOf(IntegrationType.class), PartBigProcessingPatternTerminal.class, ContentConfig.enableBigProcessingPatternTerminal);

    private final int baseDamage;
    private final Set<AEFeature> features;
    private final Set<IntegrationType> integrations;
    private final Class<? extends IPart> myPart;
    private final GuiText extraName;
    private Constructor<? extends IPart> constructor;
    private final boolean register;

    private EnumPartsAvaritiaTerminals(int baseMetaValue, Set<AEFeature> features, Set<IntegrationType> integrations, Class<? extends IPart> c, boolean register) {
        this(baseMetaValue, features, integrations, c, (GuiText)null, register);
    }

    private EnumPartsAvaritiaTerminals(int baseMetaValue, Set<AEFeature> features, Set<IntegrationType> integrations, Class<? extends IPart> c, GuiText en, boolean register) {
        this.features = Collections.unmodifiableSet(features);
        this.integrations = Collections.unmodifiableSet(integrations);
        this.myPart = c;
        this.extraName = en;
        this.baseDamage = baseMetaValue;
        this.register = register;
    }

    public boolean isCable() {
        return false;
    }

    public Set<AEFeature> getFeature() {
        return this.features;
    }

    public Set<IntegrationType> getIntegrations() {
        return this.integrations;
    }

    public Class<? extends IPart> getPart() {
        return this.myPart;
    }

    public GuiText getExtraName() {
        return this.extraName;
    }

    public Constructor<? extends IPart> getConstructor() {
        return this.constructor;
    }

    public void setConstructor(Constructor<? extends IPart> constructor) {
        this.constructor = constructor;
    }

    public int getBaseDamage() {
        return this.baseDamage;
    }

    public ItemStack getStack() {
        return new ItemStack(ThaumcraftIntegration.ITEM_PARTS_TERMINALS, 1, this.getBaseDamage());
    }

    public boolean isRegister() {
        return register;
    }
}