package domain;

import java.time.Duration;

public class RankMember {
    private final String id;
    private final Duration score;

    public RankMember(String id, Duration score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public Duration getScore() {
        return score;
    }
}
