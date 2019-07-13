package scripts.claysoftener.tasks;

import framework.taskscript.rt4.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.claysoftener.constants.Location;

public class FillBuckets extends Task<ClientContext> {
    private static long lastAnimTime;
    private static final int FILLING_ANIMATION = 832;

    public FillBuckets(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        if (ctx.players.local().animation() == FILLING_ANIMATION)
            lastAnimTime = System.currentTimeMillis();

        // Check if last bucket fill time is under 3s
        if (System.currentTimeMillis() - lastAnimTime < 3000)
            return false;

        return Location.FOUNTAIN_AREA.getLocation().contains(ctx.players.local().tile())
                && ctx.inventory.select().name("Bucket").count() == 14
                && ctx.inventory.select().name("Clay").count() == 14
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject fountain = ctx.objects.select().name("Well").nearest().poll();

        if (fountain == null) return;

        if (!fountain.inViewport()) {
            ctx.camera.turnTo(fountain);
        }

        // Use bucket on well
        ctx.inventory.select().name("Bucket").first().poll().interact("Use");
        fountain.interact("Use");

        Condition.sleep(Random.nextInt(1000, 1200));

        lastAnimTime = System.currentTimeMillis();
    }
}