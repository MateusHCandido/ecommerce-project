package com.github.io.MateusHCandido.product_service.infrastructure.controller.exception;

import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductAlreadyRegisteredException;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleProductAlreadyRegistered(ProductAlreadyRegisteredException e){
        return e.getMessage();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFound (ProductNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(BlankFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBlankField(BlankFieldException e){
        return e.getMessage();
    }

    @ExceptionHandler(ProductPriceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleProductPrice(ProductPriceException e){
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException e){
        return e.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNullPointer(NullPointerException e){
        return e.getMessage();
    }
}
