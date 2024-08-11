package com.scaler.productservice.exception;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{
    private long id;
    public ProductNotFoundException(long id){
        this.id=id;
    }
}
