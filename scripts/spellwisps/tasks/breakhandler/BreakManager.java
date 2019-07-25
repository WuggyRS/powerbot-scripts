package scripts.spellwisps.tasks.breakhandler;

import org.powerbot.script.Random;

import java.util.concurrent.TimeUnit;

public class BreakManager {
    private static long nextBreakStart, nextBreakEnd;
    private static int timeUntilNextBreak; // In minutes
    private static int breakDuration; // In minutes

    public BreakManager() {
        timeUntilNextBreak = 60;
        breakDuration = 10;

        setNextBreakTime();
//        nextBreakStart = getCurrentTime() + TimeUnit.MINUTES.toMillis(timeUntilNextBreak);
//        nextBreakEnd = nextBreakStart + TimeUnit.MINUTES.toMillis(breakDuration);
    }

    public void setNextBreakTime() {
        nextBreakStart = getCurrentTime() + TimeUnit.MINUTES.toMillis(Random.nextInt(timeUntilNextBreak - 3, timeUntilNextBreak + 5));
        nextBreakEnd = nextBreakStart + TimeUnit.MINUTES.toMillis(Random.nextInt(breakDuration - 2, breakDuration + 2));
    }

    public boolean isWaitingForBreak() {
        return getCurrentTime() < nextBreakStart;
    }

    public boolean inBreak() {
        long time = getCurrentTime();

        return time >= nextBreakStart && time <= nextBreakEnd;
    }

    public boolean isPastBreakTime() {
        return getCurrentTime() > nextBreakEnd;
    }

    public boolean isNotInBreak() {
        return isWaitingForBreak() || isPastBreakTime();
    }

    public String timeUntilBreakStart() {
        return timeUntil(nextBreakStart, getCurrentTime());
//        return (nextBreakStart - getCurrentTime()) / 1000L;
    }

    public String timeUntilBreakEnd() {
        return timeUntil(nextBreakEnd, getCurrentTime());
//        return (nextBreakEnd - getCurrentTime()) / 1000L;
    }

    private String timeUntil(long time1, long time2) {
        long runtime = time1 - time2;

        int s = (int)Math.floor(runtime/1000.0 % 60);
        int m = (int)Math.floor(runtime/60000.0 % 60);
        int h = (int)Math.floor(runtime/3600000.0);

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
