package enums;

public enum DifficultyType {
    EASY(7, 1.0),
    NORMAL(5, 1.5),
    HARD(3, 2.0),
    FIRE_OCEAN(1, 3.0);

    private final int initLife;
    private final double speedCoefficient;

    DifficultyType(int initLife, double speedCoefficient) {
        this.initLife = initLife;
        this.speedCoefficient = speedCoefficient;
    }

    public int getInitLife() {
        return initLife;
    }

    public double getSpeedCoefficient() {
        return speedCoefficient;
    }

}
