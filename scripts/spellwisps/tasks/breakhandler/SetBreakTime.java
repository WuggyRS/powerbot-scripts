package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;
import scripts.spellwisps.Spellwisps;

import java.util.concurrent.TimeUnit;

public class SetBreakTime extends Task<ClientContext> {
    public SetBreakTime(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return Spellwisps.bm.isPastBreakTime() && ctx.game.loggedIn();
    }

    @Override
    public void execute() {
        // Reset the time for next break
        Spellwisps.bm.setNextBreakTime();
    }
}