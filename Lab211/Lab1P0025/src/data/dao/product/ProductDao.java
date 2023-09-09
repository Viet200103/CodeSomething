package data.dao.product;

import business.entities.Product;
import business.utilities.ProductType;
import data.managers.IFileManager;

import java.util.*;

public class ProductDao implements IProductDao {
    private IFileManager fileManager;

    private ProductDao() {

    }

    public ProductDao(IFileManager fileManager)  {
        this.fileManager = fileManager;
    }

    @Override
    public List<Product> loadProductFromFile() throws Exception {
        List<String> dataList = fileManager.readDataFromFile();
        List<Product> productList = new ArrayList<>();

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
                case 0 -> type = ProductType.DAILY;
                case 1 -> type = ProductType.LONG_LIFE;
            }

            name = raws.get(2).trim();
            maker = raws.get(3).trim();
            manufacturingDate = raws.get(4).trim();
            expirationDate = raws.get(5).trim();

            int quantity = Integer.parseInt(
                    raws.get(6).trim()
            );

            Product product = new Product(code, type, name, maker, manufacturingDate, expirationDate, quantity);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public void addNewProduct(Product product) throws Exception {
       boolean isExists = fileManager.isCodeExists(product.getCode());

       if (isExists) {
           throw new IllegalArgumentException("Product code is duplicated");
       }

       fileManager.saveItem(productToRawData(product));
    }

    private String productToRawData(Product product) {
        return product.getCode() + ','
                + product.getType().getValue() + ','
                + product.getName() + ','
                + product.getMaker() + ','
                + product.getManufacturingDate() + ','
                + product.getManufacturingDate() + ','
                + product.getQuantity();

    }
}
