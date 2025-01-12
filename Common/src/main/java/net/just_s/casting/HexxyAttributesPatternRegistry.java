package net.just_s.casting;

import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.api.casting.castables.Action;
import at.petrak.hexcasting.api.casting.math.HexDir;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.common.lib.HexRegistries;
import dev.architectury.registry.registries.DeferredRegister;
import net.just_s.HexxyAttributesMod;
import net.just_s.casting.patterns.*;

public class HexxyAttributesPatternRegistry {
    private static final DeferredRegister<ActionRegistryEntry> ACTIONS = DeferredRegister.create(
            HexxyAttributesMod.MOD_ID, HexRegistries.ACTION
    );

    public static final ActionRegistryEntry DOMAIN_REFLECTION = createAction(
            "domain_reflection", "qaqeaa", HexDir.NORTH_EAST, new OpDomainReflection()
    );

    public static final ActionRegistryEntry SENTINEL_DOMAIN_REFLECTION = createAction(
            "sentinel_domain_reflection", "aeawaeadaa", HexDir.EAST, new OpSentinelDomainReflection()
    );

    public static final ActionRegistryEntry MEDIA_REFLECTION = createAction(
            "media_reflection", "wwaqwqeaa", HexDir.NORTH_EAST, new OpMediaReflection()
    );

    public static final ActionRegistryEntry MIND_PURIFICATION = createAction(
            "mind_purification", "waaqa", HexDir.EAST, new OpMindPurification()
    );

    private static ActionRegistryEntry createAction(final String name, final String signature, HexDir startDirection, Action action) {
        ActionRegistryEntry entry = new ActionRegistryEntry(HexPattern.fromAngles(signature, startDirection), action);
        register(name, entry);
        return entry;
    }

    private static void register(String id, ActionRegistryEntry entry) {
        ACTIONS.register(id, () -> entry);
    }

    public static void register() {
        ACTIONS.register();
    }
}
