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
