package business.services;

import business.entities.Product;
import data.repositories.IProductRepository;

public class ProductService implements ItemService<Product> {

    private final IProductRepository pRepository;

    public ProductService(IProductRepository pRepository) {
        this.pRepository = pRepository;
    }

    @Override
    public void add(Product item) throws Exception {
        pRepository.addNewProduct(item);
    }

    @Override
    public void printList() {
        try {
            pRepository.loadAllProduct().forEach(
                    System.out::println
            );
        } catch (Exception e) {
            System.out.println("Load data from file is failed");
        }
    }
}
