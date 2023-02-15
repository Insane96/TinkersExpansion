package insane96mcp.tinkersexpansion.modifiers;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

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
        if (level > DIE_ROLLS.length - 1)
            level = DIE_ROLLS.length - 1;
        int die = DIE_ROLLS[level];
        int roll = RANDOM.nextInt(die);
        if (roll != 0 && roll != DIE_ROLLS[level] - 1)
            return generatedLoot;
        int luck = getAmountWithDecimalChance(context.getLuck());

        ItemStack toModify = generatedLoot.get(RANDOM.nextInt(generatedLoot.size()));
        if (roll == 0) {
            TConstruct.LOG.log(Level.INFO, "fail");
            toModify.shrink(1);
        }
        else {
            TConstruct.LOG.log(Level.INFO, "success");
            toModify.grow(1);
        }
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
