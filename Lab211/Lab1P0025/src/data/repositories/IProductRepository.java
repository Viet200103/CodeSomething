package data.repositories;

import business.entities.Product;

import java.util.List;

public interface IProductRepository {
    void addNewProduct(Product product) throws Exception;
    List<Product> getAllProducts() throws Exception;
    boolean deleteProduct(String productCode) throws Exception;

    Product getProduct(String code) throws Exception;

    boolean updateProduct(Product product) throws Exception;
}
