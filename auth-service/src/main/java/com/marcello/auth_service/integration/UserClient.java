package com.marcello.auth_service.integration;

import com.marcello.auth_service.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "usuario-service")
public interface UserClient {

    @GetMapping("/usuarios/email")
    Optional<UsuarioDTO> findUserByEmail(@RequestParam("email") String email);
}
