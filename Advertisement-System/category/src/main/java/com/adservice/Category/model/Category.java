package com.adservice.Category.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private int id;
    @JsonProperty("Name")
    private String name;
}
