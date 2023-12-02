package enums;

public enum DifficultyType {
    EASY(7, 50),
    NORMAL(5, 42),
    HARD(3, 34),
    FIRE_OCEAN(1, 22);

    private final int initLife;
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
