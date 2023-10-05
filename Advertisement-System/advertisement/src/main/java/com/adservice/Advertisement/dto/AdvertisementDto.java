package com.adservice.Advertisement.dto;

import com.adservice.Advertisement.enums.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdvertisementDto {
    // validatiom?
    @JsonProperty("Category")
    private int category;
    @JsonProperty("Product")
    private String product;
    // fieldname user is int?
    @JsonProperty("User")
    private int user;
    @JsonProperty("Price")
    private double price;
    @JsonProperty("Type")
    private Type type;
}
