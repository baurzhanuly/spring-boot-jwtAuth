package com.example.springbootjwtAuth.exceptions;

public class AuthenticationException  extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
