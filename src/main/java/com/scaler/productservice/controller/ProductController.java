package com.scaler.productservice.controller;

import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity= new ResponseEntity<>(
                productService.getSingleProduct(id), HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> responseEntity= new ResponseEntity<>(
                productService.getAllProducts(),HttpStatus.OK
        );
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity= new ResponseEntity<>(
                productService.updateProduct(id,product) , HttpStatus.OK
        );
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity= new ResponseEntity<>(
                productService.replaceProduct(id,product) , HttpStatus.OK
        );
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
//        ResponseEntity<Product> responseEntity= new ResponseEntity<>(
//                productService.deleteProduct(id) , HttpStatus.OK
//        );
//        return responseEntity;

        productService.deleteProduct(id);
    }

    @PostMapping("")
    public Product createProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @ExceptionHandler(ArithmeticException.class)
    private ResponseEntity<String> handleArithmeticException(){
        ResponseEntity<String> responseEntity=new ResponseEntity<>(
                "Try again from Controller",HttpStatus.FORBIDDEN
        );
        return responseEntity;
    }

}
