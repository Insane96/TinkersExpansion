package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.registration.object.FluidObject;

public class TEFluidTagProvider extends FluidTagsProvider {
    public TEFluidTagProvider(DataGenerator p_126523_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126523_, TinkersExpansion.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tagLocal(TEItemsBlocks.MOLTEN_SLIMEDICE);
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Fluid TinkerTags";
    }

    /** Tags this fluid using local tags */
    private void tagLocal(FluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
    }

    /** Tags this fluid with local and forge tags */
    private void tagAll(FluidObject<?> fluid) {
        tagLocal(fluid);
        tag(fluid.getForgeTag()).addTag(fluid.getLocalTag());
    }
}
