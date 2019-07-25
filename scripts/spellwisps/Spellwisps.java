package scripts.spellwisps;

import framework.taskscript.rt6.TaskScript;
import org.powerbot.script.PaintListener;
import org.powerbot.script.Script;

import scripts.spellwisps.tasks.*;
import scripts.spellwisps.tasks.breakhandler.*;
import utils.Stopwatch;

import java.awt.*;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import static scripts.spellwisps.constants.Constants.SPELLWISP_POSITION;

@Script.Manifest(
        name = "Wuggy Spellwisps",
        description = "Kills Spellwisps and loots Impious ashes for profit.",
        properties = "client=6;topic=1352287;"
)

/*
    TODO:
        - Compatibility with breaks
        - Inventory listener to count impious ashes
 */

public class Spellwisps extends TaskScript implements PaintListener {
    private static Stopwatch sw;
    public static BreakManager bm;

    @Override
    public void onStart() {
        ctx.properties.setProperty("login.disable", "true");

        sw = new Stopwatch();
        bm = new BreakManager();

        add(
                new CloseNTXMessage(ctx), new Login(ctx), new ClickPlay(ctx),
                new SetBreakTime(ctx), new Logout(ctx), new IdleDuringBreak(ctx),

                new WalkToDeposit(ctx), new WalkToSpellwisps(ctx), new DepositAshes(ctx),
                new LootAshes(ctx), new AttackSpellwisp(ctx)
        );
    }

    @Override
    public void repaint(Graphics g) {
        int x = 20, y = 35;

        long currentTime = System.currentTimeMillis();

        if (ctx.lobby.opened() && ctx.properties.getProperty("login.disable").equals("false"))
            ctx.properties.setProperty("login.disable", "true");

        g.drawString("Runtime: " + sw.getScriptRuntime(), x, y+=15);

        if (bm.isWaitingForBreak()) {
            g.drawString("[Break Manager] Next break in " + bm.timeUntilBreakStart(), x, y+=15);

        } else if (bm.inBreak()) {
            g.drawString("[Break Manager] Currently in break, ending in " + bm.timeUntilBreakEnd(), x, y += 15);

        } else {
            g.drawString("[Break Manager] Idle", x, y+=15);
        }
    }
}
