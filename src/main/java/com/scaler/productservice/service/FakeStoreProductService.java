package com.scaler.productservice.service;

import com.scaler.productservice.dtos.FakeStoreDto;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final RedisTemplate redisTemplate;
    RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate, @Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {

        Product product = (Product) redisTemplate.opsForHash().get("Products","Product_"+id);
        if (product != null) {
            return product;
        }
        FakeStoreDto fakeStoreDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id , FakeStoreDto.class
        );
        if(fakeStoreDto==null){
            throw new ProductNotFoundException(id);
        }
        Product product1=convertFakeStoreDtoToProduct(fakeStoreDto);

        //store it in redis then return the product
        redisTemplate.opsForHash().put("Products","Product_"+id,product1);
        return product1;


     }

    @Override
    public Page<Product> getAllProducts(int page, int size) throws ProductNotFoundException {
       FakeStoreDto[] fakeStoreDtos=restTemplate.getForObject(
               "https://fakestoreapi.com/products",FakeStoreDto[].class
       );
        List<Product> product=new ArrayList<>();
       for(FakeStoreDto fakeStoreDto:fakeStoreDtos){
           product.add(convertFakeStoreDtoToProduct(fakeStoreDto));
       }
       return new PageImpl<>(product);
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
    public void deleteProduct(long id) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(null);
        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor = new HttpMessageConverterExtractor
                (FakeStoreDto.class, restTemplate.getMessageConverters());
        FakeStoreDto fakeStoreDto= restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.DELETE, requestCallback, responseExtractor);


      //  return convertFakeStoreDtoToProduct(fakeStoreDto);
    }

    @Override
    public Product addProduct(Product product) {
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
