package net.just_s.mixin;

import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.xplat.IXplatAbstractions;
import net.just_s.HexxyAttributesMod;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CastingContext.class)
public class CastingContextMixin_RadiusAttributes {
    @Shadow
    @Final
    private ServerPlayerEntity caster;

    // Due to Ephemera making a breaking inject into CastingContext (and its overall popularity)
    // I just made my mixin injecting before Ephemera's does
    @Inject(
            method = "isVecInRange",
            at = @At("HEAD"), cancellable = true, remap = false
    )
    public final void hexxyattributes$replaceStaticRadiusesToAttributes(Vec3d vec, CallbackInfoReturnable<Boolean> cir) {
        double sentinel_radius = this.caster.getAttributeValue(HexxyAttributesMod.SENTINEL_RADIUS);
        var sentinel = IXplatAbstractions.INSTANCE.getSentinel(this.caster);
        if (sentinel != null
                && sentinel.extendsRange()
                && this.caster.getWorld().getRegistryKey() == sentinel.dimension()
                && vec.squaredDistanceTo(sentinel.position()) <= sentinel_radius * sentinel_radius + 0.00000000001
        ) {
            cir.setReturnValue(true);
            return;
        }

        double ambit_radius = this.caster.getAttributeValue(HexxyAttributesMod.AMBIT_RADIUS);
        cir.setReturnValue(vec.squaredDistanceTo(this.caster.getPos()) <= ambit_radius * ambit_radius + 0.00000000001);
    }
}