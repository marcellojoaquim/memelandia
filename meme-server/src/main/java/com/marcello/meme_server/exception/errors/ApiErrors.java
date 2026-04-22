package com.marcello.meme_server.exception.errors;

import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    List<String> errors;

    public ApiErrors(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(err -> this.errors.add(err.getDefaultMessage()));
    }

    public ApiErrors(ResponseStatusException exception) {
        this.errors = Arrays.asList(exception.getReason());
    }

    public ApiErrors(BusinessException businessException) {
        this.errors = Arrays.asList(businessException.getMessage());
    }
}
