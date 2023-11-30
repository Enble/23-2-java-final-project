package domain;

public class Member {
    private final String id;
    private final String password;

    private int maxScore = 0;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
