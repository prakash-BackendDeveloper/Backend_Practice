package com.scaler.productservice;

import com.scaler.productservice.projections.ProductWithIdAndTitle;
import com.scaler.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServicesApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDbQueries(){
        List<ProductWithIdAndTitle> productwithIdandTitles = productRepository.randomSearchMethod(19L);

        for(ProductWithIdAndTitle product : productwithIdandTitles){
            System.out.println(product.getId() +" "+ product.getTitle());
        }

        ProductWithIdAndTitle productWithIdAndTitle =productRepository.randomSearchMethod2(4L);
        System.out.println(productWithIdAndTitle.getId() +" "+ productWithIdAndTitle.getTitle());
    }

}
