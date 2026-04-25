package com.marcello.post_server.service;

import com.marcello.post_server.domain.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "meme", url = "${application.usuarioService.endpointConsultarUsuario}")
public interface IUsurarioService {

    @GetMapping(
            value = "/usuarios/email",
            produces = "application/json",
            headers = "application/json")
    Usuario buscarUsuario(@RequestParam("email") String email);
}
