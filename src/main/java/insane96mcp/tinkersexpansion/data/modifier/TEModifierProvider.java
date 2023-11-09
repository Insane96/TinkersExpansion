package insane96mcp.tinkersexpansion.data.modifier;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.dynamic.StatBoostModifier;

public class TEModifierProvider extends AbstractModifierProvider {


    public TEModifierProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addModifiers() {
        if (ModList.get().isLoaded("combatroll")) {
            addModifier(TEModifierIds.LONGFOOTED, StatBoostModifier.builder().attribute("tinkersexpansion.longfooted", ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("combatroll:distance")), AttributeModifier.Operation.ADDITION, 1f, EquipmentSlot.FEET).build());
            addModifier(TEModifierIds.ACROBAT, StatBoostModifier.builder().attribute("tinkersexpansion.acrobat", ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("combatroll:recharge")), AttributeModifier.Operation.MULTIPLY_BASE, 0.2f, EquipmentSlot.LEGS).build());
            addModifier(TEModifierIds.MULTI_ROLL, StatBoostModifier.builder().attribute("tinkersexpansion.multi_roll", ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("combatroll:count")), AttributeModifier.Operation.ADDITION, 1f, EquipmentSlot.HEAD).build());
        }
    }

    @Override
    public String getName() {
        return "Tinkers Expansion Modifiers";
    }
}
