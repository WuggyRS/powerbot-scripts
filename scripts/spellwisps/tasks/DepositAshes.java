package scripts.spellwisps.tasks;

import framework.taskscript.rt6.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.DepositBox;
import org.powerbot.script.rt6.Npc;

import static scripts.spellwisps.constants.Constants.*;

public class DepositAshes extends Task<ClientContext> {
    public DepositAshes(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.backpack.select().count() == 28
                && MYRTLE_POSITION.distanceTo(ctx.players.local().tile()) <= 5;
    }

    @Override
    public void execute() {
        if (!ctx.chat.canContinue()) {
            ctx.npcs.select().id(MYRTLE_ID);
            Npc myrtle = ctx.npcs.select().id(MYRTLE_ID).poll();

            ctx.camera.turnTo(myrtle);
            Condition.sleep(200);
            ctx.camera.pitch(Random.nextInt(30,80));

            myrtle.interact("Deposit");
            Condition.wait(() -> ctx.chat.canContinue(), 500);

            return;
        }

        if (ctx.chat.canContinue()) {
            ctx.chat.clickContinue();
            Condition.wait(() -> !ctx.chat.canContinue(), 200, 10);
        }

        Condition.wait(() -> ctx.depositBox.open(), 500);

//        ctx.depositBox.deposit(IMPIOUS_ASHES, DepositBox.Amount.ALL);
//        ctx.depositBox.depositInventory();
        ctx.widgets.component(11, 16).interact("Deposit Carried Items");

        //ctx.depositBox.close();
        ctx.widgets.component(11, 13).interact("Close");
    }
}