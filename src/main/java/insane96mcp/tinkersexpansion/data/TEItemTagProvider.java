package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.registration.object.MetalItemObject;

public class TEItemTagProvider extends ItemTagsProvider {
    public TEItemTagProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, TinkersExpansion.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        addMetalTags(TEItemsBlocks.SLIMEDICE);
        addMetalTags(TEItemsBlocks.COATED_COPPER);
        this.tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .addTags(TEItemsBlocks.SLIMEDICE.getIngotTag());
    }

    /**
     * Adds relevant tags for a metal object
     * @param metal  Metal object
     */
    private void addMetalTags(MetalItemObject metal) {
        this.tag(metal.getIngotTag()).add(metal.getIngot());
        this.tag(Tags.Items.INGOTS).addTag(metal.getIngotTag());
        this.tag(metal.getNuggetTag()).add(metal.getNugget());
        this.tag(Tags.Items.NUGGETS).addTag(metal.getNuggetTag());
        this.copy(metal.getBlockTag(), metal.getBlockItemTag());
    }
}
