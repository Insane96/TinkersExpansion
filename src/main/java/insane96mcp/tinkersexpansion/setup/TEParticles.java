package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TEParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TinkersExpansion.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ELECTROCUTION_SPARKS = PARTICLE_TYPES.register("electrocution_sparks", () -> new SimpleParticleType(true));
}
