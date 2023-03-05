package insane96mcp.tinkersexpansion.network;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(TinkersExpansion.MOD_ID, "simple_channel"))
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void init() {
        CHANNEL.registerMessage(1, ElectrocutionParticleMessage.class, ElectrocutionParticleMessage::encode, ElectrocutionParticleMessage::decode, ElectrocutionParticleMessage::handle);
    }
}
