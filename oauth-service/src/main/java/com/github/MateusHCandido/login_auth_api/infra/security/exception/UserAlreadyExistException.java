package com.github.MateusHCandido.login_auth_api.infra.security.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
