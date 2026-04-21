package com.marcello.cliente_service.controller;

import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.service.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
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
    public PageImpl<UsuarioDTO> buscarTodosUsuarios(Pageable pageable) {
        return usuarioService.buscarTodos(pageable);
    }

    @GetMapping("/email")
    @Operation(description = "Retorna um usuario por email", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<UsuarioDTO>> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        UsuarioDTO usuarioDTO = usuarioService.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(Optional.of(usuarioDTO));
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna um usuario por id", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<UsuarioDTO>> buscarUsuarioPorId(@PathVariable("id") Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(Optional.of(usuarioDTO));
    }

    @PostMapping
    @Operation(description = "Salva um novo usuario", method = "POST")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var existis = usuarioService.existsByEmail(usuarioDTO.getEmail());
        if(existis) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST, "Ja existe usuario com este email");
        }
        UsuarioDTO saved = usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Atualiza um usuario", method = "PUT")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable("id") Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return usuarioService.findById(id).map(usuario -> {
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuarioService.save(usuario);
            return ResponseEntity.ok(usuarioDTO);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Remove um usuario")
    public ResponseEntity<Void> removerUsuario(@PathVariable("id") Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
