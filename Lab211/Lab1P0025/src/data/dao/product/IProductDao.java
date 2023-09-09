package data.dao.product;

import business.entities.Product;

public interface IProductDao {

    void loadProductFromFile() throws Exception;

    void addNewProduct(Product product) throws Exception;
}
