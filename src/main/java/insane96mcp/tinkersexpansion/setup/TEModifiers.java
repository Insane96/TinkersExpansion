package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.modifiers.D20Modifier;
import insane96mcp.tinkersexpansion.modifiers.ElectrocutionModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.dynamic.StatBoostModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TEModifiers extends AbstractModifierProvider {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersExpansion.MOD_ID);

    public static final StaticModifier<Modifier> D20 = MODIFIERS.register("d20", D20Modifier::new);
    public static final StaticModifier<Modifier> ELECTROCUTION = MODIFIERS.register("electrocution", ElectrocutionModifier::new);

    public TEModifiers(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addModifiers() {
        if (ModList.get().isLoaded("combatroll")) {
            addModifier(TEModifierIds.LONGFOOTED, StatBoostModifier.builder().attribute("tinkercombatroll.longfooted", ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("combatroll:distance")), AttributeModifier.Operation.ADDITION, 1f, EquipmentSlot.FEET).build());
            addModifier(TEModifierIds.ACROBAT, StatBoostModifier.builder().attribute("tinkercombatroll.acrobat", ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("combatroll:recharge")), AttributeModifier.Operation.MULTIPLY_BASE, 0.2f, EquipmentSlot.LEGS).build());
            addModifier(TEModifierIds.MULTI_ROLL, StatBoostModifier.builder().attribute("tinkercombatroll.multi_roll", ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("combatroll:count")), AttributeModifier.Operation.ADDITION, 1f, EquipmentSlot.HEAD).build());
        }
    }

    @Override
    public String getName() {
        return "Tinkers Expansion Modifiers";
    }

    public static void init(IEventBus bus) {
        MODIFIERS.register(bus);
    }
}
