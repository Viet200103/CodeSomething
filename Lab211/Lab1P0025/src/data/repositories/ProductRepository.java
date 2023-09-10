package data.repositories;

import business.entities.Product;
import data.dao.product.IProductDao;
import data.dao.product.ProductDao;
import data.managers.IFileManager;

import java.util.List;

public class ProductRepository implements IProductRepository {

    private final IProductDao productDao;

    private static ProductRepository INSTANCE;


    public ProductRepository(IFileManager fileManager) {
        this.productDao = new ProductDao(fileManager);
    }

    @Override
    public Product getProduct(String code) throws Exception {
        return productDao.loadProduct(code);
    }

    @Override
    public void addNewProduct(Product product) throws Exception {
        productDao.addNewProduct(product);
    }

    @Override
    public boolean deleteProduct(String productCode) throws Exception {
        boolean isReceiptGenerated = isProductReceiptExist(productCode);
        if (isReceiptGenerated) {
            throw new RuntimeException("Can not delete product once receipt is generated");
        }

        return productDao.deleteProduct(productCode);
    }

    @Override
    public boolean updateProduct(Product product) throws Exception {
        return productDao.updateProduct(product);
    }

    private boolean isProductReceiptExist(String productCode) {
        return false;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        return productDao.loadProductFromFile();
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
