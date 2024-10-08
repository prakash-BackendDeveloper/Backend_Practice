package com.scaler.productservice.service;

import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(long id) throws ProductNotFoundException;
    public Page<Product> getAllProducts(int pageNumber, int pageSize) throws ProductNotFoundException;

    public Product replaceProduct(long id, Product product) throws ProductNotFoundException;
    public Product updateProduct(long id,Product product) throws ProductNotFoundException;
    public void deleteProduct(long id);
    public Product addProduct(Product product);
}
