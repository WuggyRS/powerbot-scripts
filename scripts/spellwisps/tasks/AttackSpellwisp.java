package scripts.spellwisps.tasks;

import framework.taskscript.rt6.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

public class AttackSpellwisp extends Task<ClientContext> {
    public AttackSpellwisp(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.players.local().animation() == -1
                && !ctx.players.local().inCombat();
    }

    @Override
    public void execute() {
        Npc spellwisp = ctx.npcs.select().name("Spellwisp").nearest().poll();

        if (!spellwisp.valid()) return;

        ctx.camera.turnTo(spellwisp);

        spellwisp.interact("Attack");

        Condition.sleep(Random.nextInt(400, 600));
    }
}