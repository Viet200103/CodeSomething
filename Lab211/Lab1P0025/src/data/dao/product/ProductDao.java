package data.dao.product;

import business.entities.Product;
import business.utilities.ProductType;
import data.managers.IFileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProductDao implements IProductDao {
    private IFileManager fileManager;

    private final List<Product> productList = new ArrayList<>();

    private ProductDao() {

    }

    public ProductDao(IFileManager fileManager) throws Exception {
        this.fileManager = fileManager;
        loadProductFromFile();
    }

    @Override
    public void loadProductFromFile() throws Exception {
        List<String> dataList = fileManager.readDataFromFile();

        String code;
        ProductType type = null;
        String name;
        String maker;
        String manufacturingDate;
        String expirationDate;

        for (String productRaw: dataList) {
            List<String> raws = Arrays.asList(productRaw.split(","));

            code = raws.get(0).trim();

            switch (Integer.parseInt(raws.get(1).trim())) {
                case 0 -> {
                    type = ProductType.DAILY;
                }
                case 1 -> {
                    type = ProductType.LONG_LIFE;
                }
            }

            name = raws.get(2).trim();
            maker = raws.get(3).trim();
            manufacturingDate = raws.get(4).trim();
            expirationDate = raws.get(5).trim();

            int quantity = Integer.parseInt(
                    raws.get(6).trim()
            );

            Product product = new Product(code, type, name, maker, manufacturingDate, expirationDate, quantity);
            addNewProduct(product);
        }
    }

    @Override
    public void addNewProduct(Product product) {
       boolean isExists = productList.stream().anyMatch(
               p -> Objects.equals(p.getCode(), product.getCode())
       );

       if (isExists) {
           throw new IllegalArgumentException("Product code is duplicated");
       }

       productList.add(product);
    }
}
