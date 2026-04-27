package com.marcello.feed_server.integration;

import com.marcello.feed_server.config.FeignConfig;
import com.marcello.feed_server.domain.dto.MemeDTO;
import org.springframework.cloud.openfeign.FeignClient;
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
}
