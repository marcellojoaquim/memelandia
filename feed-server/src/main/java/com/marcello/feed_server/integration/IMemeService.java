package com.marcello.feed_server.integration;

import com.marcello.feed_server.config.FeignConfig;
import com.marcello.feed_server.domain.dto.CategoriaDTO;
import com.marcello.feed_server.domain.dto.MemeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "meme-server",
        url = "${application.memeService.endpointSalvarMeme}",
        configuration = FeignConfig.class
)
public interface IMemeService {

    @PostMapping(
            value = "/memes",
            produces = "application/json",
            headers = "application/json"
    )
    void salvarMeme(@RequestBody MemeDTO memeDTO);


    @GetMapping(
            value = "/categorias",
            produces = "application/json",
            headers = "application/json"
    )
    Page<CategoriaDTO> buscarCategorias(Pageable pageable);
}
