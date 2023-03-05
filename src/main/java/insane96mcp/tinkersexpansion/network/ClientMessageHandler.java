package insane96mcp.tinkersexpansion.network;

import insane96mcp.tinkersexpansion.setup.TEParticles;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.TickTask;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;

import java.util.Random;

public class ClientMessageHandler {
    private static final Random RANDOM = new Random();

    public static void summonSpark(IntList ids) {
        if (ids.isEmpty())
            return;

        BlockableEventLoop<? super TickTask> executor = LogicalSidedProvider.WORKQUEUE.get(LogicalSide.CLIENT);
        executor.tell(new TickTask(0, () -> {
            ClientLevel level = Minecraft.getInstance().level;
            for (int i = 1; i < ids.size(); i++){
                //noinspection ConstantConditions
                summonSparkFromTo(level, ids.getInt(i - 1), ids.getInt(i));
            }
        }));
    }

    public static void summonSparkFromTo(ClientLevel level, int idStart, int idEnd) {
        Entity entityStart = level.getEntity(idStart);
        Entity entityEnd = level.getEntity(idEnd);
        if (entityStart == null || entityEnd == null)
            return;
        Vec3 dir = entityEnd.position().subtract(entityStart.position()).normalize();
        for (int i = 0; i < 1000; i++) {
            double x = entityStart.getX() + (dir.x * i * 0.15d);
            double y = entityStart.getY() + (dir.y * i * 0.15d);
            double z = entityStart.getZ() + (dir.z * i * 0.15d);
            Vec3 pos = new Vec3(x, y, z);
            if (pos.distanceToSqr(entityEnd.position()) <= 0.04d)
                break;
            level.addParticle(TEParticles.ELECTROCUTION_SPARKS.get(),
                    x + (RANDOM.nextDouble() * 0.1d - 0.05d),
                    y + (entityStart.getBbHeight() / 2f) + (RANDOM.nextDouble() * 0.1d - 0.05d),
                    z + (RANDOM.nextDouble() * 0.1d - 0.05d),
                    0d, 0d, 0d);
        }
    }

}
