package domain;

import java.time.Duration;

// 아이디와 점수만을 담고 있는 랭킹 보드만을 위한 플레이어 정보 클래스
public class RankMember {
    // 아이디
    private final String id;
    // 점수
    private final Duration score;

    public RankMember(String id, Duration score) {
        this.id = id;
        this.score = score;
    }

    // RankMember 객체를 String으로 변환
    public static RankMember toRankMember(String string) {
        String[] split = string.split(",");
        return new RankMember(split[0], Duration.parse(split[1]));
    }

    // String을 RankMember 객체로 변환
    public static String toString(RankMember rankMember) {
        return rankMember.id + "," + rankMember.score;
    }

    public String getId() {
        return id;
    }

    public Duration getScore() {
        return score;
    }
}
