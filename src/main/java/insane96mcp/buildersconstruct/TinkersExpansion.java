package insane96mcp.buildersconstruct;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(TinkersExpansion.MOD_ID)
public class TinkersExpansion
{
    public static final String MOD_ID = "tinkersexpansion";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    public TinkersExpansion() {
        //ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, Config.COMMON_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        TEModifiers.MODIFIERS.register(bus);
        //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> MaterialisClient::onConstruct);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new TEModifiers(generator));
        }
    }
}