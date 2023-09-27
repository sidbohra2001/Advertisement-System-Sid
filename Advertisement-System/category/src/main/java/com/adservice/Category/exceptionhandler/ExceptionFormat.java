package com.adservice.Category.exceptionhandler;// package com.inventory.Order.exceptionhandler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExceptionFormat {
    public String statusCode;
    public String message;
}
