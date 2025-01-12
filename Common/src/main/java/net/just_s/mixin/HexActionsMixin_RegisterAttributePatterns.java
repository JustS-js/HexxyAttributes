package net.just_s.mixin;

import at.petrak.hexcasting.common.lib.hex.HexActions;
import net.just_s.casting.HexxyAttributesPatternRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HexActions.class)
public class HexActionsMixin_RegisterAttributePatterns {
    @Inject(at = @At("HEAD"), method = "register", remap = false)
    private static void hexxyattributes$registerPatterns(CallbackInfo info) {
        // made this as a mixin for easier backporting
        HexxyAttributesPatternRegistry.register();
    }
}