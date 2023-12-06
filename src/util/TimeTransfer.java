package util;

import java.time.Duration;

public class TimeTransfer {
    private TimeTransfer() {
    }

    public static String durationToStringFormat(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long secs = duration.getSeconds() % 60;
        long millis = duration.toMillis() % 1000;

        if (hours == 0) {
            return String.format("%02d : %02d : %03d", minutes, secs, millis);
        } else {
            return String.format("%02d : %02d : %02d : %03d", hours, minutes, secs, millis);
        }
    }
}
