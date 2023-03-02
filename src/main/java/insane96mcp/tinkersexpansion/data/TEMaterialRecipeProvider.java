package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import insane96mcp.tinkersexpansion.setup.TEMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fluids.FluidAttributes;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.function.Consumer;

public class TEMaterialRecipeProvider extends RecipeProvider implements IMaterialRecipeHelper, IConditionBuilder, IRecipeHelper {
    public TEMaterialRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";

        metalMaterialRecipe(consumer, TEMaterials.SLIMEDICE, folder, "slimedice", false);
        materialMeltingCasting(consumer, TEMaterials.SLIMEDICE, TEItemsBlocks.MOLTEN_SLIMEDICE, false, folder);

        materialComposite(consumer, MaterialIds.copper, TEMaterials.COATED_COPPER, TinkerFluids.moltenObsidian, false, FluidAttributes.BUCKET_VOLUME / 4, folder);
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Material Recipe";
    }

    @Override
    public String getModId() {
        return TinkersExpansion.MOD_ID;
    }
}
