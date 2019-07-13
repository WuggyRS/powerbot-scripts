package scripts.claysoftener;

import framework.taskscript.rt4.TaskScript;
import framework.treescript.rt4.*;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.Script;

import scripts.claysoftener.tasks.*;

import java.awt.*;
import java.text.NumberFormat;

@Script.Manifest(
        name = "Wuggy Clay Softener",
        description = "Makes soft clay for profit",
        properties = "client=4;"
)

public class ClaySoftener extends TaskScript implements PaintListener, MessageListener {
    private static long startTime;

    private static int clayCount;

    @Override
    public void onStart() {
        startTime = System.currentTimeMillis();
        clayCount = 0;

        add(
                new WalkToFountain(ctx), new FillBuckets(ctx), new MakeClay(ctx),
                new WalkToBank(ctx), new Banking(ctx)
        );
    }

    private String getScriptRuntime() {
        int s = (int)Math.floor(getRuntime()/1000.0 % 60);
        int m = (int)Math.floor(getRuntime()/60000.0 % 60);
        int h = (int)Math.floor(getRuntime()/3600000.0);

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private String formatNumber(int num) {
        return NumberFormat.getInstance().format(num);
    }

    private String getHourlyRate(int n) {
        return formatNumber((int)(n / ((System.currentTimeMillis() - startTime) / 3600000.0D)));
    }

    @Override
    public void repaint(Graphics g) {
        int x = 20, y = 35;

        g.drawString("Runtime: " + getScriptRuntime(), x, y+=15);
        g.drawString("Clay made (per hour): " + clayCount + " ("+getHourlyRate(clayCount)+")", x, y+=15);
    }

    @Override
    public void messaged(MessageEvent e) {
        if (e.text().contains("You now have some soft workable clay"))
            clayCount++;
    }
}
