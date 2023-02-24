package insane96mcp.tinkersexpansion.client;

import insane96mcp.tinkersexpansion.setup.TEMaterials;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

public class TEMaterialTextures extends AbstractMaterialSpriteProvider {
    @Override
    public String getName() {
        return "Tinkers' Expansion Material Textures";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(TEMaterials.SLIMEDICE)
                .meleeHarvest()
                .fallbacks("rock")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF1E7F38).addARGB(102, 0xFF357F53).addARGB(140, 0xFF499367).addARGB(178, 0xFF5DA77B).addARGB(216, 0xFF77C195).addARGB(255, 0xFF8AD4A8).build());
    }
}
