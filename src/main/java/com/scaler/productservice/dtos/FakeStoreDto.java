package com.scaler.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
}
