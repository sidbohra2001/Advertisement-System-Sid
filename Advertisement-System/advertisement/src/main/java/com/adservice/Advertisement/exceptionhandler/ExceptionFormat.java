package com.adservice.Advertisement.exceptionhandler;// package com.inventory.Order.exceptionhandler;

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
