package domain;

import enums.DifficultyType;
import java.time.Duration;

// 플레이어의 정보를 담고 있는 클래스
public class Member {
    // 아이디
    private final String id;
    // 비밀번호
    private final String password;
    // 난이도
    private DifficultyType difficultyType;
    // 점수
    private Duration score = Duration.ZERO;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // Member 객체를 String으로 변환
    public static String toString(Member member) {
        return member.id + "," + member.password;
    }

    // String을 Member 객체로 변환
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
