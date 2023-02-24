package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider;

public class TEMaterialTagProvider extends AbstractMaterialTagProvider {
    public TEMaterialTagProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TinkersExpansion.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TinkerTags.Materials.NETHER).add(
                TEMaterials.SLIMEDICE
        );
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Material Tag Provider";
    }
}
