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
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF0d572b).addARGB(102, 0xFF2f794d).addARGB(140, 0xFF519b6f).addARGB(178, 0xFF84cea2).addARGB(216, 0xFFacf6ca).addARGB(255, 0xFFe5fff0).build());
    }
}
