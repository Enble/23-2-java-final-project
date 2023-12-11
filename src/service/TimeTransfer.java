package service;

import java.time.Duration;

// Duration을 String으로 변환하는 편의성 클래스
public class TimeTransfer {
    private TimeTransfer() {
    }

    // Duration을 String으로 변환
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
