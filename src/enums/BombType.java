package enums;

public enum BombType {
    SMALL(1, 1, 20, 40),
    MEDIUM(2, 2, 30, 60),
    LARGE(3, 3, 40, 80),
    NUKE(5, 1000, 60, 120);

    private final int speed;
    private final int damage;
    private final int width;
    private final int height;

    BombType(int speed, int damage, int width, int height) {
        this.speed = speed;
        this.damage = damage;
        this.width = width;
        this.height = height;
    }

    private static BombType bombTypeFromDifficulty(DifficultyType difficulty, int random) {
        int smallProbability;
        int mediumProbability;
        int largeProbability;

        switch (difficulty) {
            case EASY:
                smallProbability = 50;
                mediumProbability = 80;
                largeProbability = 100;
                break;
            case NORMAL:
                smallProbability = 45;
                mediumProbability = 75;
                largeProbability = 95;
                break;
            case HARD:
                smallProbability = 40;
                mediumProbability = 70;
                largeProbability = 90;
                break;
            default:
                smallProbability = 30;
                mediumProbability = 60;
                largeProbability = 80;
                break;
        }

        if (random < smallProbability) {
            return SMALL;
        } else if (random < mediumProbability) {
            return MEDIUM;
        } else if (random < largeProbability) {
            return LARGE;
        } else {
            return NUKE;
        }
    }

    public static BombType generateRandomBombType(DifficultyType difficultyType) {
        int random = (int) (Math.random() * 100);
        return bombTypeFromDifficulty(difficultyType, random);
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
