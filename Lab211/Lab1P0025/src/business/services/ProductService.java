package business.services;

import business.entities.Product;
import data.repositories.IProductRepository;

public class ProductService implements ItemService<Product> {

    private IProductRepository pRepository;

    public ProductService(IProductRepository pRepository) {
        this.pRepository = pRepository;
    }

    @Override
    public void add(Product item) throws Exception {
        pRepository.addNewProduct(item);
    }
}
