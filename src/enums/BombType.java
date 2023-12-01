package enums;

public enum BombType {
    SMALL(1, 1, 20, 40),
    MEDIUM(2, 2, 30, 60),
    LARGE(3, 3, 40, 80),
    NUKE(6, 1000, 60, 120);

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

    public static BombType generateRandomBombType(DifficultyType difficultyType) {
        int random = (int) (Math.random() * 100);
        if (difficultyType == DifficultyType.EASY) {
            if (random < 50) {
                return SMALL;
            } else if (random < 80) {
                return MEDIUM;
            } else {
                return LARGE;
            }
        } else if (difficultyType == DifficultyType.NORMAL) {
            if (random < 45) {
                return SMALL;
            } else if (random < 75) {
                return MEDIUM;
            } else if (random < 95) {
                return LARGE;
            } else {
                return NUKE;
            }
        } else if (difficultyType == DifficultyType.HARD) {
            if (random < 40) {
                return SMALL;
            } else if (random < 70) {
                return MEDIUM;
            } else if (random < 90) {
                return LARGE;
            } else {
                return NUKE;
            }
        } else {
            if (random < 30) {
                return SMALL;
            } else if (random < 60) {
                return MEDIUM;
            } else if (random < 80) {
                return LARGE;
            } else {
                return NUKE;
            }
        }
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
