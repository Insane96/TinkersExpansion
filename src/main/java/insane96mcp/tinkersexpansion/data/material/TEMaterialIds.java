package insane96mcp.tinkersexpansion.data.material;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class TEMaterialIds {

    public static final MaterialId SLIMEDICE = createMaterial("slimedice");
    public static final MaterialId COATED_COPPER = createMaterial("coated_copper");

    private static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(TinkersExpansion.MOD_ID, name));
    }

}
