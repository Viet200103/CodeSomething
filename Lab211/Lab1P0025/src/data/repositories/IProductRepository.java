package data.repositories;

import business.entities.Product;

public interface IProductRepository {
    void addNewProduct(Product product) throws Exception;
}
