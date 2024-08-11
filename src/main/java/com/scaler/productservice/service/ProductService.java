package com.scaler.productservice.service;

import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product replaceProduct(long id, Product product);
    public Product updateProduct(long id,Product product);
    public Product deleteProduct(long id);
}
