package com.marcello.cliente_service.controller;

import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.service.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

    @GetMapping("/email")
    @Operation(description = "Retorna um usuario por email", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<UsuarioDTO>> buscarPorEmail(@RequestParam("email") String email) {
        UsuarioDTO usuarioDTO = usuarioService.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(Optional.of(usuarioDTO));
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna um usuario por id", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<UsuarioDTO>> buscarPorId(@PathVariable("id") Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(Optional.of(usuarioDTO));
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
