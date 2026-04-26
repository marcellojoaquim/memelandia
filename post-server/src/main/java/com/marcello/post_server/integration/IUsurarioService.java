package com.marcello.post_server.integration;

import com.marcello.post_server.config.FeignConfig;
import com.marcello.post_server.domain.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "usuario-server",
        url = "${application.usuarioService.endpointConsultarUsuario}",
        configuration = FeignConfig.class)
public interface IUsurarioService {

    @GetMapping(
            value = "/email",
            produces = "application/json",
            headers = "application/json")
    Usuario buscarUsuario(@RequestParam("email") String email);
}
