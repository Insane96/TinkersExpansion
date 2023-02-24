package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.MetalItemObject;

import java.util.function.Supplier;

public class TEBlockTagProvider extends BlockTagsProvider {
    public TEBlockTagProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, TinkersExpansion.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        addMetalTags(TEItemsBlocks.SLIMEDICE, true);

        tagBlocks(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL, TEItemsBlocks.SLIMEDICE);
    }

    /**
     * Adds relevant tags for a metal object
     * @param metal  Metal object
     */
    private void addMetalTags(MetalItemObject metal, boolean beacon) {
        this.tag(metal.getBlockTag()).add(metal.get());
        if (beacon) {
            this.tag(BlockTags.BEACON_BASE_BLOCKS).addTag(metal.getBlockTag());
        }
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(metal.getBlockTag());
    }

    /** Applies a set of tags to a block */
    @SuppressWarnings("SameParameterValue")
    private void tagBlocks(TagKey<Block> tag1, TagKey<Block> tag2, Supplier<? extends Block>... blocks) {
        tagBlocks(tag1, blocks);
        tagBlocks(tag2, blocks);
    }

    /** Applies a tag to a set of suppliers */
    @SafeVarargs
    private void tagBlocks(TagKey<Block> tag, Supplier<? extends Block>... blocks) {
        TagAppender<Block> appender = this.tag(tag);
        for (Supplier<? extends Block> block : blocks) {
            appender.add(block.get());
        }
    }
}
