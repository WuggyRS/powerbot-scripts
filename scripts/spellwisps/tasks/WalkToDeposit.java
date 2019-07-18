package scripts.spellwisps.tasks;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;

import static scripts.spellwisps.constants.Constants.MYRTLE_POSITION;

public class WalkToDeposit extends Task<ClientContext> {
    public WalkToDeposit(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.backpack.select().count() == 28
                && MYRTLE_POSITION.distanceTo(ctx.players.local().tile()) > 5;
    }

    @Override
    public void execute() {
        if (ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 5) return;

        ctx.movement.step(MYRTLE_POSITION);
    }
}