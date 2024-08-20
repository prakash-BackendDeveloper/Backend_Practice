package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(long id);


    //void deleteById(long id);

    Product findProductByTitleContains(String title);

    Product findByPriceGreaterThan(double price);

    Product findByTitleLikeIgnoreCase(String title);

    Product findTop5ByOrderByIdAsc();

    Product findTop5ByTitleLikeIgnoreCase(String title);

    Product findProductByTitleContainsAndPriceGreaterThan(String title, double price);

    @Query("select p.id as id, p.title as title from Product p where p.id=:product_id")
    List<ProductWithIdAndTitle> randomSearchMethod(Long product_id);

    @Query(value = "select p.id as id,p.title as title from Product p where p.id=:product_id", nativeQuery = true)
    ProductWithIdAndTitle randomSearchMethod2(Long product_id);




}
