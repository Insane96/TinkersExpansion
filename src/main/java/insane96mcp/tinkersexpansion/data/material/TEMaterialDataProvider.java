package insane96mcp.tinkersexpansion.data.material;

import insane96mcp.tinkersexpansion.setup.TEModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Tiers;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.tools.stats.*;

public class TEMaterialDataProvider extends AbstractMaterialDataProvider {

    public TEMaterialDataProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addMaterials() {
        addMaterial(TEMaterialIds.SLIMEDICE, 3, ORDER_WEAPON, true);
        addMaterial(TEMaterialIds.COATED_COPPER, 2, ORDER_WEAPON, true);
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
            addDefaultTraits(TEMaterialIds.SLIMEDICE, TEModifiers.D20);
            addDefaultTraits(TEMaterialIds.COATED_COPPER, TEModifiers.ELECTROCUTION);
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
            addMaterialStats(TEMaterialIds.SLIMEDICE,
                    new HeadMaterialStats(250, 4f, Tiers.DIAMOND, 2.5f),
                    new HandleMaterialStats(0.85f, 1.0f, 0.95f, 1.15f),
                    ExtraMaterialStats.DEFAULT,
                    new LimbMaterialStats(250, 0.15f, -0.1f, 0.15f),
                    new GripMaterialStats(0.85f, -0.1f, 3.5f));

            addMaterialStats(TEMaterialIds.COATED_COPPER,
                    new HeadMaterialStats(204, 4f, Tiers.IRON, 2.75f),
                    new HandleMaterialStats(0.85f, 1.05f, 0.9f, 1.15f),
                    ExtraMaterialStats.DEFAULT);
        }
    }
}
