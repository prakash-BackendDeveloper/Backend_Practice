package com.scaler.productservice.service;

import com.scaler.productservice.controller.ProductController;
import com.scaler.productservice.dtos.FakeStoreDto;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        FakeStoreDto fakeStoreDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id , FakeStoreDto.class
        );
        if(fakeStoreDto==null){
            throw new ProductNotFoundException(id);
        }
        return convertFakeStoreDtoToProduct(fakeStoreDto);

     }

    @Override
    public List<Product> getAllProducts() {
       FakeStoreDto[] fakeStoreDtos=restTemplate.getForObject(
               "https://fakestoreapi.com/products",FakeStoreDto[].class
       );
        List<Product> product=new ArrayList<>();
       for(FakeStoreDto fakeStoreDto:fakeStoreDtos){
           product.add(convertFakeStoreDtoToProduct(fakeStoreDto));
       }
       return product;
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreDto.class);
        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor
                (FakeStoreDto.class, restTemplate.getMessageConverters());
        FakeStoreDto fakeStoreDto= restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT, requestCallback, responseExtractor);


        return convertFakeStoreDtoToProduct(fakeStoreDto);
    }

    @Override
    public Product updateProduct(long id, Product product) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreDto.class);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor
                (FakeStoreDto.class, restTemplate.getMessageConverters());
        FakeStoreDto fakeStoreDto= restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.PATCH, requestCallback, responseExtractor);


        return convertFakeStoreDtoToProduct(fakeStoreDto);
    }

    @Override
    public Product deleteProduct(long id) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(null);
        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor
                (FakeStoreDto.class, restTemplate.getMessageConverters());
        FakeStoreDto fakeStoreDto= restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.DELETE, requestCallback, responseExtractor);


        return convertFakeStoreDtoToProduct(fakeStoreDto);
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
