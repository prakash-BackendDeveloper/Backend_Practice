package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private double price;
    private String title;
    @ManyToOne
    private Category category;

}
