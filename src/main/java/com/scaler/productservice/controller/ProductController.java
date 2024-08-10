package com.scaler.productservice.controller;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getSingleProduct(id);
    }

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}
