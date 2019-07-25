package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import scripts.spellwisps.Spellwisps;


public class Logout extends Task<ClientContext> {
    public Logout(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        long currentTime = System.currentTimeMillis();

        return Spellwisps.bm.inBreak()
                && !ctx.lobby.opened();
    }

    @Override
    public void execute() {
        ctx.game.logout(true);
        Condition.sleep(Random.nextInt(1500, 3000));
    }
}