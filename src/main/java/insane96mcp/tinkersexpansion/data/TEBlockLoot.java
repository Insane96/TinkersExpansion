package insane96mcp.tinkersexpansion.data;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import insane96mcp.tinkersexpansion.setup.TEItemsBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.stream.Collectors;

public class TEBlockLoot extends BlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter((block) -> TinkersExpansion.MOD_ID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                .collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        this.dropSelf(TEItemsBlocks.SLIMEDICE.get());
    }
}
