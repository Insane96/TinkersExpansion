package insane96mcp.tinkersexpansion.client;

import insane96mcp.tinkersexpansion.data.material.TEMaterialIds;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class TEMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public TEMaterialRenderInfoProvider(DataGenerator gen, @Nullable AbstractMaterialSpriteProvider materialSprites) {
        super(gen, materialSprites);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(TEMaterialIds.SLIMEDICE).color(0x8ad4a8);
        buildRenderInfo(TEMaterialIds.COATED_COPPER).color(0x2B182A);
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Render Info Provider";
    }
}
