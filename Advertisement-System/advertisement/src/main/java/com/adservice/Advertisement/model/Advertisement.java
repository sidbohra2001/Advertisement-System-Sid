package com.adservice.Advertisement.model;

import com.adservice.Advertisement.enums.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private int id;
    @JsonProperty("Category")
    private int category;
    @JsonProperty("Product")
    private String product;
    @JsonProperty("User")
    private int user;
    @JsonProperty("Price")
    private double price;
    @JsonProperty("Type")
    @Enumerated(value = EnumType.STRING)
    private Type type;
}
