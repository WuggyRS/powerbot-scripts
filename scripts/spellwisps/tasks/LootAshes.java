package scripts.spellwisps.tasks;

import framework.taskscript.rt6.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.GroundItemQuery;

import static scripts.spellwisps.constants.Constants.IMPIOUS_ASHES;

public class LootAshes extends Task<ClientContext> {
    private static GroundItemQuery<GroundItem> lootQuery;

    public LootAshes(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return !(lootQuery = ctx.groundItems.select().name("Impious ashes").nearest()).isEmpty();
    }

    @Override
    public void execute() {
        GroundItem loot = lootQuery.poll();

        if (!loot.valid()) return;

        if (ctx.players.local().tile().distanceTo(loot.tile()) > 4 && !ctx.players.local().inMotion() && !loot.inViewport()) {
            ctx.camera.turnTo(loot);
            ctx.movement.step(loot.tile());
            return;
        }

        loot.interact("Take", "Impious ashes");
        Condition.sleep(Random.nextInt(250, 400));
    }
}