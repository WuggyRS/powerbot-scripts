package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import scripts.spellwisps.Spellwisps;

import java.util.concurrent.TimeUnit;

public class ClickPlay extends Task<ClientContext> {
    public ClickPlay(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        long currentTime = System.currentTimeMillis();

        return (ctx.lobby.opened() || ctx.widgets.component(906, 135).visible())
                && Spellwisps.bm.isNotInBreak();
    }

    @Override
    public void execute() {
        ctx.widgets.component(906, 135).interact("Play");

        Condition.sleep(Random.nextInt(1500, 2500));
    }
}