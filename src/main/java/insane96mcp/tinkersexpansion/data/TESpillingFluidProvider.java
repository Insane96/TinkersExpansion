package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.material.Fluid;
import slimeknights.tconstruct.library.data.tinkering.AbstractSpillingFluidProvider;
import slimeknights.tconstruct.library.modifiers.spilling.effects.EffectSpillingEffect;
import slimeknights.tconstruct.library.recipe.FluidValues;

public class TESpillingFluidProvider extends AbstractSpillingFluidProvider {
    public TESpillingFluidProvider(DataGenerator generator) {
        super(generator, TinkersExpansion.MOD_ID);
    }

    @Override
    protected void addFluids() {
        metalborn(TEItemsBlocks.MOLTEN_SLIMEDICE.getLocalTag(), 3f).addEffect(new EffectSpillingEffect(MobEffects.LUCK, 10, 1)).addEffect(new EffectSpillingEffect(MobEffects.MOVEMENT_SLOWDOWN, 10, 2));
    }

    @Override
    public String getName() {
        return "Tinkers' Expansion Spilling Fluid Provider";
    }

    /** Builder for an effect based metal */
    private Builder metalborn(TagKey<Fluid> tag, float damage) {
        return burningFluid(tag.location().getPath(), tag, FluidValues.NUGGET, damage, 0);
    }
}
