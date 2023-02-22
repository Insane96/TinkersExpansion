package insane96mcp.tinkersexpansion.client;

import insane96mcp.tinkersexpansion.setup.TEMaterials;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class TERenderInfo extends AbstractMaterialRenderInfoProvider {
    public TERenderInfo(DataGenerator gen, @Nullable AbstractMaterialSpriteProvider materialSprites) {
        super(gen, materialSprites);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(TEMaterials.SLIMEDIE).color(0x8ad4a8);
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Render Info Provider";
    }
}
