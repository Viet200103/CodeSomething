package business.utilities;

public enum ProductType {
    DAILY(0), LONG_LIFE(1);

    private int value;

    private ProductType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
