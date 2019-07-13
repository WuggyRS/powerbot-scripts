package scripts.claysoftener.tasks;

import framework.taskscript.rt4.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.claysoftener.constants.Location;

import java.util.regex.Pattern;

public class Banking extends Task<ClientContext> {
    public Banking(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.inventory.select().name("Clay").count() == 0
                && Location.BANK_AREA.getLocation().contains(ctx.players.local().tile())
                && !ctx.players.local().inMotion();
    }

    // "Enter amount" prompt
    private boolean isInputWidgetOpen() {
        return ctx.widgets.component(162, 44).visible();
    }

    private void withdraw(String itemName, int amount) {
        ctx.bank.select().name(itemName).poll().interact("Withdraw-X");
        Condition.wait(() -> isInputWidgetOpen(), Random.nextInt(700, 900));

        if (isInputWidgetOpen()) {
            ctx.input.sendln(amount+"");
            Condition.sleep(Random.nextInt(500, 700));
        }
    }

    @Override
    public void execute() {
        // Open bank
        if (!ctx.bank.opened()) {
            GameObject bankBooth = ctx.objects.select().nearest().name("Bank booth").poll();

            if (!bankBooth.inViewport())
                ctx.camera.turnTo(bankBooth);

            bankBooth.interact("Bank");
            Condition.wait(() -> ctx.bank.opened(), 500);
            return;
        }

        // If no buckets are found in the bank, stop the script
        if (ctx.bank.select().name("Bucket").count() == 0) {
            ctx.controller.script().log.info("No buckets found, exiting");
            ctx.controller.stop();
        }

        // If bucket is not found in inventory, deposit everything & withdraw buckets
        if (ctx.inventory.select().name("Bucket").isEmpty()) {
            ctx.bank.depositInventory();
            Condition.sleep(Random.nextInt(800, 1000));

            withdraw("Bucket", 14);
        }

        // Deposit all soft clay
        if (!ctx.inventory.select().name("Soft clay").isEmpty()) {
            ctx.bank.deposit("Soft clay", Bank.Amount.ALL);
            Condition.sleep(Random.nextInt(800, 1000));
        }

        // If there are no clay in the bank, stop the script
        if (ctx.bank.select().name("Clay").count() == 0) {
            ctx.controller.script().log.info("No clay found, exiting");
            ctx.controller.stop();
        }

        // Withdraw clay
        if (ctx.inventory.select().name("Clay").isEmpty())
            withdraw("Clay", 14);

        // Close bank
        ctx.bank.close();
    }
}