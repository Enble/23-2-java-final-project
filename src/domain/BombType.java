package domain;

public enum BombType {
    SMALL(1, 1, 1),
    MEDIUM(2, 2, 2),
    LARGE(3, 3, 3),
    NUKE(6, 1000, 5);

    private final int speed;
    private final int damage;
    private final int size;

    BombType(int speed, int damage, int size) {
        this.speed = speed;
        this.damage = damage;
        this.size = size;
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

    public int getSize() {
        return size;
    }

}
