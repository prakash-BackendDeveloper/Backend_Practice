package com.scaler.productservice;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdAndTitle;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServicesApplicationTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDbQueries(){
//        List<ProductWithIdAndTitle> productwithIdandTitles = productRepository.randomSearchMethod(19L);
//
//        for(ProductWithIdAndTitle product : productwithIdandTitles){
//            System.out.println(product.getId() +" "+ product.getTitle());
//        }
//
//        ProductWithIdAndTitle productWithIdAndTitle =productRepository.randomSearchMethod2(4L);
//        System.out.println(productWithIdAndTitle.getId() +" "+ productWithIdAndTitle.getTitle());
        Optional<Product> product = productRepository.findById(1L);
        System.out.println(product.get().getTitle()+" "+product.get().getPrice()+" "+product.get().getCategory());


       // Optional<Category> category = categoryRepository.findById(1L);

//        List<Product> products=category.get().getProducts();
        System.out.println("debug");
    }

}
