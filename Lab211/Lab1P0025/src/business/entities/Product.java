package business.entities;

import business.utilities.ProductType;

public class Product extends Item {
    private String name;
    private ProductType type;

    private String maker;

    private String manufacturingDate;
    private String expirationDate;

    private int quantity;

    public Product(
            String code,
            ProductType type,
            String name,
            String maker,
            String manufacturingDate,
            String expirationDate,
            int quantity
    ) {
        super(code);
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getMaker() {
        return maker;
    }

    public ProductType getType() {
        return type;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s, %s, %s",
                getCode(), type, name, maker, manufacturingDate, expirationDate, quantity
        );
    }
}
