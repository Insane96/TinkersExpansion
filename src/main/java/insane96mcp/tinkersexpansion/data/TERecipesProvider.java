package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import insane96mcp.tinkersexpansion.setup.TEMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;

import java.util.function.Consumer;

public class TERecipesProvider extends RecipeProvider implements IConditionBuilder, IMaterialRecipeHelper, IToolRecipeHelper, ISmelteryRecipeHelper, IRecipeHelper {
    public TERecipesProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    public String getModId() {
        return TinkersExpansion.MOD_ID;
    }


    String castingFolder = "smeltery/casting/metal/";
    String meltingFolder = "smeltery/melting/metal/";
    String alloyFolder = "smeltery/alloys/";
    String materialFolder = "tools/materials/";
    String compositeFolder = "tools/parts/composite/";
    String modifierFolder = "tools/modifiers/";
    String salvageFolder = "tools/modifiers/salvage/";
    String slotlessFolder = "tools/modifiers/slotless/";
    String spillFolder = "tools/spilling/";
    String toolFolder = "tools/building/";
    String partFolder = "tools/parts/";
    String castFolder = "smeltery/casts/";
    String armorFolder = "armor/building/";
    String armorRepairFolder = "armor/repair/";

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //Slimedice
        blockIngotNuggetCompression(consumer, TEMaterials.SLIMEDICE.getId().getPath(), Item.byBlock(TEItemsBlocks.SLIMEDICE.get()), TEItemsBlocks.SLIMEDICE.getIngot(), TEItemsBlocks.SLIMEDICE.getNugget());

        metalCasting(consumer, TEItemsBlocks.MOLTEN_SLIMEDICE, TEItemsBlocks.SLIMEDICE.get(), TEItemsBlocks.SLIMEDICE.getIngot(), TEItemsBlocks.SLIMEDICE.getNugget(), castingFolder, "slimedice");
        metalMelting(consumer, TEItemsBlocks.MOLTEN_SLIMEDICE.get(), "slimedice", false, meltingFolder, false);

        AlloyRecipeBuilder.alloy(TEItemsBlocks.MOLTEN_SLIMEDICE.get(), FluidValues.INGOT)
                .addInput(TinkerFluids.moltenGold.get(), FluidValues.INGOT * 3)
                .addInput(TinkerFluids.moltenObsidian.get(), FluidValues.GLASS_BLOCK)
                .addInput(TinkerFluids.liquidSoul.get(), FluidValues.GLASS_BLOCK * 2)
                .addInput(TinkerFluids.earthSlime.get(), FluidValues.SLIMEBALL * 3)
                .save(consumer, prefix(TEItemsBlocks.MOLTEN_SLIMEDICE, alloyFolder));

        metalMaterialRecipe(consumer, TEMaterials.SLIMEDICE, materialFolder, "slimedice", false);
        materialMeltingCasting(consumer, TEMaterials.SLIMEDICE, TEItemsBlocks.MOLTEN_SLIMEDICE, materialFolder);

        //Coated Copper
        blockIngotNuggetCompression(consumer, TEMaterials.COATED_COPPER.getId().getPath(), Item.byBlock(TEItemsBlocks.COATED_COPPER.get()), TEItemsBlocks.COATED_COPPER.getIngot(), TEItemsBlocks.COATED_COPPER.getNugget());


    }

    public void blockIngotNuggetCompression(Consumer<FinishedRecipe> consumer, String name, Item block, Item ingot, Item nugget) {
        ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
                        ShapedRecipeBuilder.shaped(block, 1)
                                .pattern("XXX")
                                .pattern("XXX")
                                .pattern("XXX")
                                .define('X', ingot)
                                .group("")
                                .unlockedBy("has_ingot", has(ingot))
                                ::save
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(this.getModId(), ingot.getRegistryName().getPath() + "_to_block"));

        ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
                        ShapelessRecipeBuilder.shapeless(ingot, 9)
                                .requires(block)
                                .group("")
                                .unlockedBy("has_block", has(block))
                                ::save
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(this.getModId(), block.getRegistryName().getPath() + "_to_ingot"));

        ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
                        ShapedRecipeBuilder.shaped(ingot, 1)
                                .pattern("XXX")
                                .pattern("XXX")
                                .pattern("XXX")
                                .define('X', nugget)
                                .group("")
                                .unlockedBy("has_nugget", has(nugget))
                                ::save
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(this.getModId(), nugget.getRegistryName().getPath() + "_to_ingot"));

        ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
                        ShapelessRecipeBuilder.shapeless(nugget, 9)
                                .requires(ingot)
                                .group("")
                                .unlockedBy("has_ingot", has(ingot))
                                ::save
                )
                .generateAdvancement()
                .build(consumer, new ResourceLocation(this.getModId(), ingot.getRegistryName().getPath() + "_to_nugget"));
    }
}
