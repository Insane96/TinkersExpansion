package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.modifiers.D20Modifier;
import insane96mcp.tinkersexpansion.modifiers.ElectrocutionModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TEModifiers {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersExpansion.MOD_ID);

    public static final StaticModifier<Modifier> D20 = MODIFIERS.register("d20", D20Modifier::new);
    public static final StaticModifier<Modifier> ELECTROCUTION = MODIFIERS.register("electrocution", ElectrocutionModifier::new);

    public static void init(IEventBus bus) {
        MODIFIERS.register(bus);
    }
}
