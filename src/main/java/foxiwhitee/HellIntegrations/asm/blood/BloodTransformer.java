package foxiwhitee.HellIntegrations.asm.blood;

import static foxiwhitee.HellIntegrations.utils.asm.ASMUtils.locateOpcodeSequence;
import static foxiwhitee.HellIntegrations.utils.asm.ASMUtils.writeClass;
import static org.objectweb.asm.Opcodes.*;
import foxiwhitee.HellIntegrations.utils.asm.ASMUtils;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.tree.*;

public class BloodTransformer {
    public static final boolean IS_DEV = (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    public static byte[] transformUpgradedAltars(byte[] basicClass) {
        ClassNode classNode = ASMUtils.readClass(basicClass);
        MethodNode methodNode = ASMUtils.findMethod(classNode, new String[] { "getUpgrades" });
        if (methodNode == null)
            return basicClass;
        AbstractInsnNode point = locateOpcodeSequence(methodNode.instructions, ALOAD, CHECKCAST, ILOAD, INVOKEVIRTUAL, TABLESWITCH);
        if (point != null) {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(ALOAD, 5));
            list.add(new VarInsnNode(ALOAD, 9));
            list.add(new VarInsnNode(ILOAD, 10));
            list.add(new MethodInsnNode(
                    INVOKESTATIC,
                    "foxiwhitee/HellIntegrations/asm/blood/BloodHooks",
                    "getAdditionalUpgrades",
                    "(LWayofTime/alchemicalWizardry/common/bloodAltarUpgrade/AltarUpgradeComponent;Lnet/minecraft/block/Block;I)V",
                    false
            ));
            methodNode.instructions.insertBefore(point, list);
        }
        return writeClass(classNode);
    }
}
