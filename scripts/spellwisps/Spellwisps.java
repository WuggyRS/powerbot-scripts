package scripts.spellwisps;

import framework.taskscript.rt6.TaskScript;
import org.powerbot.script.PaintListener;
import org.powerbot.script.Script;

import scripts.spellwisps.tasks.*;
import utils.Stopwatch;

import java.awt.*;
import java.text.NumberFormat;

import static scripts.spellwisps.constants.Constants.SPELLWISP_POSITION;

@Script.Manifest(
        name = "Wuggy Spellwisps",
        description = "Kills Spellwisps and loots Impious ashes for profit.",
        properties = "client=6;topic=1352287;"
)

public class Spellwisps extends TaskScript implements PaintListener {
    private static Stopwatch sw;

    @Override
    public void onStart() {
        sw = new Stopwatch();

        add(
                new WalkToDeposit(ctx), new WalkToSpellwisps(ctx), new DepositAshes(ctx),
                new LootAshes(ctx), new AttackSpellwisp(ctx)
        );
    }

    @Override
    public void repaint(Graphics g) {
        int x = 20, y = 35;

        g.drawString("Runtime: " + sw.getScriptRuntime(), x, y+=15);
    }
}
