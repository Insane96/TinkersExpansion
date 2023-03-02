package insane96mcp.tinkersexpansion.modifiers;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TESounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TinkersExpansion.MOD_ID)
public class ElectrocutionModifier extends Modifier {

    private static final int CHARGES_REQUIRED = 4;

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
        //return CHARGES_REQUIRED - tool.getModifierLevel(this);
        return CHARGES_REQUIRED;
    }

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        //Don't trigger if attack is not fully charged
        if (context.getCooldown() < 0.9f)
            return 0;

        if (this.getCharges(tool) < this.getChargesRequired(tool)) {
            this.addCharges(tool, 1);
            return 0;
        }
        else {
            this.discharge(tool);
            DamageSource source = damageSource(context.getAttacker());
            double range = 4.5d;
            float secondaryDamage = getElectricDamage(tool, level);
            int hitEntities = 0;
            List<LivingEntity> listOfHitEntities = new ArrayList<>();
            //Add the player to the list, so it doesn't get targeted
            listOfHitEntities.add(context.getAttacker());
            Entity lastEntityHit = context.getTarget();
            do {
                List<LivingEntity> entitiesOfClass = lastEntityHit.level.getEntitiesOfClass(LivingEntity.class, lastEntityHit.getBoundingBox().inflate(range),
                        livingEntity ->
                                context.getAttacker().canAttack(livingEntity)
                                || (livingEntity instanceof Player && context.getPlayerAttacker() != null && context.getPlayerAttacker().canHarmPlayer((Player) livingEntity)));
                LivingEntity livingEntity = getNearestEntity(entitiesOfClass, listOfHitEntities, context.getTarget().position());
                if (livingEntity == null)
                    return hitEntities;
                listOfHitEntities.add(livingEntity);
                ToolAttackUtil.attackEntitySecondary(source, secondaryDamage, livingEntity, context.getLivingTarget(), true);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 0, true, true));
                livingEntity.playSound(TESounds.ELECTROCUTION.get(), 0.4f, 1.0f);
                lastEntityHit = livingEntity;
                hitEntities++;
            } while (hitEntities < 3 + level);

            return hitEntities;
        }
    }

    public static float getElectricDamage(IToolStackView tool, int level) {
        return (2f * level) * tool.getMultiplier(ToolStats.ATTACK_DAMAGE);
    }

    @Nullable
    public static <T extends LivingEntity> T getNearestEntity(List<? extends T> entitiesNearby, List<? extends T> entitiesToExclude, Vec3 pos) {
        double nearestDistance = -1.0D;
        T r = null;

        for(T entity : entitiesNearby) {
            if (entitiesToExclude.contains(entity))
                continue;
            double distance = entity.distanceToSqr(pos);
            if (nearestDistance == -1.0D || distance < nearestDistance) {
                nearestDistance = distance;
                r = entity;
            }
        }

        return r;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(applyStyle(new TranslatableComponent(getTranslationKey() + ".damage", getElectricDamage(tool, level))));
        tooltip.add(applyStyle(new TranslatableComponent(getTranslationKey() + ".charges", this.getCharges(tool), this.getChargesRequired(tool))));
    }

    private static DamageSource damageSource(LivingEntity attacker) {
        return new EntityDamageSource(TinkersExpansion.MOD_ID + ".electrocution", attacker).bypassArmor();
    }
}
