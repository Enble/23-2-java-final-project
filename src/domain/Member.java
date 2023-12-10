package domain;

import enums.DifficultyType;
import java.time.Duration;

public class Member {
    private final String id;
    private final String password;
    private DifficultyType difficultyType;
    private Duration score = Duration.ZERO;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static String toString(Member member) {
        return member.id + "," + member.password;
    }

    public static Member toMember(String string) {
        String[] split = string.split(",");
        return new Member(split[0], split[1]);
    }

    public void setScore(Duration score) {
        this.score = score;
    }

    public Duration getScore() {
        return score;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(DifficultyType difficultyType) {
        this.difficultyType = difficultyType;
    }
}
