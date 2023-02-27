package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TESounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TinkersExpansion.MOD_ID);

    public static final RegistryObject<SoundEvent> ELECTROCUTION = SOUND_EVENTS.register("electrocution", () -> new SoundEvent(new ResourceLocation(TinkersExpansion.MOD_ID, "electrocution")));
}
