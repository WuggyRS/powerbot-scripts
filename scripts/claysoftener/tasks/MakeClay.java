package scripts.claysoftener.tasks;

import framework.taskscript.rt4.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;

public class MakeClay extends Task<ClientContext> {
    private static long lastClayTime = 0;
    private static int previousSoftClayAmount = -1;

    public MakeClay(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        ItemQuery<Item> softClayQuery = ctx.inventory.select().name("Soft clay");

        int numSoftClay = softClayQuery.count();

        if (numSoftClay > previousSoftClayAmount) {
            lastClayTime = System.currentTimeMillis();
            previousSoftClayAmount = numSoftClay;
        }

        // Check if last soft clay creation time is under 3s
        if (System.currentTimeMillis() - lastClayTime < 3000)
            return false;

        return ctx.inventory.select().name("Bucket of water").count() == 14
                && ctx.inventory.select().name("Clay").count() > 0
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        // Use bucket of water on clay
        ctx.inventory.select().name("Bucket of water").first().poll().interact("Use");
        Condition.sleep(Random.nextInt(500, 700));

        ctx.inventory.select().name("Clay").first().poll().interact("Use");
        Condition.sleep(Random.nextInt(500, 700));

        Component productionInterface = ctx.widgets.component(270, 13);

        if (productionInterface.visible()) {
            productionInterface.interact("Make");
            Condition.sleep(Random.nextInt(500, 700));
        }

        Condition.sleep(Random.nextInt(1000, 1200));

        lastClayTime = System.currentTimeMillis();
    }
}