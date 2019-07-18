package utils;

import java.text.NumberFormat;

public class Stopwatch {
    private static long startTime;

    public Stopwatch() {
        startTime = System.currentTimeMillis();
    }

    public String getScriptRuntime() {
        long runtime = System.currentTimeMillis() - startTime;

        int s = (int)Math.floor(runtime/1000.0 % 60);
        int m = (int)Math.floor(runtime/60000.0 % 60);
        int h = (int)Math.floor(runtime/3600000.0);

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private String formatNumber(int num) {
        return NumberFormat.getInstance().format(num);
    }

    public String getHourlyRate(int n) {
        return formatNumber((int)(n / ((System.currentTimeMillis() - startTime) / 3600000.0D)));
    }
}
