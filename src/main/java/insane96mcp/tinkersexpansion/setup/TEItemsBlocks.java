package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.registration.object.MetalItemObject;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import java.util.function.Function;

public class TEItemsBlocks {
    protected static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(TinkersExpansion.MOD_ID);
    protected static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TinkersExpansion.MOD_ID);

    protected static final Item.Properties GENERAL_PROPS = new Item.Properties().tab(TinkerModule.TAB_GENERAL);

    protected static final Function<Block,? extends BlockItem> GENERAL_TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, GENERAL_PROPS);


    //Slimedice
    public static final MetalItemObject SLIMEDICE = BLOCKS.registerMetal("slimedice", metalBuilder(MaterialColor.TERRACOTTA_GREEN), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);

    public static final FluidObject<ForgeFlowingFluid> MOLTEN_SLIMEDICE = FLUIDS.register("molten_slimedice", hotBuilder().temperature(1200), Material.LAVA, 8);


    /** Creates a builder for a hot fluid */
    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    /** Same as above, but with a color */
    protected static BlockBehaviour.Properties builder(Material material, MaterialColor color, SoundType soundType) {
        return Block.Properties.of(material, color).sound(soundType);
    }

    /** Builder that pre-supplies metal properties */
    protected static BlockBehaviour.Properties metalBuilder(MaterialColor color) {
        return builder(Material.METAL, color, SoundType.METAL).requiresCorrectToolForDrops().strength(5.0f);
    }

    private static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(TinkersExpansion.MOD_ID, name));
    }

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        FLUIDS.register(bus);
    }
}
