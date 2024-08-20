package com.scaler.productservice.inheritanceTypes.singleTable;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_user")
@DiscriminatorColumn(
        name="st_userType",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue("0")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
}
