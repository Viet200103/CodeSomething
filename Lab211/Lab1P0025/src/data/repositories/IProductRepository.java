package data.repositories;

import business.entities.Product;

import java.util.List;

public interface IProductRepository {
    void addNewProduct(Product product) throws Exception;
    List<Product> loadAllProduct() throws Exception;

    void deleteProduct(String productCode) throws Exception;
}
