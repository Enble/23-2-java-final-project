package enums;

// 난이도 종류 열거형
public enum DifficultyType {
    // 쉬움
    EASY(7, 50),
    // 보통
    NORMAL(5, 42),
    // 어려움
    HARD(3, 34),
    // 불바다
    FIRE_OCEAN(1, 22);

    // 초기 체력
    private final int initLife;
    // 속도 계수
    private final int speedCoefficient;

    DifficultyType(int initLife, int speedCoefficient) {
        this.initLife = initLife;
        this.speedCoefficient = speedCoefficient;
    }

    public int getInitLife() {
        return initLife;
    }

    public int getSpeedCoefficient() {
        return speedCoefficient;
    }

}
