package enums;

public enum ItemType {
    MEDKIT(10, "체력이 1 회복됩니다.\n\n(F1 키를 눌러 아이템 사용)"),
    HEADWIND(30, "모든 폭탄이 잠시동안 떨어지지 않습니다.\n\n(F2 키를 눌러 아이템 사용)"),
    EMP(50, "떨어지는 모든 폭탄을 제거합니다.\n다만 코인은 얻을 수 없습니다.\n\n(F3 키를 눌러 아이템 사용)");

    private final int price;
    private final String description;

    ItemType(int price, String description) {
        this.price = price;
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
