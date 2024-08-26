package com.github.io.MateusHCandido.product_service.domain.exception;

public class BlankFieldException extends RuntimeException {
    public BlankFieldException(String blankFieldException) {
        super(blankFieldException);
    }
}
