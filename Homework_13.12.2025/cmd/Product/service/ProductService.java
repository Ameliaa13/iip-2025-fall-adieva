package cmd.Product.service;

import cmd.Product.repository.*;

import java.util.ArrayList;

import cmd.Product.Entity.*;

public class ProductService {
    
    private ProductRepo repo = new ProductRepoImpl();

    public void addProduct(String name, double price, int quantity) {
        repo.add(new ProductEntity(name, price, 0, quantity));
        System.out.println("Товар успешно добавлен");
    }

    public ProductEntity getById(long id) {
        return repo.findById(id);
    }

    public ArrayList<ProductEntity> findAll() {
        return repo.findAll();
    }

    public void removeById(long id) {
        repo.removeById(id);
        System.out.println("Товар успешно удален");
    }

    public void updateQuantity(long id, int quantity) {
        ProductEntity product = getById(id);
        product.setQuantity(quantity);
        repo.update(product);
    }


}
