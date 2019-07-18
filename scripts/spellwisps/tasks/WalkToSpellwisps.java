package scripts.spellwisps.tasks;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;

import static scripts.spellwisps.constants.Constants.SPELLWISP_POSITION;

public class WalkToSpellwisps extends Task<ClientContext> {
    public WalkToSpellwisps(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.backpack.select().count() < 28
                && SPELLWISP_POSITION.distanceTo(ctx.players.local()) > 7;
    }

    @Override
    public void execute() {
        if (ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 5) return;

        ctx.movement.step(SPELLWISP_POSITION);
    }
}