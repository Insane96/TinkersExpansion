package insane96mcp.tinkersexpansion;

import insane96mcp.tinkersexpansion.modifiers.D20Modifier;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TEModifiers extends AbstractModifierProvider {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersExpansion.MOD_ID);

    public static final StaticModifier<Modifier> D20 = MODIFIERS.register("d20", D20Modifier::new);

    public TEModifiers(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addModifiers() {
    }

    @Override
    public String getName() {
        return "Tinkers Expansion Modifiers";
    }
}
