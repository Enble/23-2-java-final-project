package domain;

public enum ItemType {
    MEDKIT(10),
    HEADWIND(30),
    EMP(100);

    private final int price;

    ItemType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
