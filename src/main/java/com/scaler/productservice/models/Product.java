package com.scaler.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private double price;
    private String title;
    private Category category;

}
