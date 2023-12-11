package enums;

// 폭탄 종류 열거형
public enum BombType {
    // 작은 폭탄
    SMALL(1, 1),
    // 중간 폭탄
    MEDIUM(2, 2),
    // 큰 폭탄
    LARGE(3, 3),
    // 핵폭탄
    NUKE(5, 1000);

    // 폭탄 속도
    private final int speed;
    // 폭탄 데미지
    private final int damage;

    BombType(int speed, int damage) {
        this.speed = speed;
        this.damage = damage;
    }

    // 난이도에 따른 폭탄 종류 반환
    private static BombType bombTypeFromDifficulty(DifficultyType difficulty, int random) {
        int smallProbability;
        int mediumProbability;
        int largeProbability;

        // 난이도에 폭탄 생성 확률 설정
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

        // 랜덤 값에 따른 폭탄 종류 반환
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

    // 난이도에 따른 랜덤 폭탄 종류 반환
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

}
