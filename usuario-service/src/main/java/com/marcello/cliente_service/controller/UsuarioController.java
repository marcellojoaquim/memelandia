package com.marcello.cliente_service.controller;

import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.service.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.ws.rs.client.ResponseProcessingException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Buscar todos os usuarios", method = "GET")
    public PageImpl<UsuarioDTO> buscarTodos(Pageable pageable) {
        return usuarioService.buscarTodos(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Salva um novo usuario", method = "POST")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var existis = usuarioService.existsByEmail(usuarioDTO.getEmail());
        if(existis) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST, "Ja existe usuario com este email");
        }
       return ResponseEntity.ok(usuarioService.save(usuarioDTO));
    }
}
