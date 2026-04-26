package com.marcello.post_server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException( ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(Map.of(
                "timestamp", System.currentTimeMillis(),
                "status", e.getStatusCode().value(),
                "erro", "Falha na integração",
                "mensagem", e.getReason()
        ));
    }
}
