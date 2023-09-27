package com.adservice.Advertisement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {
    @JsonProperty("Name")
    private String name;
}
