package business.entities;

import business.utilities.ProductType;

import java.util.Objects;

public class Product {
    private String code;
    private String name;
    private ProductType type;

    private String maker;

    private String manufacturingDate;
    private String expirationDate;

    private int quantity;

    private Product() {

    }

    public Product(
            String code,
            ProductType type,
            String name,
            String maker,
            String manufacturingDate,
            String expirationDate,
            int quantity
    ) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.quantity = quantity;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
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

    public String getExpirationDate() {
        return expirationDate;
    }

}
