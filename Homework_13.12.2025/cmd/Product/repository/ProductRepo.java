package cmd.Product.repository;

import java.util.*;

import cmd.Product.Entity.ProductEntity;

public interface ProductRepo {

    ArrayList<ProductEntity> findAll();

    ProductEntity findById(long id);

    void add(ProductEntity product);
    
    boolean update(ProductEntity product);

    boolean removeById(long id);

}
