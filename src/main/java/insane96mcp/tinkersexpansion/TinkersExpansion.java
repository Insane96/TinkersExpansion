package insane96mcp.tinkersexpansion;

import insane96mcp.tinkersexpansion.client.TEMaterialRenderInfoProvider;
import insane96mcp.tinkersexpansion.client.TEMaterialSpriteProvider;
import insane96mcp.tinkersexpansion.data.*;
import insane96mcp.tinkersexpansion.data.material.TEMaterialDataProvider;
import insane96mcp.tinkersexpansion.data.modifier.TEModifierProvider;
import insane96mcp.tinkersexpansion.network.NetworkHandler;
import insane96mcp.tinkersexpansion.particle.ElectrocutionSparkParticle;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import insane96mcp.tinkersexpansion.setup.TEModifiers;
import insane96mcp.tinkersexpansion.setup.TEParticles;
import insane96mcp.tinkersexpansion.setup.TESounds;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

@Mod(TinkersExpansion.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersExpansion
{
    public static final String MOD_ID = "tinkersexpansion";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    public TinkersExpansion() {
        //ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, Config.COMMON_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        TEModifiers.init(bus);
        TEItemsBlocks.init(bus);
        TEParticles.PARTICLE_TYPES.register(bus);
        TESounds.SOUND_EVENTS.register(bus);
        NetworkHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerParticleFactories);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeClient()) {
            TEMaterialSpriteProvider materialSprites = new TEMaterialSpriteProvider();
            generator.addProvider(new TEMaterialRenderInfoProvider(generator, materialSprites));
            generator.addProvider(new MaterialPartTextureGenerator(generator, existingFileHelper, new TinkerPartSpriteProvider(), materialSprites));
        }
        if (event.includeServer()) {
            generator.addProvider(new TEModifierProvider(generator));
            generator.addProvider(new TERecipesProvider(generator));
            generator.addProvider(new TELootTableProvider(generator));
            generator.addProvider(new TEMaterialTagProvider(generator, existingFileHelper));
            generator.addProvider(new TEFluidTagProvider(generator, existingFileHelper));
            BlockTagsProvider blockTagsProvider = new TEBlockTagProvider(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new TEItemTagProvider(generator, blockTagsProvider, existingFileHelper));
            generator.addProvider(new TESpillingFluidProvider(generator));
            AbstractMaterialDataProvider materials = new TEMaterialDataProvider(generator);
            generator.addProvider(materials);
            generator.addProvider(new TEMaterialDataProvider.TEMaterialStats(generator, materials));
            generator.addProvider(new TEMaterialDataProvider.TEMaterialTraits(generator, materials));
            generator.addProvider(new TEMaterialRecipeProvider(generator));
        }
    }

    public void preInit(FMLCommonSetupEvent event) {
        NetworkHandler.init();
    }

    public void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(TEParticles.ELECTROCUTION_SPARKS.get(), ElectrocutionSparkParticle.Provider::new);
    }
}