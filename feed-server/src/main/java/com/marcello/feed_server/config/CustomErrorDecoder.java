package com.marcello.feed_server.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        if(status.is5xxServerError()) {
            return new ResponseStatusException(status, "Erro ao salvar meme");
        }

        return new Default().decode(methodKey, response);
    }
}
