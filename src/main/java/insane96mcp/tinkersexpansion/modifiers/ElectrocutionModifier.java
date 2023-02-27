package insane96mcp.tinkersexpansion.modifiers;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;

@Mod.EventBusSubscriber(modid = TinkersExpansion.MOD_ID)
public class ElectrocutionModifier extends Modifier {

    private static final int CHARGES_REQUIRED = 6;

    private static final ResourceLocation CHARGES = new ResourceLocation(TinkersExpansion.MOD_ID, "electrocution_charges");

    public int getCharges(IToolStackView tool) {
        return tool.getPersistentData().getInt(CHARGES);
    }

    public void setCharges(IToolStackView tool, int charges) {
        tool.getPersistentData().putInt(CHARGES, charges);
    }

    public void addCharges(IToolStackView tool, int charges) {
        tool.getPersistentData().putInt(CHARGES, this.getCharges(tool) + charges);
    }

    public void discharge(IToolStackView tool) {
        tool.getPersistentData().putInt(CHARGES, 0);
    }

    public int getChargesRequired(IToolStackView tool) {
        return CHARGES_REQUIRED - tool.getModifierLevel(this);
    }

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        //Don't trigger if attack is not fully charged
        if (context.getCooldown() < 0.9)
            return 0;

        if (this.getCharges(tool) < this.getChargesRequired(tool)) {
            this.addCharges(tool, 1);
            return 0;
        }
        else {
            DamageSource source;
            Player player = context.getPlayerAttacker();
            if (player != null) {
                source = DamageSource.playerAttack(player);
            } else {
                source = DamageSource.mobAttack(context.getAttacker());
            }
            source.bypassArmor();
            double range = 5d + tool.getModifierLevel(this);
            float secondaryDamage = 4f;
            List<LivingEntity> entitiesNearby = context.getAttacker().level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, context.getAttacker(), context.getAttacker().getBoundingBox().inflate(range));
            for (int i = 0; i < entitiesNearby.size() && i < 4; i++) {
                ToolAttackUtil.attackEntitySecondary(source, secondaryDamage, entitiesNearby.get(i), context.getLivingTarget(), true);
                entitiesNearby.get(i).addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 0));
            }
            this.discharge(tool);

            return 1;
        }
    }

    @Override
    public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
        if (tool.getModifierLevel(TinkerModifiers.silky.get()) > 0)
            return generatedLoot;
        int die = 20 + (level - 1);
        int roll = RANDOM.nextInt(die) + 1;
        if (roll > 1 && roll < 19)
            return generatedLoot;

        ItemStack toModify = generatedLoot.get(RANDOM.nextInt(generatedLoot.size()));
        if (roll == 1) {
            toModify.shrink(1);
        }
        else {
            toModify.grow(1);
        }
        return generatedLoot;
    }
}
