package net.just_s.mixin;

import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.xplat.IXplatAbstractions;
import net.just_s.HexxyAttributesMod;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = CastingContext.class)
public class CastingContextMixin_RadiusAttributes {
    @Shadow
    @Final
    protected ServerPlayerEntity caster;

    /**
     * @author Just_S
     * @reason due to mixin's inability to modify local variables in this scenario it is easier to overwrite the method.
     */
    @Overwrite
    public final boolean isVecInRange(Vec3d vec) {
        double sentinel_radius = this.caster.getAttributeValue(HexxyAttributesMod.SENTINEL_RADIUS);
        var sentinel = IXplatAbstractions.INSTANCE.getSentinel(this.caster);
        if (sentinel != null
                && sentinel.extendsRange()
                && this.caster.getWorld().getRegistryKey() == sentinel.dimension()
                && vec.squaredDistanceTo(sentinel.position()) <= sentinel_radius * sentinel_radius + 0.00000000001
        ) {
            return true;
        }

        double ambit_radius = this.caster.getAttributeValue(HexxyAttributesMod.AMBIT_RADIUS);
        return vec.squaredDistanceTo(this.caster.getPos()) <= ambit_radius * ambit_radius + 0.00000000001;
    }
}