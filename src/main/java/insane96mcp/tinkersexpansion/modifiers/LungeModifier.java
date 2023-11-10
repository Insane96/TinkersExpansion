package insane96mcp.tinkersexpansion.modifiers;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.SlimeBounceHandler;

@Mod.EventBusSubscriber(modid = TinkersExpansion.MOD_ID)
public class LungeModifier extends Modifier implements GeneralInteractionModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK) {
            ModifierUtil.startUsingItem(tool, modifier.getId(), player, hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    public float getForce(IToolStackView tool, int timeLeft, ModifierEntry modifierEntry) {
        int i = this.getUseDuration(tool, modifierEntry) - timeLeft;
        float f = i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        f *= 4f;

        if (f > 3f) {
            f = 3f;
        }
        return f;
    }

    @Override
    public boolean onStoppedUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) {
            return false;
        }

        // don't allow free flight when using an elytra, should use fireworks
        if (player.isFallFlying()) {
            return false;
        }

        player.causeFoodExhaustion(0.2F);
        player.setSprinting(true);

        float f = getForce(tool, timeLeft, modifier);
        float speed = f / 3F;
        Vec3 look = player.getLookAngle();
        player.push(
                (look.x * speed),
                (1 + look.y) * speed / 2f,
                (look.z * speed));

        onSuccess(player, tool);
        SlimeBounceHandler.addBounceHandler(player);
        if (!player.level.isClientSide) {
            player.getCooldowns().addCooldown(tool.getItem(), 3);
            onSuccess(player, tool);
        }
        return true;
    }

    /** Plays the success sound and damages the sling */
    protected void onSuccess(Player player, IToolStackView tool) {
        player.getCommandSenderWorld().playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.SLIME_SLING.getSound(), player.getSoundSource(), 1f, 1f);
        ToolDamageUtil.damageAnimated(tool, 3, player, player.getUsedItemHand());
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return 72000;
    }
}
