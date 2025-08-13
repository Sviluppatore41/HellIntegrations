package foxiwhitee.HellIntegrations.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"foxiwhitee.HellIntegrations.asm"})
public class ASMCoreMod implements IFMLLoadingPlugin {
    public static boolean isObfEnv;

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                "foxiwhitee.HellIntegrations.asm.ASMClassTransformer",
                "foxiwhitee.HellIntegrations.asm.ae2.cables.CablesClassTransformer"
        };
    }

    @Override
    public void injectData(Map<String, Object> data) {
        isObfEnv = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
