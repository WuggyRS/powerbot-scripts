package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;
import scripts.spellwisps.Spellwisps;


public class IdleDuringBreak extends Task<ClientContext> {
    public IdleDuringBreak(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return Spellwisps.bm.inBreak()
                || (Spellwisps.bm.inBreak() && ctx.players.local().inCombat());
    }

    @Override
    public void execute() {
        // Do nothing
    }
}