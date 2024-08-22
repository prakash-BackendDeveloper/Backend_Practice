package com.scaler.productservice.service;

import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return product.get();
    }

    @Override
    public Page<Product> getAllProducts(int page, int size) throws ProductNotFoundException {
        PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product replaceProduct(long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        return null;
    }

    @Override
    public Product updateProduct(long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        Product productInDb=optionalProduct.get();
        //if(productInDb.getTitle()==null){
            productInDb.setTitle(product.getTitle());
       // }
        //if(productInDb.getPrice()==0){
            productInDb.setPrice(product.getPrice());
       // }
        return productRepository.save(productInDb);
    }

    @Override
    public void deleteProduct(long id) {
       productRepository.deleteById((int) id);
      //Optional<> optional= ProductRepository.deleteById(id);
    }

    @Override
    public Product addProduct(Product product) {
//        Category category=product.getCategory();
//        if(category.getId() == 0) {
//            category=categoryRepository.save(category);
//            product.setCategory(category);
//        }
        return productRepository.save(product);
    }
}
