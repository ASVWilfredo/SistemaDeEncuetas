package com.api.rest.api_rest.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExcepcionNoEncontrada extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionNoEncontrada() {
    }

    public ExcepcionNoEncontrada(String message) {
        super(message);
    }

    public ExcepcionNoEncontrada(String message, Throwable cause) {
        super(message, cause);
    }
}
