package com.adservice.Advertisement.exceptionhandler;// package com.inventory.Order.exceptionhandler;


import com.adservice.Advertisement.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
public class ExceptionsHandler {
// handle internal server error as well.
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ExceptionFormat> handleInvalidIdException(InvalidIdException e){
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ExceptionFormat> handleNoDataException(NoDataException e){
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.NOT_FOUND.name()).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MismatchException.class)
    public ResponseEntity<ExceptionFormat> handleMismatchException(MismatchException e){
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ExceptionFormat> handleRestClientException(RestClientResponseException e){
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(e.getResponseBodyAs(ExceptionFormat.class).getStatusCode()).message(e.getResponseBodyAs(ExceptionFormat.class).getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
