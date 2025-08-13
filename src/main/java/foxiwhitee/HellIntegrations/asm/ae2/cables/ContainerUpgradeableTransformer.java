package foxiwhitee.HellIntegrations.asm.ae2.cables;

import foxiwhitee.HellIntegrations.utils.asm.ASMClassTransformer;
import foxiwhitee.HellIntegrations.utils.asm.SpecialClassNode;
import org.objectweb.asm.ClassReader;

public class ContainerUpgradeableTransformer implements ASMClassTransformer {
  public ASMClassTransformer.TransformResult transformClass(String name, String transformedName, ClassReader reader, SpecialClassNode classNode) {
    classNode.addInterface("foxiwhitee/HellIntegrations/utils/cables/IContainerUpgradeableAccessor").invoke("call").build();
    return ASMClassTransformer.TransformResult.MODIFIED_STACK;
  }
}
