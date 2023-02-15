package insane96mcp.buildersconstruct.modifiers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import insane96mcp.buildersconstruct.TEModifiers;
import insane96mcp.buildersconstruct.TinkersExpansion;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.definition.module.ToolModuleHooks;
import slimeknights.tconstruct.library.tools.definition.module.interaction.DualOptionInteraction;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.*;

@Mod.EventBusSubscriber(modid = TinkersExpansion.MOD_ID)
public class D20Modifier extends Modifier {

    private static final int[] DIE_ROLLS = {0, 20, 12, 10, 8, 6, 4};

    private static final ModifierLevelDisplay NAME = new ModifierLevelDisplay.UniqueForLevels(6);

    @Override
    public Component getDisplayName(int level) {
        return NAME.nameForLevel(this, level);
    }

    @Override
    public int getPriority() {
        return 95; //Should run after luck
    }

    @Override
    public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
        int die = DIE_ROLLS[level];
        int roll = RANDOM.nextInt(die);
        if (roll != 0 && roll != DIE_ROLLS[level] - 1)
            return generatedLoot;
        int luck = getAmountWithDecimalChance(context.getLuck());

        ItemStack toModify = generatedLoot.get(RANDOM.nextInt(generatedLoot.size()));
        if (roll == 0)
            toModify.shrink(1);
        else
            toModify.grow(1);
        return generatedLoot;
    }

    /**
     * Given a value, will return the integer part plus a chance given by the decimal part to have a +1 on the return value
     * Example 1.2 would have 20% chance to return 2 and 80% chance to return 1
     */
    public int getAmountWithDecimalChance(double f) {
        double mod = f - (int)f;
        if (mod == 0f)
            return (int) f;
        f -= mod;
        if (RANDOM.nextDouble() < mod)
            f++;
        return (int) f;
    }
}
