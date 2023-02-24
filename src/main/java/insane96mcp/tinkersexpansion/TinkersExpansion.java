package insane96mcp.tinkersexpansion;

import insane96mcp.tinkersexpansion.client.TEMaterialTextures;
import insane96mcp.tinkersexpansion.client.TERenderInfo;
import insane96mcp.tinkersexpansion.data.*;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import insane96mcp.tinkersexpansion.setup.TEMaterials;
import insane96mcp.tinkersexpansion.setup.TEModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeClient()) {
            TEMaterialTextures materialSprites = new TEMaterialTextures();
            generator.addProvider(new TERenderInfo(generator, materialSprites));
            generator.addProvider(new MaterialPartTextureGenerator(generator, existingFileHelper, new TinkerPartSpriteProvider(), materialSprites));
        }
        if (event.includeServer()) {
            generator.addProvider(new TEModifiers(generator));
            generator.addProvider(new TERecipesProvider(generator));
            generator.addProvider(new TELootTableProvider(generator));
            generator.addProvider(new TEMaterialTagProvider(generator, existingFileHelper));
            generator.addProvider(new TEFluidTagProvider(generator, existingFileHelper));
            BlockTagsProvider blockTagsProvider = new TEBlockTagProvider(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new TEItemTagProvider(generator, blockTagsProvider, existingFileHelper));
            generator.addProvider(new TESpillingFluidProvider(generator));
            AbstractMaterialDataProvider materials = new TEMaterials(generator);
            generator.addProvider(materials);
            generator.addProvider(new TEMaterials.TEMaterialStats(generator, materials));
            generator.addProvider(new TEMaterials.TEMaterialTraits(generator, materials));
            generator.addProvider(new TEMaterialRecipeProvider(generator));
        }
    }
}