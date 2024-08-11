package com.scaler.productservice.service;

import com.scaler.productservice.dtos.FakeStoreDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;
    @Override
    public Product getSingleProduct(long id) {
        FakeStoreDto fakeStoreDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id , FakeStoreDto.class
        );
        return convertFakeStoreDtoToProduct(fakeStoreDto);
     }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(long id) {
        return null;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreDto fakeStoreDto) {
        Product product=new Product();
        product.setId(fakeStoreDto.getId());
        product.setTitle(fakeStoreDto.getTitle());
        product.setPrice(fakeStoreDto.getPrice());
        Category category=new Category();
        category.setDescription(fakeStoreDto.getDescription());
        product.setCategory(category);
        return product;
    }
}
