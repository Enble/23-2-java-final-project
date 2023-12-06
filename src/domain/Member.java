package domain;

import java.time.Duration;

public class Member {
    private final String id;
    private final String password;
    private Duration maxScore = Duration.ZERO;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void compareAndSetMaxScore(Duration score) {
        if (score.compareTo(maxScore) > 0) {
            maxScore = score;
        }
    }

    public void setMaxScore(Duration maxScore) {
        this.maxScore = maxScore;
    }

    public Duration getMaxScore() {
        return maxScore;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
