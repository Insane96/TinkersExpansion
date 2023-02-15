package insane96mcp.buildersconstruct;

import insane96mcp.buildersconstruct.modifiers.D20Modifier;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TEModifiers extends AbstractModifierProvider {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersExpansion.MOD_ID);

    public static final StaticModifier<Modifier> CONSTRUCTION = MODIFIERS.register("construction", D20Modifier::new);

    public TEModifiers(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addModifiers() {
    }

    @Override
    public String getName() {
        return "Builders Constructs Modifiers";
    }
}
