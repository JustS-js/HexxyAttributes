package net.just_s.forge;

import dev.architectury.platform.forge.EventBuses;
import net.just_s.HexxyAttributesMod;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(HexxyAttributesMod.MOD_ID)
public class HexxyAttributesModForge {
    public HexxyAttributesModForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(HexxyAttributesMod.MOD_ID, bus);
        bus.addListener(HexxyAttributesModClientForge::init);
        HexxyAttributesMod.init();

        bus.addListener(
                (EntityAttributeModificationEvent e) -> {
                    e.add(EntityType.PLAYER, HexxyAttributesMod.FEEBLE_MIND);
                    e.add(EntityType.PLAYER, HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER);
                    e.add(EntityType.PLAYER, HexxyAttributesMod.AMBIT_RADIUS);
                    e.add(EntityType.PLAYER, HexxyAttributesMod.SENTINEL_RADIUS);
                }
        );

//        bus.addListener(
//                (RegisterEvent e) -> {
//                    if (e.getRegistryKey().equals(HexRegistries.ACTION)) {
//                        HexxyAttributesMod.LOGGER.info("HI");
//                        HexxyAttributesPatternRegistry.register();
//                    }
//                }
//        );
    }
}
