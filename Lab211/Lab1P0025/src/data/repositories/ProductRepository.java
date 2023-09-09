package data.repositories;

import business.entities.Product;
import data.dao.product.IProductDao;
import data.dao.product.ProductDao;
import data.managers.IFileManager;

public class ProductRepository implements IProductRepository {

    private IProductDao productDao;

    private static ProductRepository INSTANCE;


    public ProductRepository(IFileManager fileManager) throws Exception {
        this.productDao = new ProductDao(fileManager);
    }

    @Override
    public void addNewProduct(Product product) throws Exception {
        productDao.addNewProduct(product);
    }

    public static IProductRepository getInstance(IFileManager fileManager) {

        if (INSTANCE == null) {
            synchronized (ProductRepository.class) {
                try {
                    INSTANCE = new ProductRepository(fileManager);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return INSTANCE;
    }

}
