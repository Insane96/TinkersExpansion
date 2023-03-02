package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.tools.stats.*;

public class TEMaterials extends AbstractMaterialDataProvider {

    public static final MaterialId SLIMEDICE = createMaterial("slimedice");
    public static final MaterialId COATED_COPPER = createMaterial("coated_copper");

    public TEMaterials(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addMaterials() {
        addMaterial(SLIMEDICE, 3, ORDER_WEAPON, true);
        addMaterial(COATED_COPPER, 2, ORDER_WEAPON, true);
    }

    private static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(TinkersExpansion.MOD_ID, name));
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Materials";
    }

    public static class TEMaterialTraits extends AbstractMaterialTraitDataProvider {

        public TEMaterialTraits(DataGenerator gen, AbstractMaterialDataProvider materials) {
            super(gen, materials);
        }

        @Override
        public String getName() {
            return "Tinkers' Expansion Material Traits";
        }

        @Override
        protected void addMaterialTraits() {
            addDefaultTraits(SLIMEDICE, TEModifiers.D20);
            addDefaultTraits(COATED_COPPER, TEModifiers.ELECTROCUTION);
        }
    }

    public static class TEMaterialStats extends AbstractMaterialStatsDataProvider {

        public TEMaterialStats(DataGenerator gen, AbstractMaterialDataProvider materials) {
            super(gen, materials);
        }

        @Override
        public String getName() {
            return "Tinkers' Expansion Material Stats";
        }

        @Override
        protected void addMaterialStats() {
            addMaterialStats(SLIMEDICE,
                    new HeadMaterialStats(250, 4f, Tiers.DIAMOND, 2.5f),
                    new HandleMaterialStats(0.85f, 1.0f, 0.95f, 1.15f),
                    ExtraMaterialStats.DEFAULT,
                    new LimbMaterialStats(250, 0.15f, -0.1f, 0.15f),
                    new GripMaterialStats(0.85f, -0.1f, 3.5f));

            addMaterialStats(COATED_COPPER,
                    new HeadMaterialStats(224, 4f, Tiers.IRON, 3f),
                    new HandleMaterialStats(0.8f, 1.05f, 0.9f, 1.2f),
                    ExtraMaterialStats.DEFAULT,
                    new LimbMaterialStats(224, -0.15f, 0.1f, 0.1f),
                    new GripMaterialStats(0.8f, 0.1f, 2.5f));
        }
    }
}
