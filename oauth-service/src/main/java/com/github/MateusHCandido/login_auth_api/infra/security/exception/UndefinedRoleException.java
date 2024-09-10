package com.github.MateusHCandido.login_auth_api.infra.security.exception;

public class UndefinedRoleException extends RuntimeException {
    public UndefinedRoleException(String message) {
        super(message);
    }
}
