package net.just_s.mixin;

import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.CastingHarness;
import net.just_s.HexxyAttributesMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = CastingHarness.class, remap = false)
public abstract class MediaHelperMixin_ModifyConsumptionModifier {
    @Shadow public abstract CastingContext getCtx();

    @ModifyVariable(
            method = "withdrawMedia",
            at = @At("HEAD"), ordinal = 0,
            argsOnly = true, remap = false
    )
    private int hexxyattributes$modifyCost(int cost) {
        return (int)(cost * this.getCtx().getCaster().getAttributeValue(HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER));
    }
}
