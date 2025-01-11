package net.just_s.mixin;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.List;

public class MixinManager implements MixinAnnotationAdjuster {
    @Override
    public AdjustableAnnotationNode adjust(List<String> targetClassNames, String mixinClassName, MethodNode handlerNode, AdjustableAnnotationNode annotationNode) {

        // This disables Ephemera mixin that interferes with attribute logic
        if(!mixinClassName.equals("net.beholderface.ephemera.mixin.LessThanEqualToSentinelMixin")) return annotationNode;
        if(!annotationNode.is(Inject.class)) return annotationNode;

        return null;
    }
}
