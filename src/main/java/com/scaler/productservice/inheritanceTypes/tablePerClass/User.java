package com.scaler.productservice.inheritanceTypes.tablePerClass;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
}
