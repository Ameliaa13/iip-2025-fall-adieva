package cmd.Product.repository;

import java.util.ArrayList;
import java.util.List;

import cmd.Product.Entity.ProductEntity;

public class ProductRepoImpl implements ProductRepo {

    private List<ProductEntity> products = new ArrayList<>();
    private int nextId = 0;

    @Override
    public ArrayList<ProductEntity> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public ProductEntity findById(long id) {
        for (ProductEntity p : products) {
            if (id == p.getId()) return p;
        }
        return null;
    }

    @Override
    public void add(ProductEntity product) {
        product.setId(nextId++);
        products.add(product);
    }

    @Override
    public boolean update(ProductEntity product) {
        ProductEntity existingProduct = findById(product.getId());
        if (existingProduct == null) return false;
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return true;
    }

    @Override
    public boolean removeById(long id) {
        ProductEntity productToDelete = findById(id);
        if (productToDelete == null) return false;
        products.remove(findById(id));
        return true; 
    } 
    
}
