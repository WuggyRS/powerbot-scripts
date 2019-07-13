package scripts.claysoftener.tasks;

import framework.taskscript.rt4.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import scripts.claysoftener.constants.Location;

public class WalkToFountain extends Task<ClientContext> {
    public WalkToFountain(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.inventory.select().name("Clay").count() == 14
                && !Location.FOUNTAIN_AREA.getLocation().contains(ctx.players.local().tile());
    }

    @Override
    public void execute() {
        // Toggle run if it's above 50% energy
        if (!ctx.movement.running() && ctx.movement.energyLevel() > 50) {
            ctx.movement.running(true);
        }

        // Walk to fountain spot
        ctx.movement.step(Location.FOUNTAIN_AREA.getSpot());
        Condition.wait(() -> !ctx.players.local().inMotion(), 750, 900);
    }
}